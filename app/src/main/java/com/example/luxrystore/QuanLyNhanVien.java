package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.luxrystore.Adapter.HoaDonAdapter;
import com.example.luxrystore.Adapter.NhanVienAdapter;

public class QuanLyNhanVien extends AppCompatActivity {
    NhanVienAdapter adapter;
    RecyclerView recyclerView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        intent=getIntent();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new NhanVienAdapter(this);
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}