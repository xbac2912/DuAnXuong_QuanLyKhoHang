package com.example.duanxuong_quanlykhohang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class login extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText userN = findViewById(R.id.txtTaiKhoan);
        EditText passN = findViewById(R.id.txtMatKhau);
        Button btnLogin = findViewById(R.id.btnLogin);
  btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent
                  = new Intent(login.this,QuanLyKhoHang.class);
          startActivity(intent);
      }
  });

}
}