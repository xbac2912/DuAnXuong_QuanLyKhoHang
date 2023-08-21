package com.example.duanxuong_quanlykhohang.DAO;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_sp {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DAO_sp(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long ADDSanPham(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("MaSP", SP.getMaSP());
        values.put("MaLoai", SP.getMaLoai());
        values.put("MaND", SP.getMaND());
        values.put("TenSP", SP.getTenSP());
        values.put("MoTa",SP.getMota());
        return db.insert("tb_SanPham", null, values);
    }

    public int DeleteRow(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("trangthai",1);

        String[] index = new String[]{
                String.valueOf(SP.getMaSP())

        };
        return db.update("tb_SanPham", values,"MaSP=?",index);
    }
    public int Deletevinhvien(DTO_sp SP) {
        String[] index = new String[]{
                String.valueOf(SP.getMaSP())

        };
        return db.delete("tb_SanPham","MaSP=?",index);
    }
    public int KhoiphucRow(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("trangthai",0);

        String[] index = new String[]{
                String.valueOf(SP.getMaSP())

        };
        return db.update("tb_SanPham", values,"MaSP=?",index);
    }
    public String getTensp(int id) {
        db = dbHelper.getReadableDatabase();
        String ten = new String();
        try {
            Cursor cursor = db.rawQuery("SELECT TenLoai FROM tb_SanPham WHERE MaSP = ?", new String[]{String.valueOf(id)});
            ten = cursor.getString(3);
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return ten;
    }
    public int getMaLoai(String id) {
        db = dbHelper.getReadableDatabase();
        int ten = 0;
        try {
            Cursor cursor = db.rawQuery("SELECT MaLoai FROM tb_SanPham WHERE MaSP = ?", new String[]{id});
            ten = cursor.getInt(0);
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return ten;
    }

    //    public void Update(DTO_sp SP) {
//        db.execSQL("UPDATE tb_SanPham set MaSP = "+"'"+ SP.getMaSP() +"'"+",MaLoai = " +SP.getMaLoai()+ ",MaND = 1,TenSP = "+"'"+SP.getTenSP()+"'"+ ",Gia = "+SP.getGia()+ ",Soluong = " +SP.getSoLuong()+",NgayLuu = "+"'"+SP.getNgayluu()+"'"+ " WHERE MaSP LIKE "+"'"+SP.getMaSP()+"'");
//    }
    public int Update(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("MaSP", SP.getMaSP());
        values.put("MaLoai", SP.getMaLoai());
        values.put("MaND", 1);
        values.put(" TenSP", SP.getTenSP());
        values.put("MoTa",SP.getMota());
        values.put("soLuongTon",SP.getSoLuongTon());
        String[] index = new String[]{
                SP.getMaSP()
        };
        return db.update("tb_SanPham", values, "MaSP=?", index);

    }

    public List<DTO_sp> getAll(int trangthai) {
        List<DTO_sp> list = new ArrayList<>();


        Cursor c = db.rawQuery("SELECT * FROM tb_SanPham INNER JOIN tb_loaihang on tb_SanPham.MaLoai = tb_loaihang.MaLoai where trangthai = "+trangthai, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String MaSP = c.getString(0);
                int MaLoai = c.getInt(1);
                int MaND = c.getInt(2);
                String TenSP = c.getString(3);
                byte[] anh = c.getBlob(4);
                int trangtha = c.getInt(6);
                String tenLoai = c.getString(8);



                list.add(new DTO_sp(MaSP,  MaLoai,tenLoai, MaND, TenSP,anh,trangtha));
            } while (c.moveToNext());
        }
        return list;
    }

    ;

    public List<DTO_sp> getAllA() {
        List<DTO_sp> list = new ArrayList<>();


        Cursor c = db.rawQuery("SELECT * FROM tb_SanPham INNER JOIN tb_loaihang on tb_SanPham.MaLoai = tb_loaihang.MaLoai", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String MaSP = c.getString(0);
                int MaLoai = c.getInt(1);
                int MaND = c.getInt(2);
                String TenSP = c.getString(3);
                byte[] anh = c.getBlob(4);
                int trangtha = c.getInt(6);
                String tenLoai = c.getString(8);



                list.add(new DTO_sp(MaSP,  MaLoai,tenLoai, MaND, TenSP,anh,trangtha));
            } while (c.moveToNext());
        }
        return list;
    }


}
