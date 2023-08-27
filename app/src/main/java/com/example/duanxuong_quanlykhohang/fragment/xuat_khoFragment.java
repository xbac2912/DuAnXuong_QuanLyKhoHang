package com.example.duanxuong_quanlykhohang.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_ThongKeXuatKho;
import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp_Phieu_Xuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DTO.DTO_ThongKe_PhieuXuat;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xuat_khoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xuat_khoFragment extends Fragment {
    RecyclerView rcvXuatKho;
    DAO_PhieuXuat daoPhieuXuat;
    List<DTO_ThongKe_PhieuXuat> list;
    Adapter_ThongKeXuatKho adapterTkPhieuXuat;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public xuat_khoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xuat_khoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static xuat_khoFragment newInstance(String param1, String param2) {
        xuat_khoFragment fragment = new xuat_khoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xuat_kho, container, false);
        // Inflate the layout for this fragment
        rcvXuatKho = view.findViewById(R.id.rcv_xuatKho);
        EditText tuNgay = view.findViewById(R.id.edt_tuNgay);
        EditText denNgay = view.findViewById(R.id.edt_denNgay);
        Button btnThongKe = view.findViewById(R.id.btn_thongKe_xuat);
        TextView soSpTon = view.findViewById(R.id.tv_soluong_xuat);
        TextView soLuongTon = view.findViewById(R.id.tv_soluongmathang_xuat);
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy giá trị ngày từ EditText
                String tuNgayValue = tuNgay.getText().toString();
                String denNgayValue = denNgay.getText().toString();
                // Hiển thị các phiếu xuất nằm trong khoảng thời gian tìm kiếm
                daoPhieuXuat = new DAO_PhieuXuat(getContext());
                list = daoPhieuXuat.layDanhSachPhieuXuatTK(tuNgayValue, denNgayValue);
                adapterTkPhieuXuat = new Adapter_ThongKeXuatKho(view.getContext(), list);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                rcvXuatKho.setLayoutManager(linearLayoutManager);
                rcvXuatKho.setAdapter(adapterTkPhieuXuat);
                adapterTkPhieuXuat.notifyDataSetChanged();

                // Tính toán số sản phẩm được xuất khỏi kho và tổng số lượng sản phẩm đã xuất
                int soSanPhamXuatKho = daoPhieuXuat.tinhSoSanPhamXuatKho(tuNgayValue, denNgayValue);
                int tongSoLuongXuat = daoPhieuXuat.tinhTongSoLuongXuat(tuNgayValue, denNgayValue);

                // Hiển thị số sản phẩm được xuất khỏi kho và tổng số lượng sản phẩm đã xuất
                soSpTon.setText(String.valueOf(soSanPhamXuatKho));
                soLuongTon.setText(String.valueOf(tongSoLuongXuat));
            }
        });

        return view;
    }
}