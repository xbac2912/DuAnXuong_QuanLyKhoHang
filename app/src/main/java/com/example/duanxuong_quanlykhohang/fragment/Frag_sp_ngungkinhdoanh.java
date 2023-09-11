package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_sp_ngungkinhdoanh#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_sp_ngungkinhdoanh extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Adapter_sp adapter_sp;
    RecyclerView rc_list;
    List<DTO_sp> list;
    DAO_sp dao_sp;

    public Frag_sp_ngungkinhdoanh() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_sp_ngungkinhdoanh newInstance(String param1, String param2) {
        Frag_sp_ngungkinhdoanh fragment = new Frag_sp_ngungkinhdoanh();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sanpham_ngungkinhdaonh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rc_list = view.findViewById(R.id.rcv_sanpham_ngung);
        dao_sp = new DAO_sp(getContext());
        list = dao_sp.getAll(1);
        adapter_sp = new Adapter_sp(view.getContext(), list, 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setAdapter(adapter_sp);
        rc_list.setLayoutManager(linearLayoutManager);
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadData() {
        adapter_sp.notifyDataSetChanged();
    }
}