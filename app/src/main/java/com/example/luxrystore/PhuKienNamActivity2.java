package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.luxrystore.Adapter.PhuKienNamAdapter;
import com.example.luxrystore.DAO.SanPhamDAO;

public class PhuKienNamActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;

    SanPhamDAO sanPhamDAO;
    PhuKienNamAdapter adapter;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phu_kien_nam2);


        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new PhuKienNamAdapter(this);
        sanPhamDAO=new SanPhamDAO(this);
        adapter.setData(sanPhamDAO.getPhuKienNam());
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }
}