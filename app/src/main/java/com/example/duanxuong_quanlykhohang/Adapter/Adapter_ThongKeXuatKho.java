package com.example.duanxuong_quanlykhohang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Adapter_ThongKeXuatKho extends RecyclerView.Adapter<Adapter_ThongKeXuatKho.ViewHolder> {
    Context context;
    List<DTO_PhieuXuat> list;
    DAO_PhieuXuat dao_phieuXuat;
    List<DTO_sp> list2;
    DAO_sp dao_sp;
    public Adapter_ThongKeXuatKho(Context context, List<DTO_PhieuXuat> list) {
        this.context = context;
        this.list = list;
        dao_phieuXuat = new DAO_PhieuXuat(context);
        dao_sp = new DAO_sp(context);
        list2=dao_sp.getAllA();
    }
    public int checkIndex(int p){
        int index = 0;

        for (DTO_sp sp : list2){
            if(list.get(p).getMaSp().equals(sp.getMaSP())){
                break;
            }
            index++;
        }
        return index;
    }
    public Uri hienthi(int p) {
        byte[] imageData = list2.get(checkIndex(p)).getMota();// Mảng byte chứa dữ liệu hình ảnh
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
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_xuatkho_thongke,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblMaSp.setText(list.get(position).getMaSp());
        holder.lblTenSp.setText(list.get(position).getTenSp());
        holder.lblNgayXuat.setText(list.get(position).getNgayXuat());
        holder.lblSoLuong.setText(list.get(position).getSoLuong()+"");
        holder.anhMoTa.setImageURI(hienthi(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblMaSp, lblTenSp, lblNgayXuat, lblGia, lblSoLuong;
        ImageView anhMoTa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaSp = itemView.findViewById(R.id.lbl_maSp_XK_TK);
            lblTenSp = itemView.findViewById(R.id.lbl_tenSp_XK_TK);
            lblNgayXuat = itemView.findViewById(R.id.lbl_ngayXuat_XK_TK);
            lblSoLuong = itemView.findViewById(R.id.lbl_soLuongXuat_XK_TK);
            anhMoTa = itemView.findViewById(R.id.imv_imgsp_show_XK_TK);
        }
    }
}
