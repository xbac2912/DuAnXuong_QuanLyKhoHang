package com.example.duanxuong_quanlykhohang.fragment;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp_Phieu_Xuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link phieu_xuat_khoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class phieu_xuat_khoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Dialog dialog;
    DAO_PhieuXuat daoPhieuXuat;
    DAO_khohang dao_khohang;
    DTO_PhieuXuat dtoPhieuXuat;
    RecyclerView rcvPhieuXuat;
    List<DTO_PhieuXuat> list;
    List<DTO_KhoHang> listkho;
    Adapter_sp_Phieu_Xuat adapterSpPhieuXuat;

    public phieu_xuat_khoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment phieu_xuat_khoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static phieu_xuat_khoFragment newInstance(String param1, String param2) {
        phieu_xuat_khoFragment fragment = new phieu_xuat_khoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        daoPhieuXuat = new DAO_PhieuXuat(dialog.getContext());
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phieu_xuat_kho, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton floatThem = view.findViewById(R.id.flb_themPhieuXuat);
        rcvPhieuXuat = view.findViewById(R.id.rcv_phieuxuatkho);
        daoPhieuXuat = new DAO_PhieuXuat(getContext());
        dao_khohang = new DAO_khohang(getContext());
        list = daoPhieuXuat.layDanhSachPhieuXuat();
        listkho = dao_khohang.selectAll();
        adapterSpPhieuXuat = new Adapter_sp_Phieu_Xuat(view.getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rcvPhieuXuat.setLayoutManager(linearLayoutManager);
        rcvPhieuXuat.setAdapter(adapterSpPhieuXuat);
        adapterSpPhieuXuat.notifyDataSetChanged();
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void showDialogAdd() {
        dialog.setContentView(R.layout.dialog_phieu_xuat_kho);
        // Ánh xạ
        EditText txtMaSp = dialog.findViewById(R.id.txtSanPhamXuat);
        EditText txtSoLuong = dialog.findViewById(R.id.txtSoLuongXuat);
        EditText txtNgayXuat = dialog.findViewById(R.id.txtNgayXuat);
        CheckBox chkDaXuat = dialog.findViewById(R.id.chkXacNhanTm);
        Button btnLuu = dialog.findViewById(R.id.btnSavex);
        Button btnThoat = dialog.findViewById(R.id.btnCancelx);
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        //
        txtNgayXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtNgayXuat.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSanPham = txtMaSp.getText().toString();
                String soLuongStr = txtSoLuong.getText().toString();
                int gia = getGia(maSanPham);
                String ngayXuatStr = txtNgayXuat.getText().toString();
                boolean daXuatKho = chkDaXuat.isChecked();
                if (!maSanPham.isEmpty() && !soLuongStr.isEmpty() && !ngayXuatStr.isEmpty()) {
                    int soLuong = Integer.parseInt(soLuongStr);
                    if (daoPhieuXuat.chekSoLuong(maSanPham, soLuong)) {
                        // Chuyển đổi ngày thành định dạng phù hợp, ví dụ: "YYYY-MM-DD"
                        String ngayXuat = chuyenDoiNgayPhuHop(ngayXuatStr);
                        daoPhieuXuat.themPhieuXuat(maSanPham, soLuong, ngayXuat, daXuatKho, gia);
                        if (daXuatKho) {
                            daoPhieuXuat.capNhatSoLuongTonSanPham2(maSanPham, Integer.parseInt(soLuongStr), daXuatKho);
                        }
                        dialog.dismiss();
                        // Cập nhật lại RecyclerView để hiển thị phiếu xuất mới
                        capNhatRecyclerView();
                    } else {
                        Toast.makeText(getContext(), "Không đủ số lượng xuất", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private int getGia(String maSP) {
        int gia = 0;
        listkho.clear();
        listkho.addAll(dao_khohang.selectAll());
        for (DTO_KhoHang l : listkho) {
            if (maSP.equals(l.getMaSP())) {
                gia = l.getGiaSP();
                Log.d(TAG, "getGia: " + gia);
                Log.d(TAG, "getGia: " + l.getGiaSP());
                break;
            }
        }
        return gia;
    }


    private String chuyenDoiNgayPhuHop(String ngayXuatStr) {
        String[] ngayThangNam = ngayXuatStr.split("-");
        String nam = ngayThangNam[2];
        String thang = ngayThangNam[1];
        String ngay = ngayThangNam[0];
        return nam + "-" + thang + "-" + ngay;
    }

    // Cập nhật RecyclerView để hiển thị phiếu xuất mới
    private void capNhatRecyclerView() {
        // Cập nhật lại danh sách phiếu xuất từ CSDL
        list = daoPhieuXuat.layDanhSachPhieuXuat();
        // Cập nhật lại adapter của RecyclerView
        adapterSpPhieuXuat = new Adapter_sp_Phieu_Xuat(requireContext(), list);
        rcvPhieuXuat.setAdapter(adapterSpPhieuXuat);
        adapterSpPhieuXuat.notifyDataSetChanged();
    }

}