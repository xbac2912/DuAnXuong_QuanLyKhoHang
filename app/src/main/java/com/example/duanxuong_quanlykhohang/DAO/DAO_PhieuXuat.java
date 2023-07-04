package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong_quanlykhohang.DTO.PhieuXuat;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;


import java.util.ArrayList;

public class DAO_PhieuXuat {
    private DBHelper dbHelper;
    public DAO_PhieuXuat (Context context) {
        dbHelper = new DBHelper(context);
    }
    public void themPhieuXuat(int maSanPham, int soLuong, String ngayXuat) {
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
        } finally {
            db.close();
        }
    }
    public ArrayList<PhieuXuat> layDanhSachPhieuXuat() {
        ArrayList<PhieuXuat> danhSachPhieuXuat = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT pm.SoPhieu, pm.NgayXuat, c.MaSP, c.SoLuong FROM tb_phieuxuat pm" +
                " INNER JOIN tb_CTPhieuxuat c ON pm.SoPhieu = c.SoPhieu";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int soPhieuIndex = cursor.getColumnIndex("SoPhieu");
            int ngayXuatIndex = cursor.getColumnIndex("NgayXuat");
            int maSanPhamIndex = cursor.getColumnIndex("MaSP");
            int soLuongIndex = cursor.getColumnIndex("SoLuong");

            if (soPhieuIndex != -1 && ngayXuatIndex != -1 && maSanPhamIndex != -1 && soLuongIndex != -1) {
                do {
                    int soPhieu = cursor.getInt(soPhieuIndex);
                    String ngayXuat = cursor.getString(ngayXuatIndex);
                    int maSanPham = cursor.getInt(maSanPhamIndex);
                    int soLuong = cursor.getInt(soLuongIndex);

                    PhieuXuat phieuXuat = new PhieuXuat(soPhieu, ngayXuat, maSanPham, soLuong);
                    danhSachPhieuXuat.add(phieuXuat);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        return danhSachPhieuXuat;
    }

    public void suaPhieuXuat(int maPhieu, int maSanPham, int soLuong, String ngayXuat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues phieuXuatValues = new ContentValues();
            phieuXuatValues.put("NgayXuat", ngayXuat);

            String whereClause = "SoPhieu = ?";
            String[] whereArgs = { String.valueOf(maPhieu) };

            db.update("tb_phieuxuat", phieuXuatValues, whereClause, whereArgs);

            ContentValues ctPhieuXuatValues = new ContentValues();
            ctPhieuXuatValues.put("MaSP", maSanPham);
            ctPhieuXuatValues.put("Soluong", soLuong);

            whereClause = "SoPhieu = ?";
            whereArgs = new String[] { String.valueOf(maPhieu) };

            db.update("tb_CTPhieuxuat", ctPhieuXuatValues, whereClause, whereArgs);
        } finally {
            db.close();
        }
    }

    public void xoaPhieuXuat(int maPhieu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String whereClause = "SoPhieu = ?";
            String[] whereArgs = { String.valueOf(maPhieu) };

            db.delete("tb_phieuxuat", whereClause, whereArgs);
            db.delete("tb_CTPhieuxuat", whereClause, whereArgs);
        } finally {
            db.close();
        }
    }

}
