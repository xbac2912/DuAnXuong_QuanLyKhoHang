package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_sp {

    private String MaSP;
    private int MaLoai;
    private  int MaND;
    private  String TenSP;
    private  byte[] Mota;
    private int Gia;
    private int SoLuong;
    private String Ngayluu;
private  String Ngayxuat;

    public DTO_sp(String maSP, int maLoai, String maND, String tenSP, String moTa, int gia, int soLuong, String ngayLuu, String ngayXuat) {
    }

    public DTO_sp(String maSP, int maLoai, int maND, String tenSP, byte[] mota, int gia, int soLuong, String ngayluu, String ngayxuat) {
        MaSP = maSP;
        MaLoai = maLoai;
        MaND = maND;
        TenSP = tenSP;
        Mota = mota;
        Gia = gia;
        SoLuong = soLuong;
        Ngayluu = ngayluu;
        Ngayxuat = ngayxuat;
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

    public String getNgayluu() {
        return Ngayluu;
    }

    public void setNgayluu(String ngayluu) {
        Ngayluu = ngayluu;
    }

    public String getNgayxuat() {
        return Ngayxuat;
    }

    public void setNgayxuat(String ngayxuat) {
        Ngayxuat = ngayxuat;
    }
}
