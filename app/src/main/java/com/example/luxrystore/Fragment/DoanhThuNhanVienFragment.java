package com.example.luxrystore.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luxrystore.Adapter.HoaDonAdapter;
import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DoanhThuActivity;
import com.example.luxrystore.R;
import com.example.luxrystore.model.HoaDon;
import com.example.luxrystore.model.NhanVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class DoanhThuNhanVienFragment extends Fragment {
    DoanhThuActivity doanhThuActivity;
EditText edDate;
TextView tenNhanVien,tvdoanhThu;
RecyclerView recyclerView;
HoaDonAdapter adapter;
private int day,month,year;
HoaDonDAO dao;
ArrayList<HoaDon> list=new ArrayList<>();
    public DoanhThuNhanVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doanh_thu_nhan_vien, container, false);
        doanhThuActivity=(DoanhThuActivity) getActivity();
        edDate=view.findViewById(R.id.ed_DT_tuNgay);
        tenNhanVien=view.findViewById(R.id.tennv);
        recyclerView=view.findViewById(R.id.lv);
        tvdoanhThu=view.findViewById(R.id.tv_doanhThu);
        NhanVien nhanVien=doanhThuActivity.getNhanVien();
        dao=new HoaDonDAO(getContext());
        adapter =new HoaDonAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=dao.getDataByMaNV(nhanVien.getId());
        adapter.setList(list);
        recyclerView.setAdapter(adapter);

        tenNhanVien.setText("Tên Nhân viên:"+nhanVien.getName());

        Calendar calendar=Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year,month,dayOfMonth);
                    edDate.setText(simpleDateFormat.format(calendar.getTime()));
                    list=dao.getDataByDateAndMaNV(edDate.getText().toString(),nhanVien.getId());

                        adapter.setList(list);

                    float doanhThu=0;
                    for (HoaDon hoaDon:list){
                        doanhThu+=hoaDon.getTongTien();
                    }
                    tvdoanhThu.setText(String.valueOf(doanhThu));

                }
            },year,month,day);

            datePickerDialog.show();

//            adapter.setList(dao.getDataByDate(day+"/"+month+"/"+year));
        });


        return  view ;
    }
}