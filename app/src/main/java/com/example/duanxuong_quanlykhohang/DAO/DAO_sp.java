package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
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
        values.put(" TenSP", SP.getTenSP());
        values.put("MoTa", SP.getMota());
        values.put("Gia", SP.getGia());
        values.put("SoLuong ", SP.getSoLuong());
        values.put("NgayLuu", SP.getNgayluu());
        values.put("NgayXuat", SP.getNgayxuat());
        return db.insert("tb_SanPham", null, values);
    }

    public int DeleteRow(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("MaSP", SP.getMaSP());
        values.put("MaLoai", SP.getMaLoai());
        values.put("MaND", SP.getMaND());
        values.put(" TenSP", SP.getTenSP());
        values.put("MoTa", SP.getMota());
        values.put("Gia", SP.getGia());
        values.put("SoLuong ", SP.getSoLuong());
        values.put("NgayLuu", SP.getNgayluu());
        values.put("NgayXuat", SP.getNgayxuat());
        String[] index = new String[]{
                String.valueOf(SP.getMaSP())

        };
        return db.delete("tb_SanPham", "MaSP=?", index);
    }
    public int Update(DTO_sp SP) {
        ContentValues values = new ContentValues();
        values.put("MaSP", SP.getMaSP());
        values.put("MaLoai", SP.getMaLoai());
        values.put("MaND", SP.getMaND());
        values.put(" TenSP", SP.getTenSP());
        values.put("MoTa", SP.getMota());
        values.put("Gia", SP.getGia());
        values.put("SoLuong ", SP.getSoLuong());
        values.put("NgayLuu", SP.getNgayluu());
        values.put("NgayXuat", SP.getNgayxuat());
        String[] index = new String[]{
                String.valueOf(SP.getMaLoai())
        };
        return db.update("tb_SanPham", values, "MaSP=?", index);
    }
    public List<DTO_sp> getAll(){
        List<DTO_sp> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_SanPham INNER JOIN tb_loaihang on tb_SanPham.MaLoai = tb_loaihang.MaLoai",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                String MaSP = c.getString(0);
                int MaLoai  = c.getInt(1);
                int MaND  = c.getInt(2);
                String TenSP = c.getString(3);
                byte[] MoTa= c.getBlob(4);
                int Gia =c.getInt(5);
                int SoLuong =c.getInt(6);
                String NgayLuu =c.getString(7);
                String NgayXuat =c.getString(8);
                String tenLoai = c.getString(10);


                list.add(new DTO_sp(MaSP,MaLoai,tenLoai,MaND,TenSP,MoTa,Gia,SoLuong,NgayLuu,NgayXuat));
            }while (c.moveToNext());
        }
        return list;
    };


}
