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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;

import java.util.Calendar;
import java.util.List;

public class Adapter_sp_Phieu_Nhap extends RecyclerView.Adapter<Adapter_sp_Phieu_Nhap.ViewHolder>{
        Context context;
        List<DTO_sp_Phieu_Nhap> list;
        List<DTO_sp> list2;

//        List<DTO_LoaiHang> listHang;
//        DAO_LoaiHang loaiHang;
//        DTO_LoaiHang dto_loaiHang;
        Adapter_loaiSP adapterLoaiSP;
    public Adapter_sp_Phieu_Nhap(Context context, List<DTO_sp_Phieu_Nhap> list) {
        this.context = context;
        this.list = list;
        }

    @NonNull
    @Override
    public Adapter_sp_Phieu_Nhap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_phieu_nhap,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_sp_Phieu_Nhap.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_phieu.setText(list.get(position).getMaPhieuNhap());
        holder.id_ma_sp.setText(list.get(position).getMaSanPham() + "");
//        holder.ten_sp.setText(list2.get(position).getTenSP());
        holder.ngayNhap.setText(list.get(position).getNgayNhap());
        holder.gia.setText(list.get(position).getGia() + "");
        holder.soLuong.setText(list.get(position).getSoLuong() + "");

        //
//        holder.xoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DAO_sp_Phieu_Nhap sp_phieu_nhap = new DAO_sp_Phieu_Nhap(context);
//                DTO_sp_Phieu_Nhap dto_sp_phieu_nhap = list.get(position);
//                sp_phieu_nhap.DeleteRow(dto_sp_phieu_nhap);
//                list.clear();
//                list.addAll(sp_phieu_nhap.getAll());
//                notifyDataSetChanged();
//                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                update(position);
//
//            }
//        });
    }
        public void update (int position){
//            DAO_sp_Phieu_Nhap sp_phieu_nhap = new DAO_sp_Phieu_Nhap(context);
//            DTO_sp_Phieu_Nhap dto_sp_phieu_nhap = list.get(position);
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            View view = inflater.inflate(R.layout.dialog_them_sp, null);
//            builder.setView(view);
//            Dialog dialog = builder.create();
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
//
//
//            TextView title = view.findViewById(R.id.tv_tilte_sp);
//            title.setText("Update Sản phẩm");
//            EditText id_sp = view.findViewById(R.id.txtIdSanPhamThem);
//            EditText Ten_sp = view.findViewById(R.id.txtTenSanPhamThem);
//            EditText tenLoai = view.findViewById(R.id.txtLoaiThem);
////        ImageView anh =dialog.findViewById(R.id.imv_imgsp);
//            Button save = view.findViewById(R.id.btnSaveThem);
//            Button back = view.findViewById(R.id.btnCancelThem);
//            final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};
//            getID[0].setId(list.get(position).getMaLoai());
//
//            Calendar lich = Calendar.getInstance();
//            int ngay = lich.get(Calendar.DAY_OF_MONTH);
//            int thang = lich.get(Calendar.MONTH);
//            int nam = lich.get(Calendar.YEAR);
//
//
//            id_sp.setText(DTO_sp.getMaSP());
//            Ten_sp.setText(DTO_sp.getTenSP());
//            tenLoai.setText(DTO_sp.getTenLoai());
//
//            tenLoai.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
//                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//                    View view2 = inflater.inflate(R.layout.dialog_them_loaihang, null);
//                    builder.setView(view2);
//                    Dialog dialogLoaiSP = builder.create();
//                    dialogLoaiSP.show();
//                    ListView listLoaiHang = view2.findViewById(R.id.lis_loaiSP);
//                    EditText themLoai = view2.findViewById(R.id.txt_themLoai);
//                    ImageButton add = view2.findViewById(R.id.ibtn_addLoai);
//                    loaiHang = new DAO_LoaiHang(context);
//                    listHang = loaiHang.getAll();
//                    adapterLoaiSP = new Adapter_loaiSP(context, listHang);
//                    listLoaiHang.setAdapter(adapterLoaiSP);
//                    themLoai.setVisibility(View.GONE);
//                    add.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            themLoai.setVisibility(View.VISIBLE);
//                            add.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dto_loaiHang = new DTO_LoaiHang();
//                                    dto_loaiHang.setTenLoai(themLoai.getText().toString());
//                                    if (loaiHang.AddRow(dto_loaiHang) > 0) {
//                                        listHang.clear();
//                                        listHang.addAll(loaiHang.getAll());
//                                        notifyDataSetChanged();
//                                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                                        dialogLoaiSP.dismiss();
//
//                                        getID[0] = listHang.get(listHang.size() - 1);
//                                        tenLoai.setText(getID[0].getTenLoai());
//
//                                    } else {
//                                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                                    }
//
//
//                                }
//                            });
//                        }
//                    });
//
//                    listLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            getID[0] = listHang.get(position);
//                            tenLoai.setText(getID[0].getTenLoai());
//                            dialogLoaiSP.dismiss();
//                        }
//                    });
//
//
//                }
//            });
//
//            save.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dto_sp_phieu_nhap.setMaSanPham(id_sp.getText().toString());
//                    DTO_sp.setTenSP(Ten_sp.getText().toString());
//                    DTO_sp.setTenLoai(getID[0].getTenLoai());
//                    DTO_sp.setMaLoai(getID[0].getId());
//
//
//                    if (sp.Update(DTO_sp) > 0) {
//                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Sửa  thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                    list.clear();
//                    list.addAll(sp.getAll());
//                    notifyDataSetChanged();
//
//                    dialog.dismiss();
//                }
//            });
//            back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });


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
//           anh = itemView.findViewById(R.id.imv_imgsp_show);
        }
    }
}
