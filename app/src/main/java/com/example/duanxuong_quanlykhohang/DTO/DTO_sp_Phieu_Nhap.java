package com.example.duanxuong_quanlykhohang.DTO;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;

public class DTO_sp_Phieu_Nhap {
    private int MaPhieuNhap;
    private String MaSanPham;
    private String TenSP;
    private String NgayNhap;
    private int Gia;
    private int SoLuong;
    private byte[] anh;



    public DTO_sp_Phieu_Nhap() {
    }

    public DTO_sp_Phieu_Nhap(int maPhieuNhap, String tenSP, String ngayNhap, int gia, int soLuong,byte[] anh) {
        MaPhieuNhap = maPhieuNhap;
        TenSP = tenSP;
        NgayNhap = ngayNhap;
        Gia = gia;
        SoLuong = soLuong;
        this.anh = anh;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public DTO_sp_Phieu_Nhap(int maPhieuNhap, String maSanPham, String tenSP, String ngayNhap, int gia, int soLuong) {
        MaPhieuNhap = maPhieuNhap;
        MaSanPham = maSanPham;
        TenSP = tenSP;
        NgayNhap = ngayNhap;
        Gia = gia;
        SoLuong = soLuong;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }
    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Uri hienthi(Context context) {

        byte[] imageData =getAnh();// Mảng byte chứa dữ liệu hình ảnh
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
