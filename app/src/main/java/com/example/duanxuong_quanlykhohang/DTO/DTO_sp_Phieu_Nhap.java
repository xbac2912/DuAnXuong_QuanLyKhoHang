package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_sp_Phieu_Nhap {
    private int MaPhieuNhap;
    private String MaSanPham;
    private String TenSP;
    private String NgayNhap;
    private int Gia;
    private int SoLuong;


    public DTO_sp_Phieu_Nhap() {
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


}
