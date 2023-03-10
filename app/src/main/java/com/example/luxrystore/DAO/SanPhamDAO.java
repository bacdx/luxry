package com.example.luxrystore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.NhanVien;
import com.example.luxrystore.model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO  {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;
    public SanPhamDAO(Context context) {
        this.context = context;
        dBhelper=new DBhelper(context);
        db=dBhelper.getReadableDatabase();
    }
    public long insert(SanPham sp){
        ContentValues values= new ContentValues();
        values.put("tenSP",sp.getTenSP());
        values.put("maLoai",sp.getMaLoai());
        values.put("soLuong",sp.getSoLuong());
        values.put("giaBan",sp.getGiaBan());
        values.put("moTa",sp.getMoTa());
        return db.insert("sanpham",null,values);
    }
    public int update(SanPham sp){
        ContentValues values= new ContentValues();
        values.put("maSP",sp.getMaSP());
        values.put("tenSP",sp.getTenSP());
        values.put("maLoai",sp.getMaLoai());
        values.put("soLuong",sp.getSoLuong());
        values.put("giaBan",sp.getGiaBan());
        values.put("moTa",sp.getMoTa());
        return db.update("sanpham",values,"maSP=?",new String[]{sp.getMaSP()});
    }

    public int delete(SanPham sp){
        return db.delete("sanpham","maSP=?",new String[]{sp.getMaSP()});
    }
    private ArrayList<SanPham> getData(String sql, String...selectionArgs){
        ArrayList<SanPham> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        do {
            SanPham kh = new SanPham();
            kh.setMaSP(cursor.getString(0));
            kh.setTenSP(cursor.getString(1));
            kh.setMaLoai(cursor.getString(2));
            kh.setSoLuong(cursor.getInt(3));
            kh.setGiaBan(cursor.getFloat(4));
            kh.setMoTa(cursor.getString(5));
            list.add(kh);
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }
    public ArrayList<SanPham> getAllData() {
        String getAllData = "select * from sanpham";
        return getData(getAllData);
    }
    public SanPham getDataByID(String id){
        String sql="select * from sanpham where maSP=?";
        return getData(sql,id).get(0);
    }
    public ArrayList<SanPham> getAoNam(){
        String sql="select* from sanpham where maLoai=1";
        return getData(sql);
    }
    public ArrayList<SanPham> getAoNu(){
        String sql="select* from sanpham where maLoai=2";
        return getData(sql);
    }
    public ArrayList<SanPham> getQuanNam(){
        String sql="select* from sanpham where maLoai=3";
        return getData(sql);
    }
    public ArrayList<SanPham> getQuanNu(){
        String sql="select* from sanpham where maLoai=4";
        return getData(sql);
    }
    public ArrayList<SanPham> getPhuKienNam(){
        String sql="select* from sanpham where maLoai=5";
        return getData(sql);
    }public ArrayList<SanPham> getPhuKienNu(){
        String sql="select* from sanpham where maLoai=6";
        return getData(sql);
    }

    public SanPham getNewSanPham(){
    String sql="select * from sanpham order by maSP desc ";
    return getData(sql).get(0);
}



}
