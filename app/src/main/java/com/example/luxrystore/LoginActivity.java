package com.example.luxrystore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
Intent intent;
Button button;
EditText edUser,edPass;
NhanVienDAO dao;
ArrayList<NhanVien> list;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.login_btnlogin);
        edUser=findViewById(R.id.login_eduser);
        edPass=findViewById(R.id.login_edpass);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editer=sharedPreferences.edit();
        dao=new NhanVienDAO(this);
        list=dao.getAllData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user=edUser.getText().toString();
                pass=edPass.getText().toString();

                intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("USER",user);
                editer.putString("USER",user);
                editer.putString("PASS",pass);
                editer.apply();
                editer.commit();

                if(dao.CheckPassWord(user,pass)){
                    startActivity(intent);
                    finish();
                    editer.commit();
                }else if(user.isEmpty()||pass.isEmpty()){
                    Toast.makeText(v.getContext(), "Khong Duoc de trong tk hoac mk", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Tai Khoan Hoac Mat Khau khong Chinh Xac", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}