package com.example.duanxuong_quanlykhohang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;

import java.util.List;

public class login extends AppCompatActivity {

    DAO_User user;
    List<DTO_User> list;
    EditText userN;
    EditText passN;
    CheckBox luuPass;
    SharedPreferences sharedPreferences;
    int hienThi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userN = findViewById(R.id.txtTaiKhoan);
        passN = findViewById(R.id.txtMatKhau);
        Button btnLogin = findViewById(R.id.btnLogin);
        ImageButton hideAndShow = findViewById(R.id.ibtn_hienthiPass);
        luuPass = findViewById(R.id.cbk_luudangNhap);
        sharedPreferences = getSharedPreferences("dangNhap", MODE_PRIVATE);
        boolean checkLuu = sharedPreferences.getBoolean("luuPass", false);
        if (checkLuu) {
            userN.setText(sharedPreferences.getString("ten", ""));
            passN.setText(sharedPreferences.getString("pass", ""));
            luuPass.setChecked(sharedPreferences.getBoolean("luuPass", false));
        }
        user = new DAO_User(this);
        list = user.getAll();
        hideAndShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hienThi == 0) {
                    hideAndShow.setImageResource(R.drawable.show);
                    passN.setInputType(InputType.TYPE_CLASS_TEXT);
                    hienThi = 1;
                } else {
                    hideAndShow.setImageResource(R.drawable.hide);
                    passN.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    hienThi = 0;
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedIn = false; // Biến để kiểm tra đăng nhập thành công
                DTO_User matchedUser = null; // Biến để lưu thông tin người dùng khớp

                String taiKhoan = userN.getText().toString().trim();
                String matKhau = passN.getText().toString().trim();

                if (taiKhoan.isEmpty()) {
                    Toast.makeText(login.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (matKhau.isEmpty()) {
                    Toast.makeText(login.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    for (DTO_User us : list) {
                        if (taiKhoan.equals(us.getNguoiDung()) && matKhau.equals(us.getMatKhau())) {
                            isLoggedIn = true; // Tài khoản và mật khẩu khớp
                            matchedUser = us; // Lưu thông tin người dùng khớp
                            break;
                        }
                    }

                    if (isLoggedIn) {
                        // Đăng nhập thành công
                        Intent intent = new Intent(login.this, QuanLyKhoHang.class);
                        intent.putExtra("user", matchedUser); // Truyền thông tin người dùng khớp
                        startActivity(intent);
                        Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        luuDangNhap();
                    } else {
                        // Đăng nhập thất bại
                        Toast.makeText(login.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void luuDangNhap() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ten", userN.getText().toString().trim());
        editor.putString("pass", passN.getText().toString().trim());
        editor.putBoolean("luuPass", luuPass.isChecked());
        editor.apply();
    }
}