package com.example.luxrystore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luxrystore.Database.DBhelper;
import com.example.luxrystore.model.KhachHang;
import com.example.luxrystore.model.NhanVien;

import java.util.ArrayList;

public class NhanVienDAO {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        this.context = context;
        dBhelper = new DBhelper(context);
        db = dBhelper.getReadableDatabase();
    }

    public long insert(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("name", nv.getName());
        values.put("sdt", nv.getStd());
        values.put("user", nv.getUser());
        values.put("password", nv.getPassWord());
        return db.insert("nhanvien", null, values);
    }

    public int update(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("name", nv.getName());
        values.put("sdt", nv.getStd());
        values.put("user", nv.getUser());
        values.put("password", nv.getPassWord());
        return db.update("nhanvien", values, "id=?", new String[]{nv.getId()});
    }

    public int delete(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("name", nv.getName());
        values.put("sdt", nv.getStd());
        values.put("user", nv.getUser());
        values.put("password", nv.getPassWord());
        return db.delete("nhanvien", "id=?", new String[]{nv.getId()});
    }

    private ArrayList<NhanVien> getData(String sql, String... selectionArgs) {
        ArrayList<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        do {
            NhanVien kh = new NhanVien();
            kh.setId(cursor.getString(0));
            kh.setName(cursor.getString(1));
            kh.setStd(cursor.getString(2));
            kh.setUser(cursor.getString(3));
            kh.setPassWord(cursor.getString(4));
            list.add(kh);
        } while (cursor.moveToNext());
        cursor.close();
        return list;
    }

    public ArrayList<NhanVien> getAllData() {
        String getAllData = "select * from nhanvien";
        return getData(getAllData);
    }

    public NhanVien getDataByID(String id) {
        String sql = "select * from nhanvien where id=?";
        return getData(sql, id).get(0);
    }
    public NhanVien getDataByUser(String user) {
        String sql = "select * from nhanvien where user=?";
        return getData(sql, user).get(0);
    }

    public boolean CheckPassWord(String user, String pass) {
        ArrayList<NhanVien> list = getAllData();
        for (int i = 0; i < list.size(); i++) {
            NhanVien nhanVien = list.get(i);
            if (nhanVien.getUser().equals(user)) {
                if (nhanVien.getPassWord().equals(pass)) {
                    return true;
                }
            }
        }

        return false;
    }
}

