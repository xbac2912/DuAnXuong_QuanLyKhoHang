package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvQLKH.setLayoutManager(linearLayoutManager);
        rcvQLKH.setAdapter(adapter_khohang);
        adapter_khohang.notifyDataSetChanged();
        searchView = view.findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Searchlist = new ArrayList<>();
                if(query.length() > 0) {
                    for(int i = 0; i<list.size(); i++) {
                        if(list.get(i).getTenSP().toUpperCase().contains(query.toUpperCase())) {
                            DTO_KhoHang kh = new DTO_KhoHang();
                            kh.setMaSP(list.get(i).getMaSP());
                            kh.setTenSP(list.get(i).getTenSP());
                            kh.setSoluong(list.get(i).getSoluong());
                            kh.setGiaSP(list.get(i).getGiaSP());
                            kh.setTenLoai(list.get(i).getTenLoai());
                            Searchlist.add(kh);
                        }
                    }
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    rcvQLKH.setLayoutManager(layoutManager);
                    adapter_khohang = new Adapter_khohang(Searchlist, getContext());
                    rcvQLKH.setAdapter(adapter_khohang);
                    adapter_khohang.notifyDataSetChanged();
                } else {
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    rcvQLKH.setLayoutManager(layoutManager);
                    adapter_khohang = new Adapter_khohang(list, getContext());
                    rcvQLKH.setAdapter(adapter_khohang);
                    adapter_khohang.notifyDataSetChanged();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Searchlist = new ArrayList<>();
                if(newText.length() > 0) {
                    for(int i = 0; i<list.size(); i++) {
                        if(list.get(i).getTenSP().toUpperCase().contains(newText.toUpperCase())) {
                            DTO_KhoHang kh = new DTO_KhoHang();
                            kh.setMaSP(list.get(i).getMaSP());
                            kh.setTenSP(list.get(i).getTenSP());
                            kh.setSoluong(list.get(i).getSoluong());
                            kh.setGiaSP(list.get(i).getGiaSP());
                            kh.setTenLoai(list.get(i).getTenLoai());
                            Searchlist.add(kh);
                        }
                    }
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    rcvQLKH.setLayoutManager(layoutManager);
                    adapter_khohang = new Adapter_khohang(Searchlist, getContext());
                    rcvQLKH.setAdapter(adapter_khohang);
                    adapter_khohang.notifyDataSetChanged();
                } else {
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    rcvQLKH.setLayoutManager(layoutManager);
                    adapter_khohang = new Adapter_khohang(list, getContext());
                    rcvQLKH.setAdapter(adapter_khohang);
                    adapter_khohang.notifyDataSetChanged();
                }
                return false;
            }
        });
        return view;
    }

}