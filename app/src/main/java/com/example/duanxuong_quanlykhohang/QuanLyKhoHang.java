package com.example.duanxuong_quanlykhohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.duanxuong_quanlykhohang.dbhelper.DBHelper;
import com.example.duanxuong_quanlykhohang.fragment.phieu_xuat_khoFragment;
import com.example.duanxuong_quanlykhohang.fragment.qlKhoHangFragment;
import com.example.duanxuong_quanlykhohang.fragment.qlNhanSuFragment;
import com.example.duanxuong_quanlykhohang.fragment.qlSanPhamFragment;
import com.example.duanxuong_quanlykhohang.fragment.ton_khoFragment;
import com.example.duanxuong_quanlykhohang.fragment.xuat_khoFragment;
import com.google.android.material.navigation.NavigationView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho_hang);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
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
        Log.e("TAG", ""+dto_user.getNguoiDung() );
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
                if(item.getItemId() == R.id.item_quanlykho) {
                    toolbar.setTitle("Quản lý kho hàng");
                    fragment = new qlKhoHangFragment();
                } else if (item.getItemId() == R.id.item_quanlysanpham) {
                    toolbar.setTitle("Quản lý sản phẩm");
                    fragment = new qlSanPhamFragment();
                } else if (item.getItemId() == R.id.item_phieuxuatkho) {
                    toolbar.setTitle("Phiếu xuất kho");
                    fragment = new phieu_xuat_khoFragment();
                } else if (item.getItemId() == R.id.item_xuatkhotheothang) {
                    toolbar.setTitle("Thống kê xuất kho");
                    fragment = new xuat_khoFragment();
                } else if (item.getItemId() == R.id.item_tonkhotheothang) {
                    toolbar.setTitle("Thống kê tồn kho");
                    fragment = new ton_khoFragment();
                } else if (item.getItemId() == R.id.item_quanlynhansu) {
                    toolbar.setTitle("Quản lý nhân sự");
                    fragment = new qlNhanSuFragment();
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

                drawerLayout.closeDrawer(GravityCompat.START);
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
                        user.UpdateRow(dto_user);
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
}