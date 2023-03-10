package com.example.luxrystore.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.R;
import com.example.luxrystore.model.NhanVien;

public class DialogAddNV extends Dialog {
    NhanVienDAO dao;
    Button btnok,btncanle;
    EditText edName,edSDT,edUser,edPass;
    String name,sdt,user,pass;
    public DialogAddNV(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_add_nv);
        btnok=findViewById(R.id.ok);
        btncanle=findViewById(R.id.canle);
        edName=findViewById(R.id.dgtvn_edname);
        edSDT=findViewById(R.id.dgtvn_edsdt);
        edUser=findViewById(R.id.dgtvn_edsuser);
        edPass=findViewById(R.id.dgtvn_edpass);
        dao=new NhanVienDAO(getContext());

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=edName.getText().toString();
                sdt=edSDT.getText().toString();
                user=edUser.getText().toString();
                pass=edPass.getText().toString();
                NhanVien nhanVien=new NhanVien(name,sdt,user,pass);
                dao.insert(nhanVien);
                cancel();
            }
        });
        btncanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
