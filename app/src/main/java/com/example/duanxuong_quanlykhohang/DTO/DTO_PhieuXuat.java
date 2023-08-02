package com.example.duanxuong_quanlykhohang.DTO;

import java.io.Serializable;

public class DTO_PhieuXuat implements Serializable {
    private int maPhieu;
    private String maSp;
    private String tenSp;
    private String ngayXuat;
    private int gia;
    private int soLuong;

    public DTO_PhieuXuat() {
    }

    public DTO_PhieuXuat(int maPhieu, String maSp, String tenSp, String ngayXuat, int gia, int soLuong) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.gia = gia;
        this.soLuong = soLuong;
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
}
