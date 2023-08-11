package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.R;

import java.util.ArrayList;

public class Adapter_khohang extends RecyclerView.Adapter<Adapter_khohang.ViewHolder>{
    private final ArrayList<DTO_KhoHang> list;
    private final Context context;
    DAO_khohang dao_khohang;

    public Adapter_khohang(ArrayList<DTO_KhoHang> list, Context context) {
        this.list = list;
        this.context = context;
        dao_khohang = new DAO_khohang(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quanlykhohang, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblMaSP.setText(String.valueOf(list.get(position).getMaSP()));
        holder.lblTenSP.setText(list.get(position).getTenSP());
        holder.lblGia.setText(String.valueOf(list.get(position).getGiaSP()));
        holder.lblSoLuong.setText(String.valueOf(list.get(position).getSoluong()));
        holder.lblTenLoai.setText(list.get(position).getTenLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblMaSP, lblTenSP, lblGia, lblSoLuong, lblTenLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaSP = itemView.findViewById(R.id.lbl_ma_spkho);
            lblTenSP = itemView.findViewById(R.id.lbl_name_sp_kho);
            lblGia = itemView.findViewById(R.id.lbl_gia_nhapkho);
            lblSoLuong = itemView.findViewById(R.id.lbl_so_luong);
            lblTenLoai = itemView.findViewById(R.id.lbl_Loaisp_kho);
        }
    }
}