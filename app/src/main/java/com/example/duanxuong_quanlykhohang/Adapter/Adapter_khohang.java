package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter_khohang extends RecyclerView.Adapter<Adapter_khohang.ViewHolder>{
    private final ArrayList<DTO_KhoHang> list;
    private final Context context;
    DAO_khohang dao_khohang;
    DAO_sp dao_sp;
    List<DTO_sp> list2;

    public Adapter_khohang(ArrayList<DTO_KhoHang> list, Context context) {
        this.list = list;
        this.context = context;
        dao_khohang = new DAO_khohang(context);
        dao_sp = new DAO_sp(context);
        list2 = dao_sp.getAll();
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
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getMaSP());
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getTenSP());
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getGiaSP());
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getSoluong());
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getTenLoai());
        Log.e("TAG", "onBindViewHolder: "+ list.get(position).getAnh());

        holder.lblTenSP.setText(list.get(position).getTenSP());
        holder.lblGia.setText(String.valueOf(list.get(position).getGiaSP()));
        holder.lblSoLuong.setText(String.valueOf(list.get(position).getSoluong()));
        holder.lblTenLoai.setText(list.get(position).getTenLoai());
        holder.anh.setImageURI(hienthi(position));

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
