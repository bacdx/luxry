package com.example.luxrystore.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.luxrystore.Fragment.DoanhThuNhanVienFragment;
import com.example.luxrystore.Fragment.QuanLyDoanhThuFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new QuanLyDoanhThuFragment();
            case 1:return new DoanhThuNhanVienFragment();
        }
        return  new  QuanLyDoanhThuFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
