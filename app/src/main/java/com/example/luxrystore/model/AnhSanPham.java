package com.example.luxrystore.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class AnhSanPham {
    String id;
    String maSP;
    String bitmap;

    public AnhSanPham() {
    }

    public AnhSanPham(String maSP, String bitmap) {
        this.maSP = maSP;
        this.bitmap = bitmap;
    }

    public AnhSanPham(String id, String maSP, String bitmap) {
        this.id = id;
        this.maSP = maSP;
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }
}
