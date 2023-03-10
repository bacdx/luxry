package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.luxrystore.Adapter.PhuKienNamAdapter;
import com.example.luxrystore.Adapter.SanPhamAdapter;
import com.example.luxrystore.DAO.AnhSanPhamDAO;
import com.example.luxrystore.DAO.SanPhamDAO;

public class ActivityPhuKienNu extends AppCompatActivity {

    RecyclerView recyclerView;
    AnhSanPhamDAO anhSanPhamDAO;
    SanPhamDAO sanPhamDAO;
    PhuKienNamAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phu_kien_nu);
        LinearLayoutManager manager=new LinearLayoutManager(this);

        adapter=new PhuKienNamAdapter(this);
        sanPhamDAO=new SanPhamDAO(this);
        adapter.setData(sanPhamDAO.getPhuKienNu());
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}