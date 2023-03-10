package com.example.luxrystore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.luxrystore.Adapter.HoaDonAdapter;
import com.example.luxrystore.Adapter.PhuKienNamAdapter;
import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DAO.cthdDAO;
import com.example.luxrystore.Dialog.DialogAddHD;
import com.example.luxrystore.Dialog.DialogGioHang;
import com.example.luxrystore.model.HoaDon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuanLyHoaDon extends AppCompatActivity {
public HoaDonAdapter adapter;
HoaDonDAO dao;
RecyclerView recyclerView;
ImageView img_search;
    private int day,month,year;
    private TextInputEditText ed_tuNgay;

private FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hoa_don);
        button=findViewById(R.id.addflo);
        dao=new HoaDonDAO(this);
        ArrayList<HoaDon> list = dao.getAllData();
        img_search = findViewById(R.id.img_search);
        ed_tuNgay = findViewById(R.id.ed_DT_tuNgay);
//        ed_tuNgay.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//             adapter.getFilter().filter(ed_tuNgay.getText().toString());
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter.getFilter().filter(ed_tuNgay.getText().toString().trim());
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                adapter.getFilter().filter(ed_tuNgay.getText().toString().trim());
//            }
//        });
    img_search.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             adapter.getFilter().filter(ed_tuNgay.getText().toString());
         }
     });




        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new HoaDonAdapter(this);
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        Intent intent=getIntent();
        String name=intent.getStringExtra("USER");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddHD dialogAddHD=new DialogAddHD(v.getContext(),name);
                dialogAddHD.create();
                dialogAddHD.show();
                adapter.notifyDataSetChanged();
            }
        });

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ed_tuNgay.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year,month,dayOfMonth);
                    ed_tuNgay.setText(simpleDateFormat.format(calendar.getTime()));
                }
            },year,month,day);

            datePickerDialog.show();

//            adapter.setList(dao.getDataByDate(day+"/"+month+"/"+year));
        });

    }

}