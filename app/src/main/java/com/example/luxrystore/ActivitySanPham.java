package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.luxrystore.Adapter.ImageAdapter;
import com.example.luxrystore.Adapter.SanPhamAdapter;
import com.example.luxrystore.DAO.AnhSanPhamDAO;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.Dialog.DialogAddHD;
import com.example.luxrystore.Dialog.DialogGioHang;
import com.example.luxrystore.R;
import com.example.luxrystore.model.AnhSanPham;
import com.example.luxrystore.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivitySanPham extends AppCompatActivity {
ImageView anhSp;
TextView tenSP,giaSP,soLuong,button;
TextView view;
RecyclerView rcy,rcy1;
Intent intent;
SanPhamDAO sanPhamDAO;
AnhSanPhamDAO anhSanPhamDAO;
    ArrayList<AnhSanPham> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        anhSp = findViewById(R.id.img_sanpham);
        tenSP = findViewById(R.id.tv_tensanpham);
        giaSP = findViewById(R.id.tv_giasanpham);
        soLuong = findViewById(R.id.tv_soLuongSP);
        button= findViewById(R.id.tv_button);
        view= findViewById(R.id.mota);
        anhSanPhamDAO=new AnhSanPhamDAO(this);

        rcy = findViewById(R.id.rcy);
        rcy1 = findViewById(R.id.rcy1);
        intent=getIntent();
        Bundle bundle =intent.getBundleExtra("SANPHAM");
        SanPham sanPham=(SanPham) bundle.getSerializable("SANPHAM");

        list=anhSanPhamDAO.getDataByMaSP(sanPham.getMaSP());
           if (!list.isEmpty()){
               byte[] bytes= Base64.decode(list.get(0).getBitmap(),Base64.DEFAULT);
               // Initialize bitmap
               Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
               // set bitmap on imageView
               anhSp.setImageBitmap(bitmap);
               Log.e("BUG1",list.get(0).getBitmap().toString());
           }


        tenSP.setText("Ten San Pham"+sanPham.getTenSP());
        giaSP.setText("Gia:"+String.valueOf( sanPham.getGiaBan()));
        soLuong.setText("So Luong:"+String.valueOf(sanPham.getSoLuong()));
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DialogGioHang dialogGioHang= new DialogGioHang(v.getContext(),sanPham);
        dialogGioHang.create();
        dialogGioHang.show();
    }
});


    ArrayList<String> listImg=new ArrayList<>();
    for (AnhSanPham  anhSanPham:list){
        listImg.add(anhSanPham.getBitmap());
    }
    ImageAdapter imageAdapter=new ImageAdapter(ActivitySanPham.this);
    rcy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
    imageAdapter.setList(listImg);
    rcy.setAdapter(imageAdapter);

    view.setText(sanPham.getMoTa());
    ArrayList<SanPham> list=new ArrayList<>();
    sanPhamDAO=new SanPhamDAO(this);
         SanPhamAdapter adapter1=new SanPhamAdapter(this);
        list=sanPhamDAO.getAoNu();
        LinearLayoutManager manager1=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rcy1=findViewById(R.id.rcy1);
        rcy1.setLayoutManager(manager1);
        adapter1.setData(list);
        rcy1.setAdapter(adapter1);
    }

}