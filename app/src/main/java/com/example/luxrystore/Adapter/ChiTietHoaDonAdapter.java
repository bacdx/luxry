package com.example.luxrystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.DAO.cthdDAO;
import com.example.luxrystore.R;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.SanPham;

import java.util.ArrayList;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.Holder>{
    cthdDAO dao;
    SanPhamDAO sanPhamDAO;
    Context context;
    ArrayList<CTHD> list ;
    public ChiTietHoaDonAdapter(Context context,String maHD) {
        this.context=context;
        dao=new cthdDAO(context);
        sanPhamDAO=new SanPhamDAO(context);
        list=dao.getDataByMaHD(maHD);
    }

    @NonNull
    @Override
    public ChiTietHoaDonAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_hdct,parent,false);
        return new ChiTietHoaDonAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietHoaDonAdapter.Holder holder, int position) {

      CTHD cthd=list.get(position);
      SanPham sanPham=sanPhamDAO.getDataByID(cthd.getMaSP());
        int soLuong=cthd.getSoLuong();
        holder.tvSTT.setText(String.valueOf(position+1));
        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvSoLuong.setText(String.valueOf(soLuong));
        holder.tvGia.setText(String.valueOf(cthd.getThanhTien()));
    }

    @Override
    public int getItemCount() {
        return list.size();
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
