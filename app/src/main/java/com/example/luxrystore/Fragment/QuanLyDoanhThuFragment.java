package com.example.luxrystore.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.luxrystore.DAO.HoaDonDAO;
import com.example.luxrystore.DoanhThuActivity;
import com.example.luxrystore.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class QuanLyDoanhThuFragment extends Fragment {

    private TextInputEditText ed_tuNgay,ed_denNgay;
    private TextView tv_doanhThu;
    private int day,month,year;
    private HoaDonDAO hoaDonDAO;
    public QuanLyDoanhThuFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View view=inflater.inflate(R.layout.fragment_quan_ly_doanh_thu, container, false);
        // Inflate the layout for this fragment
        ed_tuNgay = view.findViewById(R.id.ed_DT_tuNgay);
        ed_denNgay = view.findViewById(R.id.ed_DT_denNgay);
        tv_doanhThu = view.findViewById(R.id.tv_doanhThu);
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ed_tuNgay.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year,month,dayOfMonth);
                    ed_tuNgay.setText(simpleDateFormat.format(calendar.getTime()));
                }
            },year,month,day);
            datePickerDialog.show();
        });
        ed_denNgay.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year,month,dayOfMonth);
                    ed_denNgay.setText(simpleDateFormat.format(calendar.getTime()));
                }
            },year,month,day);
            datePickerDialog.show();

        });
        view.findViewById(R.id.btn_doanhThu).setOnClickListener(v -> {
            hoaDonDAO = new HoaDonDAO(view.getContext());
            tv_doanhThu.setText(String.valueOf(hoaDonDAO.getDoanhThu(ed_tuNgay.getText().toString(),ed_denNgay.getText().toString())));

    });
        return view;
}
}