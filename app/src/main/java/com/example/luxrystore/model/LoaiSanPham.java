package com.example.luxrystore.model;

public class LoaiSanPham {
    private String maLoai;
    private String tenloai;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoai, String tenloai) {
        this.maLoai = maLoai;
        this.tenloai = tenloai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
