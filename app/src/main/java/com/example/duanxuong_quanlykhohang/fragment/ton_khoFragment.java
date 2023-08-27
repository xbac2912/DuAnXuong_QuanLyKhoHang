package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ton_khoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ton_khoFragment extends Fragment {
    DAO_khohang daoKhoHang;
    RecyclerView rcvTonKho;


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
        TextView soSpTon = view.findViewById(R.id.tv_soluong_ton);
        TextView soLuongTon = view.findViewById(R.id.tv_soluongmathang_ton);
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày từ EditText
                String tuNgayText = tuNgay.getText().toString();
                String denNgayText = denNgay.getText().toString();

                // Gọi phương thức tính toán và truy suất cơ sở dữ liệu
                int tongSoLuongMatHang = daoKhoHang.getTotalQuantityForProduct("maSP", tuNgayText, denNgayText);
                int tongSoLuongTon = daoKhoHang.getTotalProducts(tuNgayText, denNgayText);

                // Hiển thị kết quả lên TextView
                soSpTon.setText(String.valueOf(tongSoLuongMatHang));
                soLuongTon.setText(String.valueOf(tongSoLuongTon));
            }
        });
        return view;
    }
}