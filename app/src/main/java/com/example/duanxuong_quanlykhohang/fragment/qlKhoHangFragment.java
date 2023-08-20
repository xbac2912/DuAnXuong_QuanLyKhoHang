package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_khohang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.R;

import java.util.ArrayList;

public class qlKhoHangFragment extends Fragment {
    RecyclerView rcvQLKH;
    DAO_khohang dao_khohang;
    Adapter_khohang adapter_khohang;
    SearchView searchView;
    private ArrayList<DTO_KhoHang> list = new ArrayList<>();
    private ArrayList<DTO_KhoHang> Searchlist;
    public qlKhoHangFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_kho_hang, container, false);
        rcvQLKH = view.findViewById(R.id.rcv_khohang);
        dao_khohang = new DAO_khohang(getContext());
        list = dao_khohang.selectAll();
        adapter_khohang = new Adapter_khohang(list, getContext());
        rcvQLKH.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvQLKH.setAdapter(adapter_khohang);

        searchView = view.findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               adapter_khohang.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_khohang.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }


}