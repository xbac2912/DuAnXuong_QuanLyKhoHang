package com.example.duanxuong_quanlykhohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class QuanLyKhoHang extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho_hang);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Kho Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menunavbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_quanlykho) {

                } else if (item.getItemId() == R.id.item_quanlyloaihang) {

                } else if (item.getItemId() == R.id.item_phieuxuatkho) {

                } else if (item.getItemId() == R.id.item_xuatkhotheothang) {

                } else if (item.getItemId() == R.id.item_tonkhotheothang) {

                } else if (item.getItemId() == R.id.item_quanlynhansu) {

                } else if (item.getItemId() == R.id.item_doimatkhau) {
                    dialog_UpDatePass();
                } else if (item.getItemId() == R.id.item_dangxuat) {
                    openDialog_DangXuat();
                }
                return false;
            }
        });
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
                dialog.dismiss();
                Toast.makeText(QuanLyKhoHang.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

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
    public void dialog_UpDatePass(){
        AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyKhoHang.this);
        View view =getLayoutInflater().inflate(R.layout.item_update_pass,null);
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