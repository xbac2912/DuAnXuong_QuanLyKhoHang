package com.example.duanxuong_quanlykhohang.DAO;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;

import java.util.ArrayList;

public class DAO_khohang {
    private final DBHelper dbHelper;
    public DAO_khohang(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<DTO_KhoHang> selectAll() {
        ArrayList<DTO_KhoHang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM tb_khohang INNER JOIN tb_sanPham ON tb_khohang.MaSP = tb_sanPham.MaSP INNER JOIN tb_loaihang on tb_khohang.maloai = tb_loaihang.MaLoai", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    DTO_KhoHang kh = new DTO_KhoHang();
                    kh.setMaSP(cursor.getString(0));
                    kh.setGiaSP(cursor.getInt(2));
                    kh.setSoluong(cursor.getInt(3));
                    kh.setTenSP(cursor.getString(7));
                    kh.setAnh(cursor.getBlob(8));
                    kh.setTenLoai(cursor.getString(11));
                    list.add(kh);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(DTO_KhoHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSp", kh.getMaSP());
        values.put("GiaSp", kh.getGiaSP());
        values.put("soLuong", kh.getSoluong());
        values.put("maloai", kh.getMaloai());
        long row = db.insert("tb_khohang", null, values);
        return (row > 0);
    }
    public boolean UpdateSL(String soluong,String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE tb_khohang SET soLuong=? WHERE MaSp=?", new String[] {soluong, ma});
        int row = cursor.getCount();
        return row != 0 ? true : false;
    }
    public String getSL(String ma) {
        int sl = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM tb_khohang WHERE MaSp=?", new String[] {ma});
            sl = cursor.getInt(2);
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }

        return String.valueOf(sl);
    }
    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("tb_khohang", "MaSp=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
    public boolean checkkh(String ma) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_khohang WHERE MaSp=?", new String[] {ma});
        int row = cursor.getCount();
        return row != 0 ? true : false;
    }
}
