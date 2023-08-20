package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_KhoHang {
    private String maSP;
    private String tenSP;
    private int giaSP;
    private int soluong;
    private String tenLoai;
    private int maloai;
    private byte[] anh;



    public DTO_KhoHang(String maSP, String tenSP, int giaSP, int soluong, int maloai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soluong = soluong;
        this.maloai = maloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public DTO_KhoHang(String maSP, int giaSP, int soluong, int maloai) {
        this.maSP = maSP;
        this.giaSP = giaSP;
        this.soluong = soluong;
        this.maloai = maloai;
    }

    public DTO_KhoHang() {
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
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
