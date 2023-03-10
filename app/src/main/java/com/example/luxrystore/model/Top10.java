package com.example.luxrystore.model;

public class Top10 {
    String name;
    int soLuong;
    float tongTien;

    public Top10() {
    }

    public Top10(String name, int soLuong, float tongTien) {
        this.name = name;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
