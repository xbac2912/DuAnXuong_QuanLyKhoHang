package com.example.duanxuong_quanlykhohang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.R;

import java.util.List;

public class Adapter_NhanSu extends RecyclerView.Adapter<Adapter_NhanSu.ViewHolder> {
    Context context;
    List<DTO_User> list;


    public Adapter_NhanSu(Context context, List<DTO_User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_nhan_su, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(list.get(position).getMaND() + "");
        holder.ten.setText(list.get(position).getHoTen());
        holder.taikhoan.setText(list.get(position).getNguoiDung());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO_User user = new DAO_User(context);
                DTO_User dto_user = list.get(position);
                if (dto_user.getVaiTro() == 1) {
                    Toast.makeText(context, "Không thể xóa Admin", Toast.LENGTH_SHORT).show();
                } else {
                    user.DeleteRow(dto_user);
                    list.clear();
                    list.addAll(user.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(position);
            }
        });
    }

    public void update(int position) {
        DAO_User user = new DAO_User(context);
        DTO_User dto_user = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_nhan_su, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


        TextView title = dialog.findViewById(R.id.tv_title_ns);
        title.setText("Update nhân viên");
        EditText hoTen = dialog.findViewById(R.id.txtHoTenNhanSu);
        EditText taiKhoan = dialog.findViewById(R.id.txtTaiKhoanNS);
        EditText maKhau = dialog.findViewById(R.id.txtMatKhauNS);
        EditText maKhauNS = dialog.findViewById(R.id.txtRMatKhauNS);
        Button save = dialog.findViewById(R.id.btnSaveThemNS);
        Button back = dialog.findViewById(R.id.btnCancelThemNS);
        maKhauNS.setVisibility(View.GONE);


        hoTen.setText(dto_user.getHoTen());
        taiKhoan.setText(dto_user.getNguoiDung());
        maKhau.setText(dto_user.getMatKhau());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dto_user.setHoTen(hoTen.getText().toString());
                dto_user.setNguoiDung(taiKhoan.getText().toString());
                dto_user.setMatKhau(maKhau.getText().toString());
                user.UpdateRow(dto_user);
                list.clear();
                list.addAll(user.getAll());
                notifyDataSetChanged();
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, ten, taikhoan;
        ImageButton update, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.lbl_id_ns1);
            ten = itemView.findViewById(R.id.lbl_name_ns1);
            taikhoan = itemView.findViewById(R.id.lbl_user_ns1);
            update = itemView.findViewById(R.id.ibtn_update_ns1);
            delete = itemView.findViewById(R.id.ibtn_delete_ns1);
        }
    }
}
