package com.example.duanxuong_quanlykhohang.DTO;

public class DTO_ThongKe_PhieuXuat {
    private String maSp;
    private String tenSp;
    private String ngayXuat;
    private int gia;
    private int soLuong;

    public DTO_ThongKe_PhieuXuat() {
    }

    public DTO_ThongKe_PhieuXuat(String maSp, String tenSp, String ngayXuat, int gia, int soLuong) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.gia = gia;
        this.soLuong = soLuong;
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
