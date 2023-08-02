package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;


import java.util.ArrayList;

public class DAO_PhieuXuat {
    private DBHelper dbHelper;
    public DAO_PhieuXuat (Context context) {
        dbHelper = new DBHelper(context);
    }
    public void themPhieuXuat(String maSanPham, int soLuong, String ngayXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Thêm phiếu xuất vào bảng PHIEUXUAT
            ContentValues phieuXuatValues = new ContentValues();
            phieuXuatValues.put("NgayXuat", ngayXuat);
            long phieuXuatId = db.insert("tb_phieuxuat", null, phieuXuatValues);

            // Thêm chi tiết phiếu xuất vào bảng CTPHIEUXUAT
            ContentValues ctPhieuXuatValues = new ContentValues();
            ctPhieuXuatValues.put("SoPhieu", phieuXuatId);
            ctPhieuXuatValues.put("MaSP", maSanPham);
            ctPhieuXuatValues.put("Soluong", soLuong);
            db.insert("tb_CTPhieuxuat", null, ctPhieuXuatValues);

            // Cập nhật thông tin số lượng sản phẩm trong kho sau khi thực hiện phiếu xuất
            truSoLuongSanPhamTrongKho(maSanPham, soLuong);
        } finally {
            db.close();
        }
    }

    public ArrayList<DTO_PhieuXuat> layDanhSachPhieuXuat() {
        ArrayList<DTO_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT px.SoPhieu, sp.MaSP, sp.TenSP, px.NgayXuat, pn.gia, ctx.Soluong " +
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

                    DTO_PhieuXuat phieuXuat = new DTO_PhieuXuat(soPhieu, maSanPham, tenSanPham, ngayXuat, giaSanPham, soLuong);
                    danhSachPhieuXuat.add(phieuXuat);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }

        db.close();
        return danhSachPhieuXuat;
    }

    public void capNhatSoLuongSanPhamTrongKho(int maPhieu, int soLuongNhap) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Lấy thông tin số lượng sản phẩm hiện tại trong kho
            String[] columns = {"MaSP", "Soluong"};
            String selection = "SoPhieu = ?";
            String[] selectionArgs = {String.valueOf(maPhieu)};
            Cursor cursor = db.query("tb_CTPhieuxuat", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int maSanPhamIndex = cursor.getColumnIndex("MaSP");
                int soLuongHienTaiIndex = cursor.getColumnIndex("SoLuong");

                if (maSanPhamIndex != -1 && soLuongHienTaiIndex != -1) {
                    String maSanPham = cursor.getString(maSanPhamIndex);
                    int soLuongHienTai = cursor.getInt(soLuongHienTaiIndex);

                    // Cập nhật số lượng sản phẩm trong kho (số lượng hiện tại + số lượng nhập)
                    int soLuongMoi = soLuongHienTai + soLuongNhap;

                    ContentValues values = new ContentValues();
                    values.put("SoLuong", soLuongMoi);

                    // Cập nhật thông tin số lượng sản phẩm trong kho
                    String updateWhereClause = "MaSP = ?";
                    String[] updateWhereArgs = {String.valueOf(maSanPham)};
                    db.update("tb_SanPham", values, updateWhereClause, updateWhereArgs);
                } else {
                    // Xử lý khi cột "MaSP" hoặc "SoLuong" không tồn tại trong bảng
                    // Bạn có thể ghi log lỗi hoặc hiển thị thông báo
                }
            }

            if (cursor != null) {
                cursor.close();
            }
        } finally {
            db.close();
        }
    }

    public void suaPhieuXuat(int maPhieu, int soLuong, String ngayXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues phieuXuatValues = new ContentValues();
            phieuXuatValues.put("NgayXuat", ngayXuat);

            String whereClause = "SoPhieu = ?";
            String[] whereArgs = { String.valueOf(maPhieu) };

            db.update("tb_phieuxuat", phieuXuatValues, whereClause, whereArgs);

            ContentValues ctPhieuXuatValues = new ContentValues();
            ctPhieuXuatValues.put("Soluong", soLuong);

            db.update("tb_CTPhieuxuat", ctPhieuXuatValues, whereClause, whereArgs);

            // Cập nhật thông tin số lượng sản phẩm trong kho sau khi sửa phiếu xuất
            capNhatSoLuongSanPhamTrongKho(maPhieu, soLuong);
        } finally {
            db.close();
        }
    }


    public void xoaPhieuXuat(DTO_PhieuXuat phieuXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String whereClause = "SoPhieu = ?";
            String[] whereArgs = { String.valueOf(phieuXuat.getMaPhieu()) };

            // Xóa phiếu xuất và chi tiết phiếu xuất
            db.delete("tb_phieuxuat", whereClause, whereArgs);
            db.delete("tb_CTPhieuxuat", whereClause, whereArgs);

        } finally {
            db.close();
        }
    }

    private void truSoLuongSanPhamTrongKho(String maSanPham, int soLuongXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Lấy thông tin số lượng sản phẩm hiện tại trong bảng tb_phieunhap
            String[] columns = {"soLuong"};
            String selection = "MaSP = ?";
            String[] selectionArgs = {String.valueOf(maSanPham)};
            Cursor cursor = db.query("tb_phieunhap", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int soLuongHienTaiIndex = cursor.getColumnIndex("soLuong");
                if (soLuongHienTaiIndex != -1) {
                    int soLuongHienTai = cursor.getInt(soLuongHienTaiIndex);

                    // Kiểm tra nếu số lượng tồn kho không đủ để xuất hàng
                    if (soLuongHienTai >= soLuongXuat) {
                        // Cập nhật số lượng sản phẩm trong bảng tb_phieunhap (số lượng hiện tại - số lượng xuất)
                        int soLuongMoi = soLuongHienTai - soLuongXuat;
                        ContentValues values = new ContentValues();
                        values.put("soLuong", soLuongMoi);

                        // Cập nhật thông tin số lượng sản phẩm trong bảng tb_phieunhap
                        db.update("tb_phieunhap", values, selection, selectionArgs);
                    } else {
                        // Xử lý khi số lượng tồn kho không đủ để xuất hàng
                        // Ví dụ: hiển thị thông báo lỗi hoặc thực hiện các hành động khác
                    }
                } else {
                    // Xử lý khi cột "SoLuong" không tồn tại trong bảng
                    // Bạn có thể ghi log lỗi hoặc hiển thị thông báo
                }
            }

            if (cursor != null) {
                cursor.close();
            }
        } finally {
            db.close();
        }
    }



}
