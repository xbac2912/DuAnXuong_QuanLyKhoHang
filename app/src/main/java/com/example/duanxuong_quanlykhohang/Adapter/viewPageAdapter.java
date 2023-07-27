package com.example.duanxuong_quanlykhohang.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duanxuong_quanlykhohang.fragment.phieu_nhap_khoFragment;
import com.example.duanxuong_quanlykhohang.fragment.phieu_xuat_khoFragment;

public class viewPageAdapter extends FragmentStateAdapter {
    public viewPageAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
    switch (position){
        case 0: return new phieu_nhap_khoFragment();
        case 1: return new phieu_xuat_khoFragment();
    }
        return new phieu_nhap_khoFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
