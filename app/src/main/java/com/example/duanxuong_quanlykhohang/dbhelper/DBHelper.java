package com.example.duanxuong_quanlykhohang.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static String DB_NAME = "quanlykhohang";
    static int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_User = "CREATE TABLE tb_User (     MaND     INTEGER PRIMARY KEY AUTOINCREMENT                      UNIQUE                      NOT NULL,     HoTen    TEXT    NOT NULL,     TaiKhoan TEXT    UNIQUE                      NOT NULL,     Matkhau  TEXT    NOT NULL,     VaiTro   INTEGER NOT NULL,     passOld TEXT);";
        String tb_loai = "CREATE TABLE tb_loaihang (     MaLoai  INTEGER PRIMARY KEY AUTOINCREMENT                     UNIQUE                     NOT NULL,     TenLoai TEXT    UNIQUE                     NOT NULL );";
        String tb_sanPham= "CREATE TABLE tb_SanPham (     MaSP   TEXT    PRIMARY KEY                    UNIQUE                    NOT NULL,     MaLoai INTEGER REFERENCES tb_loaihang (MaLoai) ON DELETE CASCADE                                                    ON UPDATE CASCADE                    NOT NULL,     MaND   INTEGER REFERENCES tb_User (MaND) ON DELETE CASCADE                                              ON UPDATE CASCADE                    NOT NULL,     TenSP    UNIQUE       NOT NULL,     MoTa   BLOB ,soLuongTon INTEGER DEFAULT (0) )";
        String tb_PhieuXuat ="CREATE TABLE tb_phieuxuat (     SoPhieu  INTEGER PRIMARY KEY AUTOINCREMENT                      UNIQUE                      NOT NULL,     NgayXuat TEXT    NOT NULL, DaXuatKho INTEGER DEFAULT (0) );";
        String tb_CTPhieuxuat ="CREATE TABLE tb_CTPhieuxuat (     SoPhieu INTEGER NOT NULL,     MaSP    TEXT NOT NULL,     Soluong INTEGER CHECK (Soluong > 0)                     NOT NULL,     PRIMARY KEY (         SoPhieu,         MaSP     ) );";
        String tb_phieuNhap= "CREATE TABLE tb_phieunhap (     sophieu  INTEGER PRIMARY KEY AUTOINCREMENT                      UNIQUE,     maSP  TEXT           REFERENCES tb_SanPham (MaSP) ON DELETE CASCADE                                                   ON UPDATE CASCADE,     ngaynhap TEXT,     gia      INTEGER,     soLuong INTEGER )";
        String tb_khohang=  "CREATE TABLE tb_khohang (MaSp TEXT REFERENCES tb_sanPham (MaSP) ON DELETE CASCADE, GiaSp INTEGER REFERENCES tb_phieunhap (gia) ON DELETE CASCADE, soLuong INTEGER REFERENCES tb_phieunhap (soLuong) ON DELETE CASCADE, tenLoai TEXT)";
        String addAdmin = "INSERT INTO tb_User(HoTen,TaiKhoan,Matkhau,VaiTro,passOld) VALUES('Quàng Ngọc Thủy','admin','admin',1,'admin')";

        db.execSQL(tb_User);
        db.execSQL(tb_loai);
        db.execSQL(tb_sanPham);
        db.execSQL(tb_PhieuXuat);
        db.execSQL(tb_CTPhieuxuat);
        db.execSQL(tb_phieuNhap);
        db.execSQL(tb_khohang);
        db.execSQL(addAdmin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table tb_User");
        db.execSQL("Drop table tb_loaihang");
        db.execSQL("Drop table tb_SanPham");
        db.execSQL("Drop table tb_phieuxuat");
        db.execSQL("Drop table tb_CTPhieuxuat");
    }
}
