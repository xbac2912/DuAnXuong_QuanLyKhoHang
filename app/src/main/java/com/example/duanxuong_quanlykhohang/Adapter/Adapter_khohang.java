package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter_khohang extends RecyclerView.Adapter<Adapter_khohang.ViewHolder> implements Filterable {
    private ArrayList<DTO_KhoHang> list;
    private final Context context;
    DAO_khohang dao_khohang;
    DAO_sp dao_sp;
    ArrayList<DTO_KhoHang> listold;

    public Adapter_khohang(ArrayList<DTO_KhoHang> list, Context context) {
        this.list = list;
        this.context = context;
        dao_khohang = new DAO_khohang(context);
        dao_sp = new DAO_sp(context);
        listold=list;
    }
    public void setList(ArrayList<DTO_KhoHang> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
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
        holder.anh.setImageURI(list.get(position).hienthi(context));
    }
    public Uri hienthi(int p) {
        byte[] imageData = list.get(p).getAnh();// Mảng byte chứa dữ liệu hình ảnh
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
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.toString().isEmpty()) {
                    list = listold;
                } else {
                    List<DTO_KhoHang> listnew = new ArrayList<>();
                    for (DTO_KhoHang task : listold
                    ) {
                        if (task.getTenSP().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            listnew.add(task);
                        }
                    }
                    list = (ArrayList<DTO_KhoHang>) listnew;
                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<DTO_KhoHang>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblMaSP, lblTenSP, lblGia, lblSoLuong, lblTenLoai;
        ImageView anh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaSP = itemView.findViewById(R.id.lbl_ma_spkho);
            lblTenSP = itemView.findViewById(R.id.lbl_name_sp_kho);
            lblGia = itemView.findViewById(R.id.lbl_gia_nhapkho);
            lblSoLuong = itemView.findViewById(R.id.lbl_so_luong);
            lblTenLoai = itemView.findViewById(R.id.lbl_Loaisp_kho);
            anh = itemView.findViewById(R.id.imv_imgsp_show);
        }
    }
}
