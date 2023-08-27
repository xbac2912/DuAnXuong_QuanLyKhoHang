package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;

import java.util.List;

public class Adapter_ThongKetonkho extends RecyclerView.Adapter<Adapter_ThongKetonkho.ViewHolder> {
    Context context;
    List<DTO_sp_Phieu_Nhap> list;

    public Adapter_ThongKetonkho(Context context, List<DTO_sp_Phieu_Nhap> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_tonkho_thongke,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblSoPhieu.setText(list.get(position).getMaPhieuNhap()+"");
        holder.lblTenSp.setText(list.get(position).getTenSP());
        holder.lblNgaynhap.setText(list.get(position).getNgayNhap());
        holder.lblSoLuong.setText(list.get(position).getSoLuong()+"");
        holder.anhMoTa.setImageURI(list.get(position).hienthi(context));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblSoPhieu, lblTenSp, lblNgaynhap, lblGia, lblSoLuong;
        ImageView anhMoTa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblSoPhieu = itemView.findViewById(R.id.lbl_id_tk_tk);
            lblTenSp = itemView.findViewById(R.id.lbl_name_tk_tk);
            lblNgaynhap = itemView.findViewById(R.id.lbl_date_in_tk_tk);
            lblSoLuong = itemView.findViewById(R.id.lbl_sl_tk_tk);
            lblGia = itemView.findViewById(R.id.lbl_price_tk_tk);
            anhMoTa = itemView.findViewById(R.id.imv_imgtktk_show);
        }
    }
}
