package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;


import java.util.ArrayList;

public class DAO_PhieuXuat {
    private DBHelper dbHelper;
    private Context context;


    public DAO_PhieuXuat(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
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
            ctPhieuXuatValues.put("maPhieunhap",maSanPham);

            db.insert("tb_CTPhieuxuat", null, ctPhieuXuatValues);
            capNhatSoLuongTonSanPham(maSanPham,soLuong);
        } finally {
            db.close();
        }
    }


    public ArrayList<DTO_PhieuXuat> layDanhSachPhieuXuat() {
        ArrayList<DTO_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * from tb_CTphieuxuat  INNER JOIN tb_phieuxuat on tb_CTPhieuxuat.SoPhieu = tb_phieuxuat.SoPhieu INNER JOIN tb_SanPham on tb_CTPhieuxuat.MaSP = tb_SanPham.MaSP INNER JOIN tb_phieunhap on tb_CTPhieuxuat.MaSP = tb_phieunhap.maSP";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                do {
                    int soPhieu = cursor.getInt(0);
                    String maSanPham = cursor.getString(1);
                    String tenSanPham = cursor.getString(10);
                    String ngayXuat = cursor.getString(5);
                    int giaSanPham = cursor.getInt(17);
                    int soLuong = cursor.getInt(3);
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

    public ArrayList<DTO_PhieuXuat> layDanhSachPhieuXuat_daxuat() {
        ArrayList<DTO_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * from tb_CTphieuxuat  INNER JOIN tb_phieuxuat on tb_CTPhieuxuat.SoPhieu = tb_phieuxuat.SoPhieu INNER JOIN tb_SanPham on tb_CTPhieuxuat.MaSP = tb_SanPham.MaSP INNER JOIN tb_phieunhap on tb_CTPhieuxuat.MaSP = tb_phieunhap.maSP";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                do {
                    int soPhieu = cursor.getInt(0);
                    String maSanPham = cursor.getString(1);
                    String tenSanPham = cursor.getString(10);
                    String ngayXuat = cursor.getString(5);
                    int giaSanPham = cursor.getInt(17);
                    int soLuong = cursor.getInt(3);
                    int daXuatHang = cursor.getInt(6); // Lấy trạng thái checkbox

                    boolean isChecked = (daXuatHang == 1); // Chuyển đổi giá trị thành trạng thái checkbox
                    DTO_PhieuXuat phieuXuat = new DTO_PhieuXuat(soPhieu, maSanPham, tenSanPham, ngayXuat, giaSanPham, soLuong);
                    phieuXuat.setDaXuatKho(isChecked); // Đặt trạng thái checkbox vào đối tượng DTO_PhieuXuat
                    if(isChecked){
                        danhSachPhieuXuat.add(phieuXuat);
                    }
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }

        db.close();
        return danhSachPhieuXuat;
    }
    public ArrayList<DTO_PhieuXuat> layDanhSachPhieuXuatTK(String tuNgay, String denNgay) {
        ArrayList<DTO_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String locSP="select * from tb_phieuxuat INNER JOIN tb_CTPhieuxuat on tb_phieuxuat.SoPhieu = tb_CTPhieuxuat.SoPhieu INNER JOIN tb_SanPham on tb_CTPhieuxuat.MaSP = tb_SanPham.MaSP where tb_phieuxuat.NgayXuat BETWEEN "+"'"+ tuNgay +"' and '"+denNgay+"'";

        Cursor cursor = db.rawQuery(locSP,null);

        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            try {
                do {
                    String maSanPham = cursor.getString(4);
                    String tenSanPham = cursor.getString(9);
                    String ngayXuat = cursor.getString(1);
                    int soLuong = cursor.getInt(5);
                    byte[] anh = cursor.getBlob(10);
                    int trangthai = cursor.getInt(12);
                    boolean a = trangthai==1;
                    DTO_PhieuXuat phieuXuatTK = new DTO_PhieuXuat(maSanPham, tenSanPham, ngayXuat, soLuong,a,anh);
                   if(a){
                       danhSachPhieuXuat.add(phieuXuatTK);
                   }
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }

        db.close();
        return danhSachPhieuXuat;
    }

    public void capNhatSoLuongTonSanPham(String maSanPham, int soLuongXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Lấy thông tin số lượng tồn của sản phẩm trong bảng tb_SanPham
            String[] columns = {"soLuong"};
            String selection = "MaSp = ?";
            String[] selectionArgs = {String.valueOf(maSanPham)};
            Cursor cursor = db.query("tb_khohang", columns, selection, selectionArgs, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int soLuongTonIndex = cursor.getColumnIndex("soLuong");
                    if (soLuongTonIndex != -1) {
                        int soLuongTonHienTai = cursor.getInt(soLuongTonIndex);

                        // Kiểm tra xem có đủ số lượng tồn để xuất hàng không
                        if (soLuongTonHienTai >= soLuongXuat) {
                            // Cập nhật số lượng tồn của sản phẩm (số lượng hiện tại - số lượng xuất)
                            int soLuongMoi = soLuongTonHienTai - soLuongXuat;
                            ContentValues values = new ContentValues();
                            values.put("soLuong", soLuongMoi);

                            // Cập nhật thông tin số lượng tồn của sản phẩm trong bảng tb_SanPham
                            db.update("tb_khohang", values, selection, selectionArgs);
                        } else {
                            // Xử lý khi số lượng tồn không đủ để xuất hàng
                            Toast.makeText(context, "Số lượng tồn kho không đủ để xuất!", Toast.LENGTH_SHORT).show();
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
        Cursor cursor = null;

        try {
            // Cập nhật thông tin phiếu xuất
            ContentValues phieuXuatValues = new ContentValues();
            phieuXuatValues.put("NgayXuat", ngayXuat);
            phieuXuatValues.put("DaXuatKho", daXuatKho ? 1 : 0);

            String whereClausePhieu = "SoPhieu = ?";
            String[] whereArgsPhieu = {String.valueOf(maPhieu)};
            db.update("tb_phieuxuat", phieuXuatValues, whereClausePhieu, whereArgsPhieu);

            // Cập nhật thông tin số lượng phiếu xuất
            ContentValues ctPhieuXuatValues = new ContentValues();
            ctPhieuXuatValues.put("Soluong", soLuongMoi);

            String whereClauseCTPhieu = "SoPhieu = ?";
            String[] whereArgsCTPhieu = {String.valueOf(maPhieu)};
            db.update("tb_CTPhieuxuat", ctPhieuXuatValues, whereClauseCTPhieu, whereArgsCTPhieu);

            // Cập nhật trạng thái checkbox trong bảng tb_phieuxuat
            ContentValues checkBoxValues = new ContentValues();
            checkBoxValues.put("DaXuatKho", daXuatKho ? 1 : 0);

            db.update("tb_phieuxuat", checkBoxValues, whereClausePhieu, whereArgsPhieu);
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi sửa phiếu xuất: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
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
