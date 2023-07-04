package com.example.duanxuong_quanlykhohang.DTO;

public class PhieuXuat {
    private int maPhieu;
    private String ngayXuat;
    private int maSanPham;
    private int soLuong;

    public PhieuXuat(int maPhieu, String ngayXuat, int maSanPham, int soLuong) {
        this.maPhieu = maPhieu;
        this.ngayXuat = ngayXuat;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
