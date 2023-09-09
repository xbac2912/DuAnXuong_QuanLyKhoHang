package com.example.duanxuong_quanlykhohang.DTO;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

public class DTO_PhieuXuat implements Serializable {
    private int maPhieu;
    private String maSp;
    private String tenSp;
    private String ngayXuat;
    private int gia;
    private int soLuong;
    private boolean daXuatKho;
    private byte[] anh;
    private int giaPN;
    public DTO_PhieuXuat() {
    }

    public DTO_PhieuXuat(String maSp, String tenSp, String ngayXuat, int soLuong, boolean daXuatKho, byte[] anh) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.soLuong = soLuong;
        this.daXuatKho = daXuatKho;
        this.anh = anh;
    }


    public DTO_PhieuXuat(int maPhieu, String maSp, String tenSp, String ngayXuat, int gia, int soLuong, boolean daXuatKho) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.gia = gia;
        this.soLuong = soLuong;
        this.daXuatKho = daXuatKho;
    }

    public DTO_PhieuXuat(int maPhieu, String maSp, String tenSp, String ngayXuat, int gia, int soLuong, boolean daXuatKho, byte[] anh, int giaPN) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.gia = gia;
        this.soLuong = soLuong;
        this.daXuatKho = daXuatKho;
        this.anh = anh;
        this.giaPN = giaPN;
    }

    public DTO_PhieuXuat(int maPhieu, String maSp, String tenSp, String ngayXuat, int gia, int soLuong, int giaPN) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.gia = gia;
        this.soLuong = soLuong;
        this.giaPN = giaPN;
    }

    public int getGiaPN() {
        return giaPN;
    }

    public void setGiaPN(int giaPN) {
        this.giaPN = giaPN;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isDaXuatKho() {
        return daXuatKho;
    }

    public void setDaXuatKho(boolean daXuatKho) {
        this.daXuatKho = daXuatKho;
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
