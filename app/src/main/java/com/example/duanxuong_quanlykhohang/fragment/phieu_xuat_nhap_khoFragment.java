package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duanxuong_quanlykhohang.Adapter.viewPageAdapter;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class phieu_xuat_nhap_khoFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    viewPageAdapter vpadapter;
    public phieu_xuat_nhap_khoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phieu_xuat_nhap_kho, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPage2);
        //
        vpadapter = new viewPageAdapter((FragmentActivity) getContext());// tạo đối tượng
        viewPager2.setAdapter(vpadapter);
        // Config tablayout
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Phiếu nhập kho");
                        break;
                    case 1:
                        tab.setText("Phiếu xuất kho");
                        break;
                }
            }
        }).attach();
    }
}