package com.example.luxrystore.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String maSP;
    private String tenSP;
    private String maLoai;
    private int soLuong;
    private float giaBan;
    private String moTa;

    public SanPham(String maSP, String tenSP, String maLoai, int soLuong, float giaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }
    public SanPham( String tenSP, String maLoai, int soLuong, float giaBan,String moTa) {

        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa=moTa;
    }

    public SanPham() {
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }
}
