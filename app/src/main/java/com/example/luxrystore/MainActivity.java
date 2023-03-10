package com.example.luxrystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luxrystore.Adapter.SanPhamAdapter;
import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.Dialog.DialogAddNV;
import com.example.luxrystore.model.NhanVien;
import com.example.luxrystore.model.SanPham;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SanPhamAdapter adapter,adapter1,adapter2,adapter3,adapter4,adapter5;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5;
    ArrayList<SanPham> listAoNam,listQuanNam,listQuanNu,listPhuKienNam,listPhuKienNu;
    ArrayList<SanPham> listAoNu;
    SanPhamDAO sanPhamDAO;
    NavigationView navigationView;
    Intent intent;
    TextView textView;
    SearchView searchView;
    NhanVienDAO dao;
    ImageView imgNAoNam,imgAoNu,imgQuanNam,imgQuanNu,imgPhuKienNam,imgPhuKienNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigationView);
        dao=new NhanVienDAO(this);
        intent=getIntent();
        String name=intent.getStringExtra("USER");
        NhanVien nhanVien=dao.getDataByUser(name);
        View headerView=navigationView.getHeaderView(0);
        textView=headerView.findViewById(R.id.headername);
        textView.setText(nhanVien.getName());
        imgNAoNam=findViewById(R.id.next_aonam);
        imgAoNu=findViewById(R.id.next_aonu);
        imgQuanNam=findViewById(R.id.next_quannam);
        imgQuanNu=findViewById(R.id.next_quannu);
        imgPhuKienNam=findViewById(R.id.next_phukiennam);
        imgPhuKienNu=findViewById(R.id.next_phulkiennu);
//        searchView=findViewById(R.id.search);



        // lay menu nav
        MenuItem item = navigationView.getMenu().findItem(R.id.ql_nhanvien);
        MenuItem item1 = navigationView.getMenu().findItem(R.id.ql_sanpham);
        MenuItem item2 = navigationView.getMenu().findItem(R.id.ngd_themnv);
        if(!name.equals("admin")){
            item.setEnabled(false);
            item1.setEnabled(false);
            item2.setEnabled(false);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ql_hoadon:
                        intent=new Intent(MainActivity.this,QuanLyHoaDon.class);
                        intent.putExtra("USER",name);
                        startActivity(intent);
                        break;
                    case R.id.ql_nhanvien:
                        if (name.equals("admin"))
                        {
                            intent=new Intent(MainActivity.this,QuanLyNhanVien.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Chuc Nang Danh Cho ADMIN",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.ql_sanpham:
                        intent=new Intent(MainActivity.this,ActivityAddSanPham.class);
                        startActivity(intent);
                        break;
                    case  R.id.tk_top:
                        startActivity(new Intent(MainActivity.this,TopbanchayActivity.class));
                        break;
                    case  R.id.tk_doanhThu:
                        intent=new Intent(MainActivity.this,DoanhThuActivity.class);
                        intent.putExtra("USER",nhanVien.getUser());
                        startActivity(intent);
                        break;
                    case  R.id.ngd_themnv:
                        DialogAddNV dialogAddNV=new DialogAddNV(MainActivity.this);
                        dialogAddNV.create();
                        dialogAddNV.show();
                        break;
                    case  R.id.ngd_doiMK:
                        intent=new Intent(MainActivity.this,DoiMKActivity.class);
                        intent.putExtra("USER",nhanVien.getUser());
                        startActivity(intent);
                        break;
                    case  R.id.ngd_dangXuat:
                        intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;


                }
                return true;
            }
        });


        sanPhamDAO=new SanPhamDAO(MainActivity.this);
        listAoNam=sanPhamDAO.getAoNam();
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        adapter=new SanPhamAdapter(MainActivity.this);
        recyclerView=findViewById(R.id.lv);


       Thread background =new Thread(new Runnable() {
           @Override
           public void run() {

               adapter.setData(listAoNam);
               adapter.notifyDataSetChanged();
               recyclerView.setLayoutManager(manager);
               recyclerView.setAdapter(adapter);
           }
       });
       background.run();



        adapter1=new SanPhamAdapter(MainActivity.this);
        listAoNu=sanPhamDAO.getAoNu();
        LinearLayoutManager manager1=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView1=findViewById(R.id.lv1);
        recyclerView1.setLayoutManager(manager1);
        adapter1.setData(listAoNu);
        recyclerView1.setAdapter(adapter1);

        adapter2=new SanPhamAdapter(MainActivity.this);
        listQuanNam=sanPhamDAO.getQuanNam();
        LinearLayoutManager manager2=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView2=findViewById(R.id.lv2);
        recyclerView2.setLayoutManager(manager2);
        adapter2.setData(listAoNu);
        recyclerView2.setAdapter(adapter2);

        adapter3=new SanPhamAdapter(MainActivity.this);
        listQuanNu=sanPhamDAO.getQuanNu();
        LinearLayoutManager manager3=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView3=findViewById(R.id.lv3);
        recyclerView3.setLayoutManager(manager3);
        adapter3.setData(listQuanNu);
        recyclerView3.setAdapter(adapter3);

        adapter4=new SanPhamAdapter(MainActivity.this);
        listPhuKienNam=sanPhamDAO.getPhuKienNam();
        LinearLayoutManager manager4=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView4=findViewById(R.id.lv4);
        recyclerView4.setLayoutManager(manager4);
        adapter4.setData(listPhuKienNam);
        recyclerView4.setAdapter(adapter4);

        adapter5=new SanPhamAdapter(MainActivity.this);
        listPhuKienNu=sanPhamDAO.getAoNu();
        LinearLayoutManager manager5=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView5=findViewById(R.id.lv5);
        recyclerView5.setLayoutManager(manager5);
        adapter5.setData(listPhuKienNu);
        recyclerView5.setAdapter(adapter5);














        imgNAoNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,ActivityAoNam.class);
                startActivity(intent);
            }
        });
        imgAoNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AoNuActivity2.class);
                startActivity(intent);
            }
        });
        imgQuanNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,QuanNamActivity2.class);
                startActivity(intent);
            }
        });
        imgQuanNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,ActivityQuanNu.class);
                startActivity(intent);
            }
        });
        imgPhuKienNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,PhuKienNamActivity2.class);
                startActivity(intent);
            }
        });
        imgPhuKienNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,ActivityPhuKienNu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAoNam=sanPhamDAO.getAoNam();
        listAoNu=sanPhamDAO.getAoNu();
        adapter.setData(listAoNam);
        adapter1.setData(listAoNu);
    }
}