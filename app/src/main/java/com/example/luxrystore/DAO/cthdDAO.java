package com.example.luxrystore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.CTHD;
import com.example.luxrystore.model.KhachHang;
import com.example.luxrystore.model.SanPham;
import com.example.luxrystore.model.Top10;

import java.util.ArrayList;

public class cthdDAO {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;
    public cthdDAO(Context context) {
        this.context = context;
        dBhelper=new DBhelper(context);
        db=dBhelper.getReadableDatabase();
    }
    public long insert(CTHD ct){
        ContentValues values= new ContentValues();
        values.put("maCTHD",ct.getMaCTHD());
        values.put("maHD",ct.getMaHD());
        values.put("maSP",ct.getMaSP());
        values.put("soLuong",ct.getSoLuong());
        values.put("thanhTien",ct.getThanhTien());
        return db.insert("chitiethoadon",null,values);
    }
    public int update(CTHD ct){
        ContentValues values= new ContentValues();
        values.put("maCTHD",ct.getMaCTHD());
        values.put("maHD",ct.getMaHD());
        values.put("maSP",ct.getMaSP());
        values.put("soLuong",ct.getSoLuong());
        values.put("thanhTien",ct.getThanhTien());
        return db.update("chitiethoadon",values,"maCTHD=?",new String[]{ct.getMaCTHD()});
    }
    public int delete(CTHD ct){
        return db.delete("chitiethoadon","maCTHD=?",new String[]{ct.getMaCTHD()});
    }
    private ArrayList<CTHD> getData(String sql, String...selectionArgs){
        ArrayList<CTHD> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        do {
            CTHD kh = new CTHD();
            kh.setMaCTHD(cursor.getString(0));
            kh.setMaHD(cursor.getString(1));
            kh.setMaSP(cursor.getString(2));
            kh.setSoLuong(cursor.getInt(3));
            kh.setThanhTien(cursor.getFloat(4));
            list.add(kh);
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }
    public ArrayList<CTHD> getAllData() {
        String getAllData = "select * from chitiethoadon";
        return getData(getAllData);
    }
    public CTHD getDataByID(String id){
        String sql="select * from chitiethoadon where maCTHD=?";
        return getData(sql,id).get(0);
    }
    public  ArrayList<CTHD> getDataByMaHD(String id){
        String sql="select * from chitiethoadon where maHD=?";
        return getData(sql,id);
    }
    public ArrayList<Top10> top10(){
        Cursor cursor = db.rawQuery("SELECT  sanpham.tenSP,sum(chitiethoadon.soLuong),sum(thanhtien)as thanhtien FROM sanpham JOIN chitiethoadon on sanpham.maSP=chitiethoadon.maSP GROUP BY chitiethoadon.maSP ORDER BY COUNT(sanpham.maSP)DESC LIMIT 10 ",null);
        ArrayList<Top10> list = new ArrayList<>();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new Top10(cursor.getString(0),cursor.getInt(1),cursor.getFloat(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
