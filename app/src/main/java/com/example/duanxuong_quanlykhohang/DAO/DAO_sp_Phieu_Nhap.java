package com.example.duanxuong_quanlykhohang.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_sp_Phieu_Nhap {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public DAO_sp_Phieu_Nhap(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
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
                byte[] anh = c.getBlob(9);

                list.add(new DTO_sp_Phieu_Nhap(MaPhieuNhap,MaSanPham,tenSP,NgayNhap,Gia,SoLuong,anh));
            }while (c.moveToNext());
        }
        return list;
    };
    public List<DTO_sp_Phieu_Nhap> getthongke(String tuNgay,String denNgay){
        List<DTO_sp_Phieu_Nhap> list = new ArrayList<>();
        Log.d(TAG, "getthongke: đang chạy phần đầu");
        Cursor c = db.rawQuery("select * from tb_phieunhap INNER JOIN tb_SanPham on tb_phieunhap.maSP = tb_SanPham.MaSP where tb_phieunhap.ngaynhap BETWEEN '"+tuNgay+"' and '"+denNgay+"'",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
               int maPhieu = c.getInt(0);
               String ngayNhap= c.getString(2);
               int gia = c.getInt(3);
               int soLuong = c.getInt(4);
               String ten = c.getString(8);
               byte[] anh = c.getBlob(9);
                Log.d(TAG, "getthongke: "+gia);


                list.add(new DTO_sp_Phieu_Nhap(maPhieu,ten,ngayNhap,gia,soLuong,anh));
            }while (c.moveToNext());
        }
        return list;
    };
public int xoasp_phieuNhap(DTO_sp sp){
    String[] index = new String[]{
            String.valueOf(sp.getMaSP())

    };
    return db.delete("tb_phieuNhap","maSP=?",index);
}

}
