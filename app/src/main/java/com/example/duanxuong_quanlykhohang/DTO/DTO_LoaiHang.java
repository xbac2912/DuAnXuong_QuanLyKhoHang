package com.example.duanxuong_quanlykhohang.DTO;

import java.io.Serializable;

public class DTO_LoaiHang implements Serializable {
    private int id;
    private String tenLoai;

    public DTO_LoaiHang(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
