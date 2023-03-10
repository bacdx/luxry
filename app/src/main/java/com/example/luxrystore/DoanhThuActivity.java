package com.example.luxrystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.luxrystore.Adapter.FragmentAdapter;
import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.model.NhanVien;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoanhThuActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager2 viewPager2;
FragmentAdapter adapter;

Intent intent;
NhanVien nhanVien;
NhanVienDAO dao;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        tabLayout=findViewById(R.id.tablayout);
        viewPager2=findViewById(R.id.viewpage2);
        adapter=new FragmentAdapter(this);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        viewPager2.setAdapter(adapter);
        intent=getIntent();
        dao=new NhanVienDAO(this);
        String name=intent.getStringExtra("USER");
        nhanVien=dao.getDataByUser(name);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:tab.setText("Tổng Thu");break;
                    case 1:tab.setText("Hóa Đơn Nhân Viên");
                }
            }
        }).attach();

    }
}