package com.example.luxrystore.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luxrystore.model.GioHang;

import com.example.luxrystore.R;
import com.example.luxrystore.model.SanPham;

public class DialogGioHang extends AlertDialog.Builder {
        public EditText sl;
    public DialogGioHang(Context context, SanPham sanPham) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.dialog_giohang,null);
        setView(view);
        sl=view.findViewById(R.id.tv_sol);
        setTitle("Thêm "+sanPham.getTenSP()+" Vào Giỏ");
        setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {




                if (!sl.getText().toString().isEmpty()){
                    int soLuong=Integer.valueOf(sl.getText().toString());
                    if(sanPham.getSoLuong()>soLuong){

                        GioHang gioHang=new GioHang(sanPham,soLuong);
                        GioHang.ListGioHang.list.add(gioHang);
                        Toast.makeText(getContext(),"them Thanh Cong",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(),"Khong du so luong",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getContext(),"them That Bai",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}



