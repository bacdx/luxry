package com.example.luxrystore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    Context context;
DBhelper dBhelper;
SQLiteDatabase db;
    public KhachHangDAO(Context context) {
        this.context = context;
        dBhelper=new DBhelper(context);
        db=dBhelper.getReadableDatabase();
    }
    public long insert(KhachHang kh){
        ContentValues values= new ContentValues();
        values.put("name",kh.getName());
        values.put("sdt",kh.getStd());
        return db.insert("khachhang",null,values);
    }
    public int update(KhachHang kh){
        ContentValues values= new ContentValues();
        values.put("name",kh.getName());
        values.put("sdt",kh.getStd());
        return db.update("khachhang",values,"id=?",new String[]{kh.getId()});
    }
    public int delete(KhachHang kh){
        ContentValues values= new ContentValues();
        values.put("name",kh.getName());
        values.put("sdt",kh.getStd());
        return db.delete("khachhang","id=?",new String[]{kh.getId()});
    }
    private ArrayList<KhachHang> getData(String sql, String...selectionArgs){
        ArrayList<KhachHang> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        do {
            KhachHang kh = new KhachHang();
            kh.setId(cursor.getString(0));
            kh.setName(cursor.getString(1));
            kh.setStd(cursor.getString(2));
            list.add(kh);
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }
    public ArrayList<KhachHang> getAllData() {
        String getAllData = "select * from khachhang";
        return getData(getAllData);
    }
        public KhachHang getDataByID(String id){
            String sql="select * from khachhang where id=?";
            return getData(sql,id).get(0);
    }


}
