package com.example.luxrystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.DAO.cthdDAO;
import com.example.luxrystore.Dialog.DialogAddHD;
import com.example.luxrystore.R;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.HoaDon;
import com.example.luxrystore.model.NhanVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.Holder> implements Filterable {
    public void setList(ArrayList<HoaDon> list) {
        this.list = list;
    }

    private ArrayList<HoaDon> list=new ArrayList<>();
    private ArrayList<HoaDon> listTemp;
    Context context;
    HoaDonDAO dao;
    cthdDAO cthdDAO;
   ChiTietHoaDonAdapter cthDadapter;
    NhanVienDAO nhanVienDAO;
    public HoaDonAdapter(ArrayList<HoaDon> list) {
        this.list = list;
        this.listTemp = list;
    }

    public HoaDonAdapter(Context context) {
        this.context = context;
        dao=new HoaDonDAO(context);
        cthdDAO=new cthdDAO(context);
        nhanVienDAO=new NhanVienDAO(context);
        list=dao.getAllData();
        listTemp = dao.getAllData();
    }
    public void setData(ArrayList<HoaDon>list ){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoaDonAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_hoadon,parent,false);
        return new HoaDonAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.Holder holder, int position) {
        SimpleDateFormat sfm= new SimpleDateFormat("dd/MM/yyyy");
        HoaDon hoaDon=list.get(position);
  if (list==null){
      return;
  }else {
      holder.tvDate.setText("Ngày Lập: "+sfm.format(hoaDon.getNgayLap()));
      holder.tvID.setText("Số mã: "+hoaDon.getMaHD());
      holder.itemHoaDon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              NhanVien nhanVien=nhanVienDAO.getDataByID(hoaDon.getMaNV());
              DialogAddHD dialogAddHD=new DialogAddHD(context);
              dialogAddHD.tvDate.setText("Ngày Lập: "+sfm.format(hoaDon.getNgayLap()));
              dialogAddHD.tvMaNV.setText("Mã NV: "+nhanVien.getName());
              dialogAddHD.tvName.setText(hoaDon.getTenKH());
              dialogAddHD.tvName.setEnabled(false);
              dialogAddHD.tvMaHD.setText("Mã HD"+hoaDon.getMaHD());
              dialogAddHD.tvTongTien.setText("Tổng tiền:  "+hoaDon.getTongTien()+"VND");
              cthDadapter=new ChiTietHoaDonAdapter(context,hoaDon.getMaHD());
              dialogAddHD.view.setAdapter(cthDadapter);
              dialogAddHD.btnok.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      dialogAddHD.cancel();
                  }
              });

              dialogAddHD.btncancel.setVisibility(View.GONE);
              dialogAddHD.create();

              dialogAddHD.show();
          }
      });
  }
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }




    public class Holder extends RecyclerView.ViewHolder {
        CardView itemHoaDon;
        TextView tvDate,tvID;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.date);
            tvID=itemView.findViewById(R.id.id);
            itemHoaDon=itemView.findViewById(R.id.item_hoadon);
        }
    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()){
                      list=listTemp;
                }else {
                    List<HoaDon>listtemp= new ArrayList<>();
                    for(HoaDon hoaDon : listTemp){
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        String mDate=simpleDateFormat.format(hoaDon.getNgayLap());
                        if(mDate.toLowerCase().contains(search.toLowerCase())){
                            listtemp.add(hoaDon);
                        }
                    }
                    list= (ArrayList<HoaDon>) listtemp;
                }
                FilterResults results = new FilterResults();
                results.values=list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<HoaDon>) results.values;
                notifyDataSetChanged();


            }
        };
    }
}
