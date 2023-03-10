package com.example.luxrystore.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxrystore.Adapter.CTHDadapter;
import com.example.luxrystore.Adapter.HoaDonAdapter;
import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.DAO.SanPhamDAO;
import com.example.luxrystore.DAO.cthdDAO;
import com.example.luxrystore.QuanLyHoaDon;
import com.example.luxrystore.model.GioHang;
import com.example.luxrystore.R;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.HoaDon;
import com.example.luxrystore.model.NhanVien;
import com.example.luxrystore.model.SanPham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialogAddHD extends Dialog {
    public  RecyclerView view;
    public Button btnok,btncancel;
    public TextView tvMaHD,tvMaNV,tvDate,tvTongTien;
    public EditText tvName;
    HoaDonDAO dao;
    NhanVienDAO  nhanVienDAO;
    cthdDAO cthdDAO;
    SanPhamDAO sanPhamDAO;
    static int maHD=1;

    public DialogAddHD(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_add_hd);
        tvMaHD=findViewById(R.id.mahd);
        tvMaNV=findViewById(R.id.manv);
        tvName=findViewById(R.id.makh);
        tvDate=findViewById(R.id.date);
        tvTongTien=findViewById(R.id.tongtien);
        btnok=findViewById(R.id.ok);
        btncancel=findViewById(R.id.cancle);
        dao=new HoaDonDAO(getContext());
        nhanVienDAO=new NhanVienDAO(getContext());

        cthdDAO=new cthdDAO(getContext());
        LinearLayoutManager manager=new LinearLayoutManager(context);
        CTHDadapter cthDadapter=new CTHDadapter(getContext());
        view=findViewById(R.id.lv);
        view.setLayoutManager(manager);
        view.setAdapter(cthDadapter);


    }

    public DialogAddHD(@NonNull Context context, String name) {
        super(context);

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String sDate= simpleDateFormat.format(date);
        setContentView(R.layout.dialog_add_hd);
        tvMaHD=findViewById(R.id.mahd);
        tvMaNV=findViewById(R.id.manv);
        tvName=findViewById(R.id.makh);
        tvDate=findViewById(R.id.date);
        tvTongTien=findViewById(R.id.tongtien);
        btnok=findViewById(R.id.ok);
        btncancel=findViewById(R.id.cancle);
        dao=new HoaDonDAO(getContext());
        nhanVienDAO=new NhanVienDAO(getContext());
        cthdDAO=new cthdDAO(getContext());
        LinearLayoutManager manager=new LinearLayoutManager(context);
        CTHDadapter cthDadapter=new CTHDadapter(getContext());
        view=findViewById(R.id.lv);
        view.setLayoutManager(manager);
        view.setAdapter(cthDadapter);
        sanPhamDAO=new SanPhamDAO(getContext());
        NhanVien nhanVien =nhanVienDAO.getDataByUser(name);
        tvMaHD.setText(String.valueOf(maHD));
        tvTongTien.setText(String.valueOf(TongTien()));
        tvMaNV.setText(nhanVien.getName());
        tvDate.setText(sDate);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV,tenKH;
                Date date=new Date();
                Float tongTien;
                maNV=nhanVien.getId();
                tenKH=tvName.getText().toString();
                tongTien=TongTien();
                if(!tenKH.isEmpty()&&tongTien!=0){
                    try {
                        date= simpleDateFormat.parse(sDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    HoaDon hoaDon =new HoaDon(maNV,tenKH,tongTien,date);
                    if(dao.insert(hoaDon)>0){
                        hoaDon=dao.getNewData();
                        ArrayList<GioHang>  list=GioHang.ListGioHang.list;
                        for (int i=0;i<list.size();i++){
                            GioHang gioHang=list.get(i);
                            CTHD cthd=new CTHD(hoaDon.getMaHD(),gioHang.getSanPham().getMaSP(),gioHang.getSoLuong(),gioHang.getThanhTien());

                            if (   cthdDAO.insert(cthd)>0){
                                SanPham sanPham=sanPhamDAO.getDataByID(gioHang.getSanPham().getMaSP());
                                sanPham.setSoLuong(sanPham.getSoLuong()-gioHang.getSoLuong());
                                sanPhamDAO.update(sanPham);
                                GioHang.ListGioHang.list.clear();
                            }


                        }

                        QuanLyHoaDon quanLyHoaDon=(QuanLyHoaDon) context;
                        HoaDonDAO dao=new HoaDonDAO(context);
                        quanLyHoaDon.adapter.setList(dao.getAllData());
                        cancel();
                    }else {

                    }
                }else {
                        Toast.makeText(v.getContext(), "KHong The Tao Hoa Don", Toast.LENGTH_SHORT).show();
                    }


            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }
    private float TongTien(){
        float tongTien=0;
        ArrayList<GioHang> list=GioHang.ListGioHang.list;
        for (int i=0;i<list.size();i++){
            GioHang gioHang=list.get(i);
            tongTien+=gioHang.getThanhTien();
        }
        return tongTien;
    }
}
