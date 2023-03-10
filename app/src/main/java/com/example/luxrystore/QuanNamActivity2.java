package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.luxrystore.Adapter.PhuKienNamAdapter;
import com.example.luxrystore.DAO.AnhSanPhamDAO;
import com.example.luxrystore.DAO.SanPhamDAO;

public class QuanNamActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    SanPhamDAO sanPhamDAO;
    PhuKienNamAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_nam2);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new PhuKienNamAdapter(this);
        sanPhamDAO=new SanPhamDAO(this);
        adapter.setData(sanPhamDAO.getQuanNam());
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}