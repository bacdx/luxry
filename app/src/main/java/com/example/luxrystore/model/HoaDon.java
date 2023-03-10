package com.example.luxrystore.model;

import java.util.Date;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String tenKH;
    private  float tongTien;
    private Date ngayLap;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }


    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public HoaDon() {

    }

    public HoaDon( String maNV, String tenKH, float tongTien, Date ngayLap) {
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
    }
}
