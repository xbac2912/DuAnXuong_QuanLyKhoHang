package com.example.duanxuong_quanlykhohang.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.R;

import java.util.List;

public class Adapter_NhanSu extends BaseAdapter {
    Context context;
    List<DTO_User> list;

    public Adapter_NhanSu(Context context, List<DTO_User> list) {
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
        return list.get(position).getMaND();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        if(convertView==null){
            view=View.inflate(context, R.layout.item_list_nhan_su,null);
        }else {
        view=convertView;
        }

        TextView ma = view.findViewById(R.id.lbl_id_ns);
        TextView ten = view.findViewById(R.id.lbl_name_ns);
        TextView tk = view.findViewById(R.id.lbl_user_ns);

        DTO_User  user = list.get(position);

        ma.setText(user.getMaND()+"");
        ten.setText(user.getHoTen());
        tk.setText(user.getNguoiDung());
        notifyDataSetChanged();

        return view;
    }
}
