package com.example.duanxuong_quanlykhohang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;

import java.util.Calendar;
import java.util.List;

public class Adapter_sp_Phieu_Xuat extends RecyclerView.Adapter<Adapter_sp_Phieu_Xuat.ViewHolder> {
    Context context;
    List<DTO_PhieuXuat> list;
    DAO_PhieuXuat dao_phieuXuat;
    public Adapter_sp_Phieu_Xuat(Context context, List<DTO_PhieuXuat> list) {
        this.context = context;
        this.list = list;
        dao_phieuXuat = new DAO_PhieuXuat(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_xuatkho,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.maPhieu.setText(list.get(position).getMaPhieu()+"");
        holder.maSp.setText(list.get(position).getMaSp());
        holder.tenSp.setText(list.get(position).getTenSp());
        holder.ngayXuat.setText(list.get(position).getNgayXuat());
        holder.gia.setText(list.get(position).getGia()+"");
        holder.soLuong.setText(list.get(position).getSoLuong()+"");
        holder.xoaPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao_phieuXuat = new DAO_PhieuXuat(context);
                DTO_PhieuXuat dto_phieuXuat = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao_phieuXuat.xoaPhieuXuat(dto_phieuXuat);
                        list.clear();
                        list.addAll(dao_phieuXuat.layDanhSachPhieuXuat());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Giữ nguyên phiếu xuất!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.updatePhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capNhatPhieuXuat(position);
            }
        });
    }
    public void capNhatPhieuXuat(int position){
        DAO_PhieuXuat daoPhieuXuat = new DAO_PhieuXuat(context);
        DTO_PhieuXuat dto_phieuXuat = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_sua_phieu_xuat, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText txtUdSoLuong = view.findViewById(R.id.txtUdSoLuongXuat);
        EditText txtUdNgayXuat = view.findViewById(R.id.txtUdNgayXuat);
        Button luuThongTin = view.findViewById(R.id.btnUdSavex);
        Button thoat = view.findViewById(R.id.btnUdCancelx);
        // Hiển thị thông tin cần cập nhật lên EditText
        txtUdSoLuong.setText(String.valueOf(dto_phieuXuat.getSoLuong()));
        txtUdNgayXuat.setText(dto_phieuXuat.getNgayXuat());
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        txtUdNgayXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtUdNgayXuat.setText(String.format("%d-%d-%d", year, month, dayOfMonth));
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });
        // Xử lý khi bấm nút Lưu
        luuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongMoi = Integer.parseInt(txtUdSoLuong.getText().toString());
                String ngayXuatMoi = txtUdNgayXuat.getText().toString();

                // Thực hiện cập nhật phiếu xuất trong cơ sở dữ liệu
                daoPhieuXuat.suaPhieuXuat(dto_phieuXuat.getMaPhieu(), soLuongMoi, ngayXuatMoi);

                // Cập nhật lại danh sách phiếu xuất trong RecyclerView
                list.clear();
                list.addAll(daoPhieuXuat.layDanhSachPhieuXuat());
                notifyDataSetChanged();
                Toast.makeText(context, "Cập nhật phiếu xuất thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
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
        TextView maPhieu, maSp, tenSp , ngayXuat, soLuong, gia;
        ImageButton updatePhieu,xoaPhieu;
        ImageView anh ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maPhieu = itemView.findViewById(R.id.lbl_id_phieu_xuat);
            maSp = itemView.findViewById(R.id.lbl_id_sp_xuat);
            tenSp = itemView.findViewById(R.id.lbl_name_sp_xuat);
            ngayXuat = itemView.findViewById(R.id.lbl_ngay_xuat);
            gia = itemView.findViewById(R.id.lbl_gia_xuat);
            soLuong = itemView.findViewById(R.id.lbl_so_luong_xuat);
            updatePhieu = itemView.findViewById(R.id.ibtn_update_xuat);
            xoaPhieu = itemView.findViewById(R.id.ibtn_delete_xuat);
//            anh = itemView.findViewById(R.id.imv_imgsp_show);
        }
    }
}