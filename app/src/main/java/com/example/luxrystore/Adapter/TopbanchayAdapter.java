package com.example.luxrystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.DAO.LoaSanPhamDAO;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.R;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.SanPham;
import com.example.luxrystore.model.Top10;

import java.util.ArrayList;

public class TopbanchayAdapter extends RecyclerView.Adapter<TopbanchayAdapter.TopViewholder>{
    Context context ;
    ArrayList<Top10>list;
    SanPhamDAO sanPhamDAO;
    LoaSanPhamDAO loaSanPhamDAO;
    public TopbanchayAdapter(Context context) {
        this.context = context;
        sanPhamDAO=new SanPhamDAO(context);

    }

    public void setData(ArrayList<Top10> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TopViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_topbanchay,parent,false);
        return new TopViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewholder holder, int position) {
        Top10 cthd = list.get(position);
        if(cthd == null){
            return;
        }

        holder.ma.setText("Tên San Phảm: "+ cthd.getName());
        holder.soluong.setText("Số lượng: "+cthd.getSoLuong());
        holder.giaban.setText("Tổng Thu : "+cthd.getTongTien()+"VND");
    }

    @Override
    public int getItemCount() {
        if (list!=null) {
            return list.size();
        }
        return 0;
    }


    public class TopViewholder extends RecyclerView.ViewHolder {
        private TextView phukien , soluong,ma, giaban;

        public TopViewholder(@NonNull View v) {
            super(v);

            soluong = v.findViewById(R.id.tv_soluong);
            ma = v.findViewById(R.id.tv_ma);
            giaban = v.findViewById(R.id.tv_giaban);
        }
    }
}
