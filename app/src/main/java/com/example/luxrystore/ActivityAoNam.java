package com.example.luxrystore;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.Adapter.PhuKienNamAdapter;
import com.example.luxrystore.Adapter.SanPhamAdapter;
import com.example.luxrystore.DAO.SanPhamDAO;

public class ActivityAoNam extends AppCompatActivity {
    PhuKienNamAdapter adapter;
    RecyclerView recyclerView;
    SanPhamDAO sanPhamDAO;
    public ActivityAoNam() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ao_nam);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new PhuKienNamAdapter(this);
        sanPhamDAO=new SanPhamDAO(this);
        adapter.setData(sanPhamDAO.getAoNam());
        recyclerView=findViewById(R.id.lv_aonam);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}