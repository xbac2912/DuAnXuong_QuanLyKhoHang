package com.example.duanxuong_quanlykhohang.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_NhanSu;
import com.example.duanxuong_quanlykhohang.DAO.DAO_User;
import com.example.duanxuong_quanlykhohang.DTO.DTO_User;
import com.example.duanxuong_quanlykhohang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link qlNhanSuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class qlNhanSuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Dialog dialog;
    DAO_User user;
    DTO_User dto_user;
    Adapter_NhanSu nhanSu;
    RecyclerView list_NS;
    List<DTO_User> list;

    public qlNhanSuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment qlNhanSuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static qlNhanSuFragment newInstance(String param1, String param2) {
        qlNhanSuFragment fragment = new qlNhanSuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        user = new DAO_User(dialog.getContext());
        list = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taoDoiTuong();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ql_nhan_su, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnThem = view.findViewById(R.id.btn_themNhanSu);
        list_NS = view.findViewById(R.id.lst_nhansu);
        list = user.getAll();

        nhanSu = new Adapter_NhanSu(view.getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        list_NS.setLayoutManager(linearLayoutManager);
        list_NS.setAdapter(nhanSu);
        nhanSu.notifyDataSetChanged();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    public void openDialog_tb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (themNS() > 0) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

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

    String name;
    String userName;
    String pass;

    private void showDialogAdd() {
        dialog.setContentView(R.layout.dialog_them_nhan_su);
        EditText hoTen = dialog.findViewById(R.id.txtHoTenNhanSu);
        EditText taiKhoan = dialog.findViewById(R.id.txtTaiKhoanNS);
        EditText maKhau = dialog.findViewById(R.id.txtMatKhauNS);
        EditText maKhauNS = dialog.findViewById(R.id.txtRMatKhauNS);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.btnSaveThemNS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maKhau.getText().toString().equals(maKhauNS.getText().toString())) {
                    name = hoTen.getText().toString();
                    userName = taiKhoan.getText().toString();
                    pass = maKhauNS.getText().toString();
                    openDialog_tb();
                    dialog.dismiss();
                    nhanSu.notifyDataSetChanged();
                } else {
                    Toast.makeText(dialog.getContext(), "Mật khẩu nhập không trùng nhau ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.findViewById(R.id.btnCancelThemNS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public long themNS() {
        dto_user = new DTO_User();
        dto_user.setHoTen(name);
        dto_user.setNguoiDung(userName);
        dto_user.setMatKhau(pass);
        dto_user.setVaiTro(0);

        long a = user.AddRow(dto_user);
        list.clear();
        list.addAll(user.getAll());
        nhanSu.notifyDataSetChanged();

        return a;
    }

}