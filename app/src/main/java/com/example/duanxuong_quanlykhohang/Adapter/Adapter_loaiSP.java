package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.R;

import java.util.List;

public class Adapter_loaiSP extends BaseAdapter {
    Context context;
    List<DTO_LoaiHang> list;

    public Adapter_loaiSP(Context context, List<DTO_LoaiHang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_list_loai_sp, parent, false);

        TextView id = convertView.findViewById(R.id.id_loai_hang);
        TextView ten = convertView.findViewById(R.id.ten_loai_hang);

        DTO_LoaiHang loaiHang = list.get(position);
        id.setText(loaiHang.getId() + "");
        ten.setText(loaiHang.getTenLoai());
        return convertView;
    }
}
