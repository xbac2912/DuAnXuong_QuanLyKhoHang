package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_KhoHang {
    private int maSP;
    private String tenSP;
    private int giaSP;
    private int soluong;
    private String tenLoai;

    public DTO_KhoHang(int maSP, String tenSP, int giaSP, int soluong, String tenLoai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soluong = soluong;
        this.tenLoai = tenLoai;
    }

    public DTO_KhoHang(int maSP, int giaSP, int soluong, String tenLoai) {
        this.maSP = maSP;
        this.giaSP = giaSP;
        this.soluong = soluong;
        this.tenLoai = tenLoai;
    }

    public DTO_KhoHang() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
