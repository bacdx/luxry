package com.example.luxrystore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.ActivitySanPham;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.Dialog.DialogGioHang;
import com.example.luxrystore.R;
import com.example.luxrystore.model.SanPham;

import java.util.ArrayList;

public class PhuKienNamAdapter extends RecyclerView.Adapter<PhuKienNamAdapter.Holder> {
    SanPhamDAO sanPhamDAO;
    Context context;
    Intent intent;
    ArrayList<SanPham> list =new ArrayList<>();
    public PhuKienNamAdapter(Context context) {
        this.context=context;
        sanPhamDAO=new SanPhamDAO(context);

    }
    public void setData(ArrayList<SanPham> list){
        this.list=list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PhuKienNamAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.item_list,parent,false);
        return new PhuKienNamAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhuKienNamAdapter.Holder holder, int position) {
        SanPham sp=list.get(position);
        if (sp==null){
            return;
        }

        holder.tenPKNam.setText("Ten San Pham: "+sp.getTenSP());
        holder.soLuong.setText("So Luong: "+sp.getSoLuong());
        holder.gia.setText("Gia Ban: "+sp.getGiaBan());
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamDAO.delete(sp);
                list=sanPhamDAO.getPhuKienNam();
                notifyDataSetChanged();
            }
        });

        holder.itemPKNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("SANPHAM",sp);
                intent=new Intent(context, ActivitySanPham.class);
                intent.putExtra("SANPHAM",bundle);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CardView itemPKNam;
        ImageView imgPKNam,imgxoa;
        TextView tenPKNam,soLuong,gia;
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemPKNam=itemView.findViewById(R.id.item_phukiennam);
            imgPKNam=itemView.findViewById(R.id.imgpknam);
            tenPKNam=itemView.findViewById(R.id.tv_ten);
            soLuong=itemView.findViewById(R.id.tvsol);
            gia=itemView.findViewById(R.id.gia);
            imgxoa=itemView.findViewById(R.id.imgxoa);
        }
    }
}
