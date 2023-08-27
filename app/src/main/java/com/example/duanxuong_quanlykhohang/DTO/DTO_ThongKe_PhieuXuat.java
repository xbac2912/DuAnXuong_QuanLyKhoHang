package com.example.duanxuong_quanlykhohang.DTO;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;

public class DTO_ThongKe_PhieuXuat {
    private String maSp;
    private String tenSp;
    private String ngayXuat;

    private int soLuong;
    private byte[] anh;

    public DTO_ThongKe_PhieuXuat() {
    }

    public DTO_ThongKe_PhieuXuat(String maSp, String tenSp, String ngayXuat, int soLuong, byte[] anh) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;
        this.soLuong = soLuong;
        this.anh = anh;
    }

    public DTO_ThongKe_PhieuXuat(String maSp, String tenSp, String ngayXuat, int soLuong) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.ngayXuat = ngayXuat;

        this.soLuong = soLuong;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
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


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public Uri hienthi(Context context) {

        byte[] imageData =getAnh();// Mảng byte chứa dữ liệu hình ảnh
        String tempFileName = "temp_image.jpg";
        Uri uri;

// Tạo đường dẫn tới tập tin ảnh tạm
        File tempFile = new File(context.getCacheDir(), tempFileName);

// Ghi dữ liệu blob vào tập tin ảnh tạm
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(imageData);
            fileOutputStream.close();

            uri = Uri.fromFile(tempFile);
            return uri;
        } catch (Exception e) {
            return null;
        }

    }
}
