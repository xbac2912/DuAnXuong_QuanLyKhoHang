package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_sp_Phieu_Nhap {
    private int MaPhieuNhap;
    private String MaSanPham;
    private int Gia;
    private int SoLuong;
    private String NgayNhap;

    public DTO_sp_Phieu_Nhap() {
    }

    public DTO_sp_Phieu_Nhap(int maPhieuNhap, String maSanPham, int gia, int soLuong, String ngayNhap) {
        MaPhieuNhap = maPhieuNhap;
        MaSanPham = maSanPham;
        Gia = gia;
        SoLuong = soLuong;
        NgayNhap = ngayNhap;
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

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }
}
