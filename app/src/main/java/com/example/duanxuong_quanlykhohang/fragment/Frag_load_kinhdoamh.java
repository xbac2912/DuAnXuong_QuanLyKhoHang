package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duanxuong_quanlykhohang.QuanLyKhoHang;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.tabs.TabLayout;

public class Frag_load_kinhdoamh extends Fragment {
    private TabLayout tabLayout;

    public Frag_load_kinhdoamh() {
    }

    public Frag_load_kinhdoamh(TabLayout tabLayout){
        this.tabLayout=tabLayout;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_load, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QuanLyKhoHang quanLyKhoHang = (QuanLyKhoHang) getActivity();
        FragmentManager manager = quanLyKhoHang.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout, loadFrag()).commit();
    }

    private Frag_sp_ql loadFrag() {
        Frag_sp_ql frag_sp_ql = new Frag_sp_ql();
        Frag_load_kinhdoamh frag_load_kinhdoamh = new Frag_load_kinhdoamh();
        frag_load_kinhdoamh.setTabLayout(frag_sp_ql.getTabLayout());
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        if (tab != null) {
            tab.select();
        }
        return frag_sp_ql;
    }
}
