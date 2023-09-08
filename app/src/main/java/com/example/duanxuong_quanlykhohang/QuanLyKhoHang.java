package com.example.duanxuong_quanlykhohang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.fragment.Frag_load;
import com.example.duanxuong_quanlykhohang.fragment.Frag_sp_ql;
import com.example.duanxuong_quanlykhohang.fragment.phieu_xuat_nhap_khoFragment;
import com.example.duanxuong_quanlykhohang.fragment.qlKhoHangFragment;
import com.example.duanxuong_quanlykhohang.fragment.qlNhanSuFragment;
import com.example.duanxuong_quanlykhohang.fragment.ton_khoFragment;
import com.example.duanxuong_quanlykhohang.fragment.xuat_khoFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuanLyKhoHang extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    // sử dụng database
    View header;
    List<DTO_User> list;
    DAO_User user;
    DTO_User dto_user;
    ImageView avatar ;
    TextView  tenND;
    Frag_load frag_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho_hang);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        frag_load = new Frag_load();
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        list = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Kho Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menunavbar);

        // gọi hiển thị tên với avatar người dùng // nhận dữ liệu từ login
        header= navigationView.getHeaderView(0);
        user = new DAO_User(this);
        Intent intent = getIntent();
        dto_user= (DTO_User) intent.getSerializableExtra("user");
        list.add(dto_user);
        if(list!=null){
        avatar=header.findViewById(R.id.imgUsename);
        tenND=header.findViewById(R.id.lblUsername);
        avatar.setImageResource(R.drawable.logo);
        tenND.setText(list.get(0).getHoTen());
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int check=0;
                if(item.getItemId() == R.id.item_quanlykho) {
                    toolbar.setTitle("Quản lý kho hàng");
                    fragment = new qlKhoHangFragment();
                } else if (item.getItemId() == R.id.item_quanlysanpham) {
                    toolbar.setTitle("Quản lý sản phẩm");
                    fragment = new Frag_sp_ql();
                } else if (item.getItemId() == R.id.item_phieuxuatkho) {
                    toolbar.setTitle("Phiếu nhập - xuất kho");
                    fragment = new phieu_xuat_nhap_khoFragment();
                } else if (item.getItemId() == R.id.item_xuatkhotheothang) {
                    toolbar.setTitle("Thống kê xuất kho");
                    fragment = new xuat_khoFragment();
                } else if (item.getItemId() == R.id.item_tonkhotheothang) {
                    toolbar.setTitle("Thống kê tồn kho");
                    fragment = new ton_khoFragment();
                } else if (item.getItemId() == R.id.item_quanlynhansu) {
                  if(list.get(0).getVaiTro()==1){
                      toolbar.setTitle("Quản lý nhân sự");
                      fragment = new qlNhanSuFragment();
                  }else {
                      check=1;
                      Toast.makeText(QuanLyKhoHang.this, "Bạn không đủ quyền hạng", Toast.LENGTH_SHORT).show();
                  }
                } else if (item.getItemId() == R.id.item_doimatkhau) {
                    dialog_UpDatePass();
                } else if (item.getItemId() == R.id.item_dangxuat) {
                    openDialog_DangXuat();
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }

                if (check==0){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout, new qlKhoHangFragment()).commit();
    }
    public void openDialog_DangXuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyKhoHang.this);
        builder.setTitle("ĐĂNG XUẤT");
        builder.setMessage("Bạn có chắc chắn muốn thoát không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(QuanLyKhoHang.this, login.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void openDialog_tb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyKhoHang.this);
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // thực hiện kiểm tra mk trước khi thay đổi dữ liệu
                Log.e("TAG", "PASS: "+list.get(0).getMatKhau());
                if(ma1Check.equals(list.get(0).getMatKhau())){
                    if(ma2Check.equals(ma3Check)){
                        DTO_User dto_user = list.get(0);
                        dto_user.setMatKhau(ma3Check);
                        user.UpdateRow(dto_user,QuanLyKhoHang.this);
                        list = user.getAll();
                        Log.e("TAG", "PASS: "+list.get(0).getMatKhau());
                        dialog.dismiss();
                        Toast.makeText(QuanLyKhoHang.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(QuanLyKhoHang.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(QuanLyKhoHang.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }



            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    // truyền dữ liệu
    String ma1Check ;
    String ma2Check ;
    String ma3Check ;

    public void dialog_UpDatePass(){
        AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyKhoHang.this);
        View view =getLayoutInflater().inflate(R.layout.dialog_update_pass,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //ánh xạ
        EditText txtMatKhauCu = view.findViewById(R.id.txtMatKhauCu);
        EditText txtMatKhauMoi = view.findViewById(R.id.txtMatKhauMoi);
        EditText txtMatKhauMoixn = view.findViewById(R.id.txtMatKhauMoixn);
        Button btnCancelud = view.findViewById(R.id.btnCancelud);
        Button btnSaveud = view.findViewById(R.id.btnSaveud);
        //
        btnSaveud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ma1Check=txtMatKhauCu.getText().toString();
                ma2Check=txtMatKhauMoi.getText().toString();
                ma3Check=txtMatKhauMoixn.getText().toString();
                openDialog_tb();
                dialog.dismiss();
            }
        });
        btnCancelud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void importAnh(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    Uri selectedImage;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            Toast.makeText(this, "Chọn ảnh thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public byte[] getAnh() {
        // Max allowed size in bytes
        int maxSize = 1024 * 1024; // 1MB

        try {
            InputStream inputStream = getContentResolver().openInputStream(selectedImage);

            // Đọc ảnh vào một đối tượng Bitmap
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);

            // Tính toán tỷ lệ nén cần áp dụng để đảm bảo kích thước không vượt quá maxSize
            int scale = 1;
            while ((options.outWidth * options.outHeight) * (1 / Math.pow(scale, 2)) > maxSize) {
                scale++;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            inputStream.close();

            // Đọc ảnh lại với tỷ lệ nén
            inputStream = getContentResolver().openInputStream(selectedImage);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, options);

            // Chuyển đổi Bitmap thành byte array
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteBuffer); // Thay đổi định dạng và chất lượng nén tùy theo nhu cầu
            byte[] imageData = byteBuffer.toByteArray();

            return imageData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}