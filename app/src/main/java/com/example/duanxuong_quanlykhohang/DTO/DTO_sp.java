package com.example.duanxuong_quanlykhohang.DTO;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;

public class DTO_sp {

    private String MaSP;
    private int MaLoai;
    private String tenLoai;
    private int MaND;
    private String TenSP;
    private byte[] Mota;
    private int trangthai;

    private int soLuongTon;
//    private int Gia;
//    private int SoLuong;
//    private String Ngayluu;
//    private String Ngayxuat;


    public DTO_sp(String maSP, int maLoai, String tenLoai, int maND, String tenSP, byte[] mota) {
        MaSP = maSP;
        MaLoai = maLoai;
        this.tenLoai = tenLoai;
        MaND = maND;
        TenSP = tenSP;
        Mota = mota;

    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public DTO_sp(String maSP, String tenLoai , int maLoai, int maND, String tenSP) {
        MaSP = maSP;

        this.tenLoai = tenLoai;
        this.MaLoai = maLoai;
        MaND = maND;
        TenSP = tenSP;



    }

    public DTO_sp(String maSP, int maLoai, String tenLoai, int maND, String tenSP, byte[] mota, int trangthai) {
        MaSP = maSP;
        MaLoai = maLoai;
        this.tenLoai = tenLoai;
        MaND = maND;
        TenSP = tenSP;
        Mota = mota;
        this.trangthai = soLuongTon;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public DTO_sp() {
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public DTO_sp(String maSP, int maLoai, String maND, String tenSP, String moTa, int gia, int soLuong, String ngayLuu, String ngayXuat) {
    }

    public DTO_sp(String maSP, int maLoai, int maND, String tenSP, byte[] mota) {
        MaSP = maSP;
        MaLoai = maLoai;
        MaND = maND;
        TenSP = tenSP;
        Mota = mota;


    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public byte[] getMota() {
        return Mota;
    }

    public void setMota(byte[] mota) {
        Mota = mota;
    }


    public Uri hienthi(Context context) {

        byte[] imageData =getMota();// Mảng byte chứa dữ liệu hình ảnh
        String tempFileName = "temp_image.jpg";
        Uri uri;

// Tạo đường dẫn tới tập tin ảnh tạm
        File tempFile = new File(context.getCacheDir(), tempFileName);

// Ghi dữ liệu blob vào tập tin ảnh tạm
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(imageData);
            fileOutputStream.close();

            uri = Uri.fromFile(tempFile);
            return uri;
        } catch (Exception e) {
            return null;
        }

    }
}
