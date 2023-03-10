package com.example.luxrystore.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.HoaDon;
import com.example.luxrystore.model.NhanVien;
import com.example.luxrystore.model.SanPham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class HoaDonDAO {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;
    SimpleDateFormat sfm=new SimpleDateFormat("dd/MM/yyyy");
    public HoaDonDAO(Context context) {
        this.context = context;
        dBhelper=new DBhelper(context);
        db=dBhelper.getReadableDatabase();
    }
    public long insert(HoaDon hd){

        ContentValues values= new ContentValues();
        values.put("maNV",hd.getMaNV());
        values.put("tenKH",hd.getTenKH());
        values.put("ngayLap",sfm.format(hd.getNgayLap()));
        values.put("tongTien",hd.getTongTien());
        return db.insert("hoadon",null,values);
    }
    public int update(HoaDon hd){
        ContentValues values= new ContentValues();
        values.put("maHD",hd.getMaHD());
        values.put("maNV",hd.getMaNV());
        values.put("tenKH",hd.getTenKH());
        values.put("ngayLap",sfm.format(hd.getNgayLap()));
        values.put("tongTien",hd.getTongTien());
        return db.update("hoadon",values,"maHD=?",new String[]{hd.getMaHD()});
    }
    public int delete(HoaDon hd){
        return db.delete("hoadon","maHD=?",new String[]{hd.getMaHD()});
    }
    private ArrayList<HoaDon> getData(String sql, String...selectionArgs){
        ArrayList<HoaDon> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
       if(cursor.getCount()>0){
           do {
               HoaDon kh = new HoaDon();
               kh.setMaHD(cursor.getString(0));
               kh.setMaNV(cursor.getString(1));
               kh.setTenKH(cursor.getString(2));
               try {
                   kh.setNgayLap(sfm.parse(cursor.getString(3)));
               } catch (ParseException e) {
                   e.printStackTrace();
               }

               kh.setTongTien(cursor.getInt(4));
               list.add(kh);
           }while (cursor.moveToNext());
       }
        cursor.close();
        return list;
    }
    public ArrayList<HoaDon> getAllData() {
        String getAllData = "select * from hoadon";
        return getData(getAllData);
    }
    public HoaDon getDataByID(String id){
        String sql="select * from hoadon where maHD=?";
        return getData(sql,id).get(0);
    }
    public ArrayList<HoaDon> getDataByMaNV(String maNV){
        String sql="select * from hoadon where maNV=?";
        return getData(sql,maNV);
    }
    public ArrayList<HoaDon> getDataByDate(String date){
        String sql="select * from hoadon where ngayLap=?";
        return getData(sql,date);

    }
    public ArrayList<HoaDon> getDataByDateAndMaNV(String date,String maNV){
        String sql="select * from hoadon where ngayLap=? and maNV=?";
        return getData(sql,date,maNV);
    }
    public HoaDon getNewData(){
        String sql="select * from hoadon order by maHD desc ";
        return getData(sql).get(0);
    }
    @SuppressLint("Range")
    public  float getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu="select sum(tongTien) as doanhThu from hoadon where ngayLap>=? and ngaylap<=?";
        List<Float> list=new ArrayList<Float>();
        Cursor c=db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try{
                list.add((Float.parseFloat(c.getString(c.getColumnIndex("doanhThu")))));
            }catch (Exception e){
                list.add(0f);
            }
        }
        return list.get(0);
    }

}
