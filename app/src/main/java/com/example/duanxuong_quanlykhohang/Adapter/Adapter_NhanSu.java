package com.example.duanxuong_quanlykhohang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setIcon(R.drawable.baseline_warning_amber_24);
                    builder.setMessage("Xác nhận muốn xóa");


                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            user.DeleteRow(dto_user);
                            list.clear();
                            list.addAll(user.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setTitle("Vui lòng chọn");
               String [] chon= new String[]{
                 "Sửa thông tin","Reset password"
               };
               builder.setItems(chon, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if(which==0){
                           update(position,which);
                       }else {
                           update(position,which);
                       }
                   }
               });
               Dialog dialog1 = builder.create();
               dialog1.show();
            }
        });
    }

    public void update(int position,int chucnang) {
        DAO_User user = new DAO_User(context);
        DTO_User dto_user = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_nhan_su, null);
        builder.setView(view);
        Dialog dialog_sua = builder.create();
        dialog_sua.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_sua.show();



        TextView title = dialog_sua.findViewById(R.id.tv_title_ns);
        title.setText("Update nhân viên");
        EditText hoTen = dialog_sua.findViewById(R.id.txtHoTenNhanSu);
        EditText taiKhoan = dialog_sua.findViewById(R.id.txtTaiKhoanNS);
        EditText maKhau = dialog_sua.findViewById(R.id.txtMatKhauNS);
        EditText maKhauNS = dialog_sua.findViewById(R.id.txtRMatKhauNS);

        if(chucnang==0){
            maKhau.setVisibility(View.GONE);
            maKhauNS.setVisibility(View.GONE);
        }else {
            dialog_sua.dismiss();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle("Lưu ý !");
            builder1.setIcon(R.drawable.baseline_warning_amber_24);
            builder1.setMessage("Bạn có muốn reset password của "+dto_user.getHoTen());
            builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    dialog_sua.dismiss();

                }
            });
            builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dto_user.setMatKhau("00000000");
                    if (user.UpdateRow(dto_user,context)>0){
                        Toast.makeText(context, "Mật khẩu đã được reset", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }else {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Dialog dialog_reset = builder1.create();
            dialog_reset.show();
        }



        Button save = dialog_sua.findViewById(R.id.btnSaveThemNS);
        Button back = dialog_sua.findViewById(R.id.btnCancelThemNS);


        hoTen.setText(dto_user.getHoTen());
        taiKhoan.setText(dto_user.getNguoiDung());
        maKhau.setText(dto_user.getMatKhau());
        maKhauNS.setText(dto_user.getMatKhau());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    dto_user.setHoTen(hoTen.getText().toString());
                    dto_user.setNguoiDung(taiKhoan.getText().toString());
                    dto_user.setMatKhau(maKhau.getText().toString());
                    if(user.UpdateRow(dto_user,context)>0){
                        list.clear();
                        list.addAll(user.getAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog_sua.dismiss();
                    }else {
                        Toast.makeText(context, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    }



            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_sua.dismiss();
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
