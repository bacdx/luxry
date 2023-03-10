package com.example.luxrystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.R;
import com.example.luxrystore.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.Holder> {
    Context context;
    ArrayList<NhanVien> list;
    NhanVienDAO dao;

    public NhanVienAdapter(Context context) {
        this.context = context;
        dao=new NhanVienDAO(context);
        list=dao.getAllData();
    }

    @NonNull
    @Override
    public NhanVienAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_qlnhanvien,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.Holder holder, int position) {
        NhanVien nhanVien=list.get(position);
        holder.stt.setText(nhanVien.getId());
        holder.name.setText(nhanVien.getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             dao.delete(nhanVien);
             list=dao.getAllData();
             notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView stt,name;
        ImageView img;
        public Holder(@NonNull View itemView) {
            super(itemView);
    stt = itemView.findViewById(R.id.item_stt);
    name = itemView.findViewById(R.id.item_namenv);
    img = itemView.findViewById(R.id.xoa);
        }
    }
}
