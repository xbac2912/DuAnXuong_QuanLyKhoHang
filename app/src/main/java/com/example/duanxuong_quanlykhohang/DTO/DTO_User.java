package com.example.duanxuong_quanlykhohang.DTO;

import java.io.Serializable;

public class DTO_User implements Serializable {
    private int maND;
    private String hoTen;
    private String nguoiDung;
    private String matKhau;
    private int vaiTro;
    private String oldPass;

    public DTO_User() {
    }

    public DTO_User(int maND, String hoTen, String nguoiDung, String matKhau, int vaiTro, String oldPass) {
        this.maND = maND;
        this.hoTen = hoTen;
        this.nguoiDung = nguoiDung;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.oldPass = oldPass;
    }

    public DTO_User(int maND, String hoTen, String nguoiDung, String matKhau, int vaiTro) {
        this.maND = maND;
        this.hoTen = hoTen;
        this.nguoiDung = nguoiDung;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }
}
