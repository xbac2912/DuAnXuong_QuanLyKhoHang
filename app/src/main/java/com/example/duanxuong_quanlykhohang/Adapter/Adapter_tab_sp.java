package com.example.duanxuong_quanlykhohang.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duanxuong_quanlykhohang.fragment.Frag_sp_ngungkinhdoanh;
import com.example.duanxuong_quanlykhohang.fragment.qlSanPhamFragment;

public class Adapter_tab_sp extends FragmentStateAdapter {
    int page = 2;

    public Adapter_tab_sp(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new qlSanPhamFragment();
        } else {
            return new Frag_sp_ngungkinhdoanh();
        }
    }

    @Override
    public int getItemCount() {
        return page;
    }
}
