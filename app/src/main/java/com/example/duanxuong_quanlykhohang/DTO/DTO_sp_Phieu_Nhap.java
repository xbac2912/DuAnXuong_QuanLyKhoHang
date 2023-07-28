package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_sp_Phieu_Nhap {
    private String MaPhieuNhap;
    private int MaSanPham;
    private int Gia;
    private int SoLuong;
    private String NgayNhap;

    public DTO_sp_Phieu_Nhap() {
    }

    public DTO_sp_Phieu_Nhap(String maPhieuNhap, int maSanPham, int gia, int soLuong, String ngayNhap) {
        MaPhieuNhap = maPhieuNhap;
        MaSanPham = maSanPham;
        Gia = gia;
        SoLuong = soLuong;
        NgayNhap = ngayNhap;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
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
