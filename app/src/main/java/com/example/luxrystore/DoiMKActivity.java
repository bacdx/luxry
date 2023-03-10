package com.example.luxrystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luxrystore.DAO.NhanVienDAO;
import com.example.luxrystore.model.NhanVien;

public class DoiMKActivity extends AppCompatActivity {
EditText edUser,edOldPass,edNewPass,edReNewPass;
Button btnok,btnCanle;
TextView tvLog;
Intent intent;
NhanVienDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mkactivity);
        edUser=findViewById(R.id.login_eduser);
        edOldPass=findViewById(R.id.login_edpass);
        edNewPass=findViewById(R.id.login_ednewpass);
        edReNewPass=findViewById(R.id.login_edrenewpass);
        btnok=findViewById(R.id.login_btnlogin);
        btnCanle=findViewById(R.id.login_btncanle);
        dao=new NhanVienDAO(this);
        tvLog=findViewById(R.id.log);
        intent=getIntent();
        String user=intent.getStringExtra("USER");
        edUser.setText(user);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user,pass,newPass,reNewPass;
                user=edUser.getText().toString();
                pass=edOldPass.getText().toString();newPass=edNewPass.getText().toString();
                reNewPass=edReNewPass.getText().toString();
                if( dao.CheckPassWord(user,pass)==false){
                    tvLog.setText("Vui Long KIem Tra Lai User va Pass");
                }
                else if (newPass.isEmpty()||reNewPass.isEmpty()){
                       tvLog.setText("KHong Duoc De Trong");

                }
                else if (!newPass.equals(reNewPass)){
                    tvLog.setText("Xac Nhan Lai Mat Khau Moi");
                }
                else {
                    NhanVien nhanVien=dao.getDataByUser(user);
                    nhanVien.setPassWord(newPass);
                    dao.update(nhanVien);
                    tvLog.setText("Doi Mat Khau Thanh Cong");
                    tvLog.setTextColor(Color.BLUE);

                }


            }
        });
    }
}