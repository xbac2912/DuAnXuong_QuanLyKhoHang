package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;


import java.util.ArrayList;

public class DAO_PhieuXuat {
    private DBHelper dbHelper;

    public DAO_PhieuXuat(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void themPhieuXuat(String maSanPham, int soLuong, String ngayXuat, boolean daXuatKho) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Thêm phiếu xuất vào bảng PHIEUXUAT
            ContentValues phieuXuatValues = new ContentValues();
            phieuXuatValues.put("NgayXuat", ngayXuat);
            phieuXuatValues.put("DaXuatKho", daXuatKho ? 1 : 0); // Chuyển đổi giá trị checkbox thành số nguyên 1 hoặc 0
            long phieuXuatId = db.insert("tb_phieuxuat", null, phieuXuatValues);
            // Thêm chi tiết phiếu xuất vào bảng CTPHIEUXUAT
            ContentValues ctPhieuXuatValues = new ContentValues();
            ctPhieuXuatValues.put("SoPhieu", phieuXuatId);
            ctPhieuXuatValues.put("MaSP", maSanPham);
            ctPhieuXuatValues.put("Soluong", soLuong);
            db.insert("tb_CTPhieuxuat", null, ctPhieuXuatValues);

            // Cập nhật thông tin số lượng tồn trong kho sau khi thực hiện phiếu xuất
            capNhatSoLuongTonSanPham(maSanPham, soLuong); // Trừ đi số lượng xuất

        } finally {
            db.close();
        }
    }


    public ArrayList<DTO_PhieuXuat> layDanhSachPhieuXuat() {
        ArrayList<DTO_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT px.SoPhieu, sp.MaSP, sp.TenSP, px.NgayXuat, pn.gia, ctx.Soluong, px.DaXuatKho " +
                "FROM tb_phieuxuat px " +
                "INNER JOIN tb_CTPhieuxuat ctx ON px.SoPhieu = ctx.SoPhieu " +
                "INNER JOIN tb_phieunhap pn ON ctx.SoPhieu = pn.sophieu " +
                "INNER JOIN tb_SanPham sp ON ctx.MaSP = sp.MaSP";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                do {
                    int soPhieu = cursor.getInt(0);
                    String maSanPham = cursor.getString(1);
                    String tenSanPham = cursor.getString(2);
                    String ngayXuat = cursor.getString(3);
                    int giaSanPham = cursor.getInt(4);
                    int soLuong = cursor.getInt(5);
                    int daXuatHang = cursor.getInt(6); // Lấy trạng thái checkbox

                    boolean isChecked = (daXuatHang == 1); // Chuyển đổi giá trị thành trạng thái checkbox

                    DTO_PhieuXuat phieuXuat = new DTO_PhieuXuat(soPhieu, maSanPham, tenSanPham, ngayXuat, giaSanPham, soLuong);
                    phieuXuat.setDaXuatKho(isChecked); // Đặt trạng thái checkbox vào đối tượng DTO_PhieuXuat
                    danhSachPhieuXuat.add(phieuXuat);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }

        db.close();
        return danhSachPhieuXuat;
    }


    private void capNhatSoLuongTonSanPham(String maSanPham, int soLuongXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Lấy thông tin số lượng tồn của sản phẩm trong bảng tb_SanPham
            String[] columns = {"soLuongTon"};
            String selection = "MaSP = ?";
            String[] selectionArgs = {String.valueOf(maSanPham)};
            Cursor cursor = db.query("tb_SanPham", columns, selection, selectionArgs, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int soLuongTonIndex = cursor.getColumnIndex("soLuongTon");
                    if (soLuongTonIndex != -1) {
                        int soLuongTonHienTai = cursor.getInt(soLuongTonIndex);

                        // Kiểm tra xem có đủ số lượng tồn để xuất hàng không
                        if (soLuongTonHienTai >= soLuongXuat) {
                            // Cập nhật số lượng tồn của sản phẩm (số lượng hiện tại - số lượng xuất)
                            int soLuongMoi = soLuongTonHienTai - soLuongXuat;
                            ContentValues values = new ContentValues();
                            values.put("soLuongTon", soLuongMoi);

                            // Cập nhật thông tin số lượng tồn của sản phẩm trong bảng tb_SanPham
                            db.update("tb_SanPham", values, selection, selectionArgs);
                        } else {
                            // Xử lý khi số lượng tồn không đủ để xuất hàng
                            Log.e("TAG", "Số lượng tồn kho không đủ để xuất!");
                        }
                    } else {
                        // Xử lý khi cột "soLuongTon" không tồn tại trong bảng
                    }
                } else {
                    // Xử lý khi cursor không có dữ liệu (cột "soLuongTon" không tồn tại trong kết quả truy vấn)
                }
                cursor.close();
            }
        } finally {
            db.close();
        }
    }


    public void suaPhieuXuat(int maPhieu, int soLuongMoi, String ngayXuat, boolean daXuatKho) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String[] columns = {"MaSP", "Soluong"};
            String selection = "SoPhieu = ?";
            String[] selectionArgs = {String.valueOf(maPhieu)};
            Cursor cursor = db.query("tb_CTPhieuxuat", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int maSPIndex = cursor.getColumnIndex("MaSP");
                int soLuongCuIndex = cursor.getColumnIndex("Soluong");

                if (soLuongCuIndex != -1 && maSPIndex != -1) {
                    int soLuongCu = cursor.getInt(soLuongCuIndex);
                    String maSanPham = cursor.getString(maSPIndex);

                    ContentValues phieuXuatValues = new ContentValues();
                    phieuXuatValues.put("NgayXuat", ngayXuat);
                    String whereClause = "SoPhieu = ?";
                    String[] whereArgs = {String.valueOf(maPhieu)};

                    db.update("tb_phieuxuat", phieuXuatValues, whereClause, whereArgs);

                    ContentValues ctPhieuXuatValues = new ContentValues();
                    ctPhieuXuatValues.put("Soluong", soLuongMoi);
                    db.update("tb_CTPhieuxuat", ctPhieuXuatValues, whereClause, whereArgs);

                    // Cập nhật thông tin số lượng tồn trong kho sau khi sửa phiếu xuất
                    int chenhLechSoLuong = soLuongCu - soLuongMoi;
                    capNhatSoLuongTonSanPham(maSanPham, chenhLechSoLuong);

                    // Cập nhật trạng thái checkbox
                    ContentValues checkBoxValues = new ContentValues();
                    checkBoxValues.put("DaXuatKho", daXuatKho ? 1 : 0);
                    db.update("tb_phieuxuat", checkBoxValues, whereClause, whereArgs);
                } else {
                    // Xử lý khi cột "Soluong" hoặc "MaSP" không tồn tại trong kết quả truy vấn
                    Log.e("TAG", "Mã sản phẩm hoặc số lượng không tồn tại!");
                }
            } else {
                // Xử lý khi cursor không có dữ liệu (không tìm thấy phiếu xuất)
                Log.e("TAG", "Không tìm thấy phiếu xuất!");
            }

            if (cursor != null) {
                cursor.close();
            }
        } finally {
            db.close();
        }
    }




    public void xoaPhieuXuat(DTO_PhieuXuat phieuXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String whereClause = "SoPhieu = ?";
            String[] whereArgs = {String.valueOf(phieuXuat.getMaPhieu())};

            // Xóa phiếu xuất và chi tiết phiếu xuất
            db.delete("tb_phieuxuat", whereClause, whereArgs);
            db.delete("tb_CTPhieuxuat", whereClause, whereArgs);

        } finally {
            db.close();
        }
    }

}
