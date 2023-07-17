package com.example.duanxuong_quanlykhohang.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Adapter_sp extends RecyclerView.Adapter<Adapter_sp.ViewHolder>{

    Context context;
    List<DTO_sp> list;

    public Adapter_sp(Context context, List<DTO_sp> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_sanpham,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     holder.id_sp.setText(list.get(position).getMaSP()+"");
     holder.ten_sp.setText(list.get(position).getTenSP());
     holder.soLuong.setText(list.get(position).getSoLuong()+"");
     holder.ngayNhap.setText(list.get(position).getNgayluu());
     holder.theLoai.setText(list.get(position).getTenLoai());
     holder.gia.setText(list.get(position).getGia()+"");

//        byte[] imageData = list.get(position).getMota() ; // Mảng byte chứa dữ liệu hình ảnh
//        String tempFileName = "temp_image.jpg";
//
//// Tạo đường dẫn tới tập tin ảnh tạm
//        File tempFile = new File(context.getCacheDir(), tempFileName);
//
//// Ghi dữ liệu blob vào tập tin ảnh tạm
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
//            fileOutputStream.write(imageData);
//            fileOutputStream.close();
//
//            Uri uri = Uri.fromFile(tempFile);
//
//            holder.anh.setImageURI(uri);
//        }catch (Exception e){
//
//        }


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
    DAO_sp sp =new DAO_sp(context);
    DTO_sp DTO_sp = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


        TextView title = dialog.findViewById(R.id.tv_tilte_sp);
        title.setText("Update Sản phẩm");
        EditText id_sp = dialog.findViewById(R.id.txtIdSanPhamThem);
        EditText Ten_sp = dialog.findViewById(R.id.txtTenSanPhamThem);
        EditText soluong = dialog.findViewById(R.id.txtSoLuongThem);
        EditText ngaynhap = dialog.findViewById(R.id.txtNgayLuuKhoThem);
//        ImageView anh =dialog.findViewById(R.id.imv_imgsp);
        Button save = dialog.findViewById(R.id.btnSaveThem);
        Button back = dialog.findViewById(R.id.btnCancelThem);



        id_sp.setText(DTO_sp.getMaSP());
        Ten_sp.setText(DTO_sp.getTenSP());
        soluong.setText(DTO_sp.getSoLuong());
        id_sp.setText(DTO_sp.getMaSP());
        ngaynhap.setText(DTO_sp.getSoLuong()+"");


//        byte[] imageData = list.get(position).getMota() ; // Mảng byte chứa dữ liệu hình ảnh
//        String tempFileName = "temp_image.jpg";
//
//// Tạo đường dẫn tới tập tin ảnh tạm
//        File tempFile = new File(context.getCacheDir(), tempFileName);
//
//// Ghi dữ liệu blob vào tập tin ảnh tạm
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
//            fileOutputStream.write(imageData);
//            fileOutputStream.close();
//
//            Uri uri = Uri.fromFile(tempFile);
//
//            anh.setImageURI(uri);
//        }catch (Exception e){
//
//        }
      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DTO_sp.setMaSP(id_sp.getText().toString());
              DTO_sp.setMaSP(Ten_sp.getText().toString());
              DTO_sp.setMaSP(soluong.getText().toString());
              DTO_sp.setMaSP(ngaynhap.getText().toString());

              sp.Update(DTO_sp);
              list.clear();
              list.addAll(sp.getAll());
              notifyDataSetChanged();
              Toast.makeText(context, "Lưu Thành công", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder{
EditText id_sp , ten_sp , soLuong,ngayNhap,theLoai , gia;
ImageButton xoa,update;

ImageView anh ;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           id_sp = itemView.findViewById(R.id.lbl_id_sp);
           ten_sp =  itemView.findViewById(R.id.lbl_name_sp);
           soLuong =  itemView.findViewById(R.id.lbl_sl_sp);
           ngayNhap =  itemView.findViewById(R.id.lbl_date_add_sp);
           theLoai =  itemView.findViewById(R.id.lbl_ten_loai_sp);
           gia =  itemView.findViewById(R.id.lbl_price_sp);
           xoa =  itemView.findViewById(R.id.ibtn_delete_sp);
           update =  itemView.findViewById(R.id.ibtn_update_sp);
//           anh = itemView.findViewById(R.id.imv_imgsp_show);

       }
   }
}
