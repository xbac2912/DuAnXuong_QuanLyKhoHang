package com.example.duanxuong_quanlykhohang.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_ThongKetonkho;
import com.example.duanxuong_quanlykhohang.DAO.DAO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_PhieuXuat;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ton_khoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ton_khoFragment extends Fragment {
    RecyclerView rcvTonKho;
    DAO_PhieuXuat daoPhieuXuat;
    List<DTO_PhieuXuat> list_px;
    DAO_sp_Phieu_Nhap dao_sp_phieu_nhap;
    List<DTO_sp_Phieu_Nhap> list_pn;
    Calendar lich;
    int soLuongXuat = 0;
    int soLuongNhap = 0;
    Adapter_ThongKetonkho adapter_thongKetonkho;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ton_khoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ton_khoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ton_khoFragment newInstance(String param1, String param2) {
        ton_khoFragment fragment = new ton_khoFragment();
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
        View view = inflater.inflate(R.layout.fragment_ton_kho, container, false);
        // Inflate the layout for this fragment
        rcvTonKho = view.findViewById(R.id.rcv_tonkho);
        EditText tuNgay = view.findViewById(R.id.edt_tuNgay_ton);
        EditText denNgay = view.findViewById(R.id.edt_denNgay_ton);
        Button btnThongKe = view.findViewById(R.id.btn_thongKe_ton);
        TextView soSpnhap = view.findViewById(R.id.tv_soluong_nhap_);
        TextView soSpxuat = view.findViewById(R.id.tv_soluong_xuat_);
        TextView soSpTon = view.findViewById(R.id.tv_soluong_ton);
        TextView soLuongTon = view.findViewById(R.id.tv_soluongmathang_ton);
        lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        dao_sp_phieu_nhap = new DAO_sp_Phieu_Nhap(getContext());
        daoPhieuXuat = new DAO_PhieuXuat(getContext());
        list_pn = dao_sp_phieu_nhap.getAll();
        adapter_thongKetonkho = new Adapter_ThongKetonkho(getContext(), list_pn);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvTonKho.setLayoutManager(manager);
        rcvTonKho.setAdapter(adapter_thongKetonkho);
        list_px = daoPhieuXuat.layDanhSachPhieuXuat_daxuat();
        soLuongXuat = 0;
        soLuongNhap = 0;
        for (DTO_sp_Phieu_Nhap pn : list_pn) {
            soLuongNhap += pn.getSoLuong();
        }
        for (DTO_PhieuXuat px : list_px) {
            soLuongXuat += px.getSoLuong();
        }
        // Gọi phương thức tính toán và truy suất cơ sở dữ liệu
        int tongSoLuongTon = soLuongNhap - soLuongXuat;
        int tongSoMatHangTon = demSO();
        // Hiển thị kết quả lên TextView
        soSpnhap.setText(soLuongNhap + "");
        soSpxuat.setText(soLuongXuat + "");
        soSpTon.setText(String.valueOf(tongSoLuongTon));
        soLuongTon.setText(String.valueOf(tongSoMatHangTon));
        tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tuNgay.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });
        denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        denNgay.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày từ EditText
                String tuNgayText = tuNgay.getText().toString();
                String denNgayText = denNgay.getText().toString();
                list_pn.clear();
                list_pn.addAll(dao_sp_phieu_nhap.getthongke(tuNgayText, denNgayText));
                adapter_thongKetonkho.notifyDataSetChanged();
                list_px = daoPhieuXuat.layDanhSachPhieuXuatTK(tuNgayText, denNgayText);
                soLuongXuat = 0;
                soLuongNhap = 0;
                for (DTO_PhieuXuat px : list_px) {
                    soLuongXuat += px.getSoLuong();
                }
                for (DTO_sp_Phieu_Nhap pn : list_pn) {
                    soLuongNhap += pn.getSoLuong();
                }
                // Gọi phương thức tính toán và truy suất cơ sở dữ liệu
                int tongSoLuongTon = soLuongNhap - soLuongXuat;
                int tongSoMatHangTon = demSO();
                // Hiển thị kết quả lên TextView
                soSpnhap.setText(soLuongNhap + "");
                soSpxuat.setText(soLuongXuat + "");
                soSpTon.setText(String.valueOf(tongSoLuongTon));
                soLuongTon.setText(String.valueOf(tongSoMatHangTon));
            }
        });
        return view;
    }

    private int demSO() {
        Set<String> so = new HashSet<>();
        for (DTO_sp_Phieu_Nhap sp : list_pn) {
            so.add(sp.getMaSanPham());
        }
        return so.size();
    }
}