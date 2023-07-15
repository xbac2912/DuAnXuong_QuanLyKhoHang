package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.R;

import java.util.List;

public class Adapter_sp extends RecyclerView.Adapter<Adapter_sp.ViewHolder>{

    Context context;
    List<DTO_User> list;

    public Adapter_sp(Context context, List<DTO_User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
EditText id_sp , ten_sp , soLuong,ngayNhap,theLoai , gia;
ImageButton xoa,update;

ImageView anh ;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           id_sp = itemView.findViewById(R.id.lbl_id_sp);
           ten_sp =  itemView.findViewById(R.id.lbl_name_sp);
           soLuong =  itemView.findViewById(R.id.lbl_sl_sp);
           ngayNhap =  itemView.findViewById(R.id.lbl_date_add_sp);
           theLoai =  itemView.findViewById(R.id.lbl_ten_loai_sp);
           gia =  itemView.findViewById(R.id.lbl_price_sp);
           xoa =  itemView.findViewById(R.id.ibtn_delete_sp);
           update =  itemView.findViewById(R.id.ibtn_update_sp);
           anh = itemView.findViewById(R.id.imv_imgsp_show);

       }
   }
}
