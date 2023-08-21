package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_sp_ngungkinhdoanh extends Fragment {
    Adapter_sp adapter_sp;
    RecyclerView rc_list;
    List<DTO_sp > list;
    DAO_sp dao_sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sanpham_ngungkinhdaonh,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_sanpham_ngung);
        dao_sp = new DAO_sp(getContext());
        list = dao_sp.getAll(1);
        adapter_sp = new Adapter_sp(view.getContext(),list,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setAdapter(adapter_sp);
        rc_list.setLayoutManager(linearLayoutManager);
    }
    public List<DTO_sp> loc(List<DTO_sp> list){
        List<DTO_sp> listCheck = new ArrayList<>();
        for (DTO_sp sp :list){
            if(sp.getTrangthai()==1){
                listCheck.add(sp);
            }
        }
        return listCheck;
    }
}
