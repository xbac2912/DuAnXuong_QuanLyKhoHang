package com.example.duanxuong_quanlykhohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_tab_sp;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Frag_sp_ql extends Fragment {
    ViewPager2 pager2;
    TabLayout tabLayout;
    TabLayoutMediator mediator;
    Adapter_tab_sp adapter_tab_sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sp_ngung_kinh_doanh,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_tab_sp = new Adapter_tab_sp(this);
        pager2 = view.findViewById(R.id.viewPage2_sp);
        pager2.setAdapter(adapter_tab_sp);
        tabLayout = view.findViewById(R.id.tabLayout_sp);


         new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0){
                    tab.setText("Đang kinh doanh");
                }else {
                    tab.setText("Ngừng kinh doanh");
                }
            }
        }).attach();



    }
}
