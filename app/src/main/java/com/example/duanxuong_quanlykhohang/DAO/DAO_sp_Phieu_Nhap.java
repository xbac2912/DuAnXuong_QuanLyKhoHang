package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_sp_Phieu_Nhap {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DAO_sp_Phieu_Nhap(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long ADDSanPhamPhieunhpa(DTO_sp_Phieu_Nhap SP) {
        ContentValues values = new ContentValues();
        values.put("maSP", SP.getMaSanPham());
        values.put("ngaynhap", SP.getNgayNhap());
        values.put("gia", SP.getGia());
        values.put("soLuong", SP.getSoLuong());

        return db.insert("tb_phieuNhap", null, values);
    }

    public int DeleteRow(DTO_sp_Phieu_Nhap SP) {

        String[] index = new String[]{
                String.valueOf(SP.getMaPhieuNhap())

        };
        return db.delete("tb_phieuNhap", "sophieu=?", index);
    }
    //    public void Update(DTO_sp SP) {
//        db.execSQL("UPDATE tb_SanPham set MaSP = "+"'"+ SP.getMaSP() +"'"+",MaLoai = " +SP.getMaLoai()+ ",MaND = 1,TenSP = "+"'"+SP.getTenSP()+"'"+ ",Gia = "+SP.getGia()+ ",Soluong = " +SP.getSoLuong()+",NgayLuu = "+"'"+SP.getNgayluu()+"'"+ " WHERE MaSP LIKE "+"'"+SP.getMaSP()+"'");
//    }
    public int Update(DTO_sp_Phieu_Nhap SP) {
        ContentValues values = new ContentValues();
        values.put("sophieu", SP.getMaPhieuNhap());
        values.put("maSP", SP.getMaSanPham());
        values.put("ngaynhap", SP.getNgayNhap());
        values.put("gia", SP.getGia());
        values.put("soLuong", SP.getSoLuong());
        String[] index = new String[]{
                String.valueOf(SP.getMaPhieuNhap())

        };
        return db.update("tb_phieuNhap",values,"sophieu=?",index);

    }
    public List<DTO_sp_Phieu_Nhap> getAll(){
        List<DTO_sp_Phieu_Nhap> list = new ArrayList<>();

        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM tb_phieuNhap INNER JOIN tb_sanPham on tb_phieuNhap.maSP = tb_sanPham.maSP",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                int MaPhieuNhap = c.getInt(0);
                String MaSanPham  = c.getString(1);
                String NgayNhap = c.getString(2);
                int Gia  = c.getInt(3);
                int SoLuong  = c.getInt(4);
                String tenSP = c.getString(8);


                list.add(new DTO_sp_Phieu_Nhap(MaPhieuNhap,MaSanPham,tenSP,NgayNhap,Gia,SoLuong));
            }while (c.moveToNext());
        }
        return list;
    };


}
