package com.example.luxrystore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.AnhSanPham;
import com.example.luxrystore.model.CTHD;

import java.util.ArrayList;

public class AnhSanPhamDAO   {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase database;
    public AnhSanPhamDAO(Context context) {
        this.context = context;
        dBhelper=new DBhelper(context);
        database=dBhelper.getReadableDatabase();
    }
    public long insert(AnhSanPham ct){
        ContentValues values= new ContentValues();
        values.put("maSP",ct.getMaSP());
        values.put("linkimg",String.valueOf(ct.getBitmap()));


        return database.insert("anhsanpham",null,values);
    }
    public int update(AnhSanPham ct){
        ContentValues values= new ContentValues();
        values.put("maCTHD",ct.getId());
        values.put("maSP",ct.getMaSP());
        values.put("linkimg",String.valueOf(ct.getBitmap()));

        return database.update("anhsanpham",values,"id=?",new String[]{ct.getId()});
    }
    public int delete(AnhSanPham ct){
        return database.delete("anhsanpham","id=?",new String[]{ct.getId()});
    }
    private ArrayList<AnhSanPham> getData(String sql, String...selectionArgs){
        ArrayList<AnhSanPham> list=new ArrayList<>();
        Cursor cursor=database.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
       if( cursor.getCount()>0&&cursor!=null) {
           do {
               AnhSanPham kh = new AnhSanPham();
               kh.setId(cursor.getString(0));
               kh.setMaSP(cursor.getString(1));
               kh.setBitmap(cursor.getString(2));

               list.add(kh);
           } while (cursor.moveToNext());
       }
        cursor.close();
        return list;
    }
    public ArrayList<AnhSanPham> getAllData() {
        String getAllData = "select * from anhsanpham";
        return getData(getAllData);
    }
    public ArrayList<AnhSanPham> getDataByMaSP(String maSP) {
        String getDataByMaSP = "select * from anhsanpham where maSP=?";
        return getData(getDataByMaSP,maSP);
    }
}
