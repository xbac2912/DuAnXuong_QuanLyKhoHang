package com.example.duanxuong_quanlykhohang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_User {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DAO_User(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }


    public long AddRow(DTO_User user){
        ContentValues values = new ContentValues();
        values.put("HoTen",user.getHoTen());
        values.put("TaiKhoan",user.getNguoiDung());
        values.put("MatKhau",user.getMatKhau());
        values.put("VaiTro",user.getVaiTro());
        return db.insert("tb_User",null,values);
    }
    public int UpdateRow(DTO_User user,Context context){

        DAO_User daoUser = new DAO_User(context);
        List<DTO_User> list = daoUser.getAll();
        int check=0;

        for (DTO_User dtoUser:list) {
            if(dtoUser.getNguoiDung().equals(user.getNguoiDung())){
                if(dtoUser.getMaND()==user.getMaND()){
                    check=1;
                    break;
                }
            }
        }
        if(check==1){
            ContentValues values = new ContentValues();
            values.put("HoTen",user.getHoTen());
            values.put("TaiKhoan",user.getNguoiDung());
            values.put("MatKhau",user.getMatKhau());
            values.put("VaiTro",user.getVaiTro());
            String [] index = new String[]{
                    String.valueOf(user.getMaND())
            };
            return db.update("tb_User",values,"MaND=?",index);
        }else {
            return 0;
        }

    }
    public void DeleteRow(DTO_User user){
//        String [] index = new String[]{
//                String.valueOf(user.getMaND())
//        };
         db.execSQL("DELETE FROM tb_User WHERE MaND LIKE "+user.getMaND());
//        return db.delete("tb_User","MaND=?",index);
    }

    public List<DTO_User> getAll(){
        List<DTO_User> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_User",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                int maND = c.getInt(0);
                String tenND = c.getString(1);
                String taiKhoan = c.getString(2);
                String matKhau = c.getString(3);
                int chucVu= c.getInt(4);
                list.add(new DTO_User(maND,tenND,taiKhoan,matKhau,chucVu));
            }while (c.moveToNext());
        }
        return list;
    };
}
