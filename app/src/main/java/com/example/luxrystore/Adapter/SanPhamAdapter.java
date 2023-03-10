package com.example.luxrystore.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.ActivitySanPham;
import com.example.luxrystore.DAO.AnhSanPhamDAO;
import com.example.luxrystore.R;
import com.example.luxrystore.model.AnhSanPham;
import com.example.luxrystore.model.SanPham;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHoder>{
    Context context;
    ArrayList<SanPham>list;
    ArrayList<AnhSanPham> listImg=new ArrayList<>();
    AnhSanPhamDAO anhSanPhamDAO;
    Intent intent;
    public SanPhamAdapter(Context context) {
        this.context = context;
        anhSanPhamDAO=new AnhSanPhamDAO(context);
    }

    public void setData(ArrayList<SanPham> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SanPhamAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHoder holder, int position) {
        SanPham sanPham=list.get(position);

        holder.tvName.setText("Ten San Pham: "+sanPham.getTenSP());
        holder.tvSoluong.setText("So Luong: "+sanPham.getSoLuong());
        holder.tvGia.setText("Gia Ban: "+sanPham.getGiaBan());
        holder.itemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("SANPHAM",sanPham);
                intent=new Intent(context, ActivitySanPham.class);
                intent.putExtra("SANPHAM",bundle);
                context.startActivity(intent);
            }
        });

                listImg=anhSanPhamDAO.getDataByMaSP(sanPham.getMaSP());
                if(listImg.size()<2){
                    holder.imgBig.setImageResource(R.drawable.phukiennam);
                    holder.imgSmall.setImageResource(R.drawable.aonu);
                }
                else{
                    for (int i=0;i<2;i++) {
                        byte[] bytes = Base64.decode(listImg.get(i).getBitmap(), Base64.DEFAULT);
                        // Initialize bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        // set bitmap on imageView
                        holder.imgBig.setImageBitmap(bitmap);
                        holder.imgSmall.setImageBitmap(bitmap);
                    }





            }



    }

    @Override
    public int getItemCount() {
        if(!list.isEmpty()){
           return list.size();
        }
        return 0;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        LinearLayout itemSanPham;
        ImageView imgBig,imgSmall;
        TextView tvName,tvSoluong,tvGia;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imgBig=itemView.findViewById(R.id.img);
            imgSmall=itemView.findViewById(R.id.logo);
            itemSanPham=itemView.findViewById(R.id.item_sanpham);
            tvName=itemView.findViewById(R.id.tensp);
            tvGia=itemView.findViewById(R.id.soluong);
            tvSoluong=itemView.findViewById(R.id.gia);
        }
    }
}
