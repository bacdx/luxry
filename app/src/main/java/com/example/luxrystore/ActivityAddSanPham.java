package com.example.luxrystore;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luxrystore.Adapter.ImageAdapter;
import com.example.luxrystore.DAO.AnhSanPhamDAO;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.model.AnhSanPham;
import com.example.luxrystore.model.SanPham;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ActivityAddSanPham extends AppCompatActivity {
    Button pick,btnCreate ;
    TextView textView ;
    EditText edName,edSoLuong,edGia,edMoTa;
    Spinner spMaLoai;
    RecyclerView recyclerView;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> stringArrayAdapter;
    SpinnerAdapter spinnerAdapter;
    ImageAdapter adapter;
    Toolbar toolbar ;
    String sImage;
    SanPhamDAO sanPhamDAO ;
    AnhSanPhamDAO anhSanPhamDAO;
    String name,maLoai,moTa;
    int soLuong;
    private  static final int docdulieu=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);
        edName=findViewById(R.id.tensp);
        spMaLoai=findViewById(R.id.masp);
        edSoLuong=findViewById(R.id.soluong);
        edGia=findViewById(R.id.edgia);
        edMoTa=findViewById(R.id.moTa);
        btnCreate=findViewById(R.id.btncreate);
        pick =findViewById(R.id.btn_pick);
        textView =findViewById(R.id.txt1);
        adapter=new ImageAdapter(this);
        recyclerView=findViewById(R.id.lv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4) );
        recyclerView.setAdapter(adapter);
        toolbar=findViewById(R.id.toolBar);
        toolbar.setTitle("Thêm Sản Phẩm");
        setSupportActionBar(toolbar);

       ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.array, android.R.layout.simple_spinner_dropdown_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaLoai.setAdapter(adapter);
        spMaLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (position){
                        case 0: maLoai="1";break;
                        case 1: maLoai="2";break;
                        case 2: maLoai="3";break;
                        case 3: maLoai="4";break;
                        case 4: maLoai="5";break;
                        case 5: maLoai="6";break;
                    }
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(ContextCompat.checkSelfPermission(ActivityAddSanPham.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ActivityAddSanPham.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},docdulieu);
        }
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                float gia;
                name=edName.getText().toString();
                moTa=edMoTa.getText().toString();
                soLuong=Integer.valueOf(edSoLuong.getText().toString());
                gia=Float.valueOf(edGia.getText().toString());
                if (name.isEmpty()||maLoai.isEmpty()||soLuong==0||gia==0){
                    Toast.makeText(v.getContext(),"Ban Phai Nhap Du Du Lieu",Toast.LENGTH_LONG);
                }else {
                    SanPham sanPham =new SanPham(name,maLoai,soLuong,gia,moTa);
                    CreateSP(sanPham,list,ActivityAddSanPham.this);
                    Toast.makeText(v.getContext(),"Them Thanh Cong",Toast.LENGTH_LONG);
                }
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);

                }
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"du lieu doc xong"),1);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&&resultCode == Activity.RESULT_OK){
            if (data.getClipData()!=null){
                int x = data.getClipData().getItemCount();
                for (int i = 0;i<x ; i++){
                    Uri uri=data.getClipData().getItemAt(i).getUri();
                    try {
                        Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        // initialize byte stream
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        // compress Bitmap
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                        byte[] bytes=stream.toByteArray();
                        sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
                        list.add(sImage);
                        adapter.setList(list);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    adapter.setList(list);
                }
                Log.e("BUG1",list.get(0).toString());

            }else if (data.getData()!=null){
                String anh = data.getData().getPath();
                Uri uri=Uri.parse(anh);
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    // initialize byte stream
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    // compress Bitmap
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] bytes=stream.toByteArray();
                    sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
                    list.add(sImage);
                    adapter.setList(list);
                }catch (IOException e){
                    e.printStackTrace();
               }

            }
        }
    }

    public  void CreateSP(SanPham sanPham , ArrayList<String> list, Context context){
        SanPhamDAO sanPhamDAO=new SanPhamDAO(context);
        AnhSanPhamDAO anhSanPhamDAO=new AnhSanPhamDAO(context);
        sanPhamDAO.insert(sanPham);
        sanPham=sanPhamDAO.getNewSanPham();
        AnhSanPham anhSanPham;
        for (int i=0;i<list.size();i++){
            anhSanPham=new AnhSanPham(sanPham.getMaSP(),list.get(i));
            if(anhSanPhamDAO.insert(anhSanPham)<0){
                Log.e("AAA","eror");
            }
        }
    }
    }
