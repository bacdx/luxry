package com.example.luxrystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.DAO.cthdDAO;

import com.example.luxrystore.model.GioHang;
import com.example.luxrystore.R;
import com.example.luxrystore.model.SanPham;

public class CTHDadapter extends RecyclerView.Adapter<CTHDadapter.Holder> {
   cthdDAO dao;
Context context;
    public CTHDadapter(Context context) {
        this.context=context;
        dao=new cthdDAO(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_hdct,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        GioHang gioHang=GioHang.ListGioHang.list.get(position);
        SanPham sanPham=gioHang.getSanPham();
        int soLuong=gioHang.getSoLuong();
        holder.tvSTT.setText("0");
        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvSoLuong.setText(String.valueOf(soLuong));
        holder.tvGia.setText(String.valueOf(gioHang.getThanhTien()));
   }

    @Override
    public int getItemCount() {
        return GioHang.ListGioHang.list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvSTT,tvTenSP,tvSoLuong,tvGia;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvSTT=itemView.findViewById(R.id.stt);
            tvTenSP=itemView.findViewById(R.id.tensp);
            tvSoLuong=itemView.findViewById(R.id.sl);
            tvGia=itemView.findViewById(R.id.gia);
        }
    }
}
