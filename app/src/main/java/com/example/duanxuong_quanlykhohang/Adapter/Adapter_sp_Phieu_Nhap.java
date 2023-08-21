package com.example.duanxuong_quanlykhohang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class Adapter_sp_Phieu_Nhap extends RecyclerView.Adapter<Adapter_sp_Phieu_Nhap.ViewHolder>{
        Context context;
        List<DTO_sp_Phieu_Nhap> list;
        DAO_sp_Phieu_Nhap dao_sp_phieu_nhap;
        DAO_sp dao_sp;
        List<DTO_sp> list2;


    public Adapter_sp_Phieu_Nhap(Context context, List<DTO_sp_Phieu_Nhap> list) {
        this.context = context;
        this.list = list;
        dao_sp_phieu_nhap = new DAO_sp_Phieu_Nhap(context);
        dao_sp = new DAO_sp(context);
        list2 = dao_sp.getAll(0);
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_phieu_nhap,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_phieu.setText(list.get(position).getMaPhieuNhap()+"");
        holder.id_ma_sp.setText(list.get(position).getMaSanPham());
        holder.ten_sp.setText(list.get(position).getTenSP());
        holder.ngayNhap.setText(list.get(position).getNgayNhap());
        holder.gia.setText(list.get(position).getGia() + "");
        holder.soLuong.setText(list.get(position).getSoLuong() + "");
        holder.anh.setImageURI(hienthi(position));

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO_sp_Phieu_Nhap sp_phieu_nhap = new DAO_sp_Phieu_Nhap(context);
                DTO_sp_Phieu_Nhap dto_sp_phieu_nhap = list.get(position);
                sp_phieu_nhap.DeleteRow(dto_sp_phieu_nhap);
                list.clear();
                list.addAll(sp_phieu_nhap.getAll());
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(position);

            }
        });
    }
        public void update (int position){
            DAO_sp_Phieu_Nhap sp_phieu_nhap = new DAO_sp_Phieu_Nhap(context);
            DTO_sp_Phieu_Nhap dto_sp_phieu_nhap = list.get(position);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = inflater.inflate(R.layout.dialog_them_sp_phieu_nhap, null);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


            TextView title = view.findViewById(R.id.tv_tilte_sp);
            title.setText("Update Phiếu Nhập Kho");
            EditText id_spthemP = view.findViewById(R.id.txtIdSanPhamThem);
            EditText ngayNhapP = view.findViewById(R.id.txtNgayNhap);
            EditText giaP = view.findViewById(R.id.txtgiaNhap);
            EditText soLuongP = view.findViewById(R.id.txtsoLuongNhap);
//        ImageView anh =dialog.findViewById(R.id.imv_imgsp);
            Button save = view.findViewById(R.id.btnSavephieuThem);
            Button back = view.findViewById(R.id.btnCancelphieuThem);
            final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};
            getID[0].setId(list.get(position).getMaPhieuNhap());

            Calendar lich = Calendar.getInstance();
            int ngay = lich.get(Calendar.DAY_OF_MONTH);
            int thang = lich.get(Calendar.MONTH);
            int nam = lich.get(Calendar.YEAR);
            ngayNhapP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog bangLich = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            ngayNhapP.setText(String.format("%d-%d-%d", year, month+1, dayOfMonth));
                        }
                    }, nam, thang, ngay);
                    bangLich.show();
                }
            });


            id_spthemP.setText(dto_sp_phieu_nhap.getMaSanPham());
            ngayNhapP.setText(dto_sp_phieu_nhap.getNgayNhap());
            giaP.setText(dto_sp_phieu_nhap.getGia()+"");
            soLuongP.setText(dto_sp_phieu_nhap.getSoLuong()+"");

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dto_sp_phieu_nhap.setMaSanPham(id_spthemP.getText().toString());
                    dto_sp_phieu_nhap.setNgayNhap(ngayNhapP.getText().toString());
                    dto_sp_phieu_nhap.setGia(Integer.parseInt(giaP.getText().toString()));
                    dto_sp_phieu_nhap.setSoLuong(Integer.parseInt(soLuongP.getText().toString()));


                    if (sp_phieu_nhap.Update(dto_sp_phieu_nhap) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa  thất bại", Toast.LENGTH_SHORT).show();
                    }
                    list.clear();
                    list.addAll(sp_phieu_nhap.getAll());
                    notifyDataSetChanged();

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

    public int checkIndex(int p){
        int index = 0;

        for (DTO_sp sp : list2){
            if(list.get(p).getMaSanPham().equals(sp.getMaSP())){
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_phieu, id_ma_sp, ten_sp , soLuong, ngayNhap, gia;
        ImageButton xoa,update;

        ImageView anh ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_phieu = itemView.findViewById(R.id.lbl_id_phieu_nhap);
            ten_sp =  itemView.findViewById(R.id.lbl_name_sp_nhap);
            id_ma_sp =  itemView.findViewById(R.id.lbl_id_sp_nhap);
            ngayNhap =  itemView.findViewById(R.id.lbl_ngay_nhap);
            gia =  itemView.findViewById(R.id.lbl_gia_nhap);
            soLuong =  itemView.findViewById(R.id.lbl_so_luong_nhap);
            xoa =  itemView.findViewById(R.id.ibtn_delete_nhap);
            update =  itemView.findViewById(R.id.ibtn_update_nhap);
           anh = itemView.findViewById(R.id.imv_imgsp_show);
        }
    }
}
