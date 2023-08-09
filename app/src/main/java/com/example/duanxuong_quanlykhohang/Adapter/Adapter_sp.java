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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class Adapter_sp extends RecyclerView.Adapter<Adapter_sp.ViewHolder> {

    Context context;
    List<DTO_sp> list;

    List<DTO_LoaiHang> listHang;
    DAO_LoaiHang loaiHang;
    DTO_LoaiHang dto_loaiHang;
    Adapter_loaiSP adapterLoaiSP;


    public Adapter_sp(Context context, List<DTO_sp> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_sanpham, parent, false);

        return new ViewHolder(view);
    }

    public Uri hienthi(int p) {
        byte[] imageData = list.get(p).getMota();// Mảng byte chứa dữ liệu hình ảnh
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

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_sp.setText(list.get(position).getMaSP() + "");
        holder.ten_sp.setText(list.get(position).getTenSP());
        holder.theLoai.setText(list.get(position).getTenLoai());
        holder.soLuongTon.setText(list.get(position).getSoLuongTon() + "");
        holder.anh.setImageURI(hienthi(position));


        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO_sp sp = new DAO_sp(context);
                DTO_sp DTO_sp = list.get(position);
                sp.DeleteRow(DTO_sp);
                list.clear();
                list.addAll(sp.getAll());
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

    public void update(int position) {
        DAO_sp sp = new DAO_sp(context);
        DTO_sp DTO_sp = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


        TextView title = view.findViewById(R.id.tv_tilte_sp);
        title.setText("Update Sản phẩm");
        EditText id_sp = view.findViewById(R.id.txtIdSanPhamThem);
        EditText Ten_sp = view.findViewById(R.id.txtTenSanPhamThem);
        EditText tenLoai = view.findViewById(R.id.txtLoaiThem);
//        ImageView anh =dialog.findViewById(R.id.imv_imgsp);
        Button save = view.findViewById(R.id.btnSaveThem);
        Button back = view.findViewById(R.id.btnCancelThem);
        final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};
        getID[0].setId(list.get(position).getMaLoai());

        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);


        id_sp.setText(DTO_sp.getMaSP());
        Ten_sp.setText(DTO_sp.getTenSP());
        tenLoai.setText(DTO_sp.getTenLoai());

        tenLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view2 = inflater.inflate(R.layout.dialog_them_loaihang, null);
                builder.setView(view2);
                Dialog dialogLoaiSP = builder.create();
                dialogLoaiSP.show();
                ListView listLoaiHang = view2.findViewById(R.id.lis_loaiSP);
                EditText themLoai = view2.findViewById(R.id.txt_themLoai);
                ImageButton add = view2.findViewById(R.id.ibtn_addLoai);
                loaiHang = new DAO_LoaiHang(context);
                listHang = loaiHang.getAll();
                adapterLoaiSP = new Adapter_loaiSP(context, listHang);
                listLoaiHang.setAdapter(adapterLoaiSP);
                themLoai.setVisibility(View.GONE);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themLoai.setVisibility(View.VISIBLE);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dto_loaiHang = new DTO_LoaiHang();
                                dto_loaiHang.setTenLoai(themLoai.getText().toString());
                                if (loaiHang.AddRow(dto_loaiHang) > 0) {
                                    listHang.clear();
                                    listHang.addAll(loaiHang.getAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialogLoaiSP.dismiss();

                                    getID[0] = listHang.get(listHang.size() - 1);
                                    tenLoai.setText(getID[0].getTenLoai());

                                } else {
                                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    }
                });

                listLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        getID[0] = listHang.get(position);
                        tenLoai.setText(getID[0].getTenLoai());
                        dialogLoaiSP.dismiss();
                    }
                });


            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DTO_sp.setMaSP(id_sp.getText().toString());
                DTO_sp.setTenSP(Ten_sp.getText().toString());
                DTO_sp.setTenLoai(getID[0].getTenLoai());
                DTO_sp.setMaLoai(getID[0].getId());


                if (sp.Update(DTO_sp) > 0) {
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa  thất bại", Toast.LENGTH_SHORT).show();
                }
                list.clear();
                list.addAll(sp.getAll());
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_sp, ten_sp, soLuongTon, theLoai;
        ImageButton xoa, update;

        ImageView anh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_sp = itemView.findViewById(R.id.lbl_id_sp);
            ten_sp = itemView.findViewById(R.id.lbl_name_sp);
            theLoai = itemView.findViewById(R.id.lbl_ten_loai_sp);
            soLuongTon = itemView.findViewById(R.id.lbl_soluongton);
            xoa = itemView.findViewById(R.id.ibtn_delete_sp);
            update = itemView.findViewById(R.id.ibtn_update_sp);
            anh = itemView.findViewById(R.id.imv_imgsp_show);

        }
    }
}
