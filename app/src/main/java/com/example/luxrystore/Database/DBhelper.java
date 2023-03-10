package com.example.luxrystore.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME="luxry";
    private static final String TABLE_NHANVIEN="create table nhanvien(" +
            "id integer not null  primary key autoincrement," +
            "name text not null," +
            "sdt text not null," +
            "user text not null," +
            "password text not null);";
    private static  final   String TABLE_LOAISANPHAM="create table loaisanpham(" +
            "maLoai integer not null   primary key autoincrement," +
            "tenLoai text not null); ";

    private static final String TABLE_SANPHAM="create table sanpham(" +
            "maSP integer not null primary key autoincrement," +
            "tenSP text not null," +
            "maLoai integer not null references loaisanpham(maLoai)," +
            "soLuong integer not null," +
            "giaBan real not null," +
            "moTa text);";
    private static final String TABLE_ANHSANPHAM="create table anhsanpham(" +
            "maASP integer not null primary key autoincrement," +
            "maSP text not null references sanpham(maSP)," +
            "linkimg text  );";
    private static final String TABLE_HOADON="create table hoadon(" +
            "maHD integer not null primary key autoincrement," +
            "maNV integer not null references nhanvien(id)," +
            "tenKH text not null ," +
            "ngayLap date not null," +
            "tongTien real not null);";
    private static final String TABLE_CTHD="create table chitiethoadon(" +
            "maCTHD integer not null primary key autoincrement," +
            "maHD integer not null references hoadon(maHD)," +
            "maSP integer not null references sanpham(maSP)," +
            "soLuong integer not null ," +
            "thanhTien real not null);";
    private static int VISION=1;

    public DBhelper(@Nullable Context context) {
        super(context,DB_NAME , null, VISION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
                db.execSQL(TABLE_LOAISANPHAM);
                db.execSQL(TABLE_SANPHAM);
                db.execSQL(TABLE_ANHSANPHAM);
                db.execSQL(TABLE_NHANVIEN);
                db.execSQL(TABLE_CTHD);
                db.execSQL(TABLE_HOADON);
                db.execSQL(data.insertNhanVien);
                db.execSQL(data.insertLoaiSP);
//                db.execSQL(data.insertKhachHang);
                db.execSQL(data.insertSanPham);
                db.execSQL(data.insertcthd);
                db.execSQL(data.insertHoaDon);
                db.execSQL(data.insertSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                                 db.execSQL("drop table if exists nhanvien");
                                 db.execSQL("drop table if exists loaisanpham");
                                 db.execSQL("drop table if exists sanpham");
                                 db.execSQL("drop table if exists cthd");
                                 db.execSQL("drop table if exists hoadon");
                                 db.execSQL("drop table if exists anhsanpham");
    }
}
