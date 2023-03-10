package com.example.luxrystore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.Adapter.TopbanchayAdapter;
import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DAO.cthdDAO;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.SanPham;
import com.example.luxrystore.model.Top10;

import java.util.ArrayList;

public class TopbanchayActivity extends AppCompatActivity {
    private ArrayList<Top10> list;
    private TopbanchayAdapter topbanchayAdapter;
    private RecyclerView recyclerView;
    private cthdDAO cthdDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbanchay);
        recyclerView = findViewById(R.id.recyclerview1);
        cthdDAO = new cthdDAO(TopbanchayActivity.this);

        list = cthdDAO.top10();
        topbanchayAdapter = new TopbanchayAdapter(this);
        topbanchayAdapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TopbanchayActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(topbanchayAdapter);
    }
}