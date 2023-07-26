package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_sp {

    private String MaSP;
    private int MaLoai;
    private String tenLoai;
    private int MaND;
    private String TenSP;
    private byte[] Mota;
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

    public DTO_sp(String maSP, String tenLoai ,int maLoai, int maND, String tenSP) {
        MaSP = maSP;

        this.tenLoai = tenLoai;
        this.MaLoai = maLoai;
        MaND = maND;
        TenSP = tenSP;



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

}
