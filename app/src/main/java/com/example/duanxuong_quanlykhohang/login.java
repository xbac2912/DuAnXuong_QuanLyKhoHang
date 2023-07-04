package com.example.duanxuong_quanlykhohang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;

import java.util.List;

public class login extends AppCompatActivity {

    DAO_User user;
    List<DTO_User> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText userN = findViewById(R.id.txtTaiKhoan);
        EditText passN = findViewById(R.id.txtMatKhau);
        Button btnLogin = findViewById(R.id.btnLogin);
        user = new DAO_User(this);
        user.addADMIN();
        list=user.getAll();

  btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          int a = 0;
          if(userN.getText().toString().isEmpty()) {
              Toast.makeText(login.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
          } else if(passN.getText().toString().isEmpty()) {
              Toast.makeText(login.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
          } else {
              for(DTO_User us : list){
                  if (userN.getText().toString().equals(us.getNguoiDung())){
                      if(passN.getText().toString().equals(us.getMatKhau())){
                          Intent intent = new Intent(login.this,QuanLyKhoHang.class);
                          DTO_User dto_user = us;
                          intent.putExtra("user",dto_user);
                          startActivity(intent);
                          a=0;
                          Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                          break;
                      }else {
                          a=2;
                      }
                  }else {
                      a=1;
                  }
              }
              if(a==1){
                  Toast.makeText(login.this, "Sai tên tài khoản", Toast.LENGTH_SHORT).show();
              } else if (a==2) {
                  Toast.makeText(login.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
              }
          }
      }
  });

}
}