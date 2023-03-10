package com.example.luxrystore.model;

import java.util.ArrayList;

public  class GioHang {

    private SanPham sanPham;
    private int soLuong;
    private Float thanhTien;

    public GioHang() {
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Float getThanhTien() {
        return thanhTien;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public GioHang(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        thanhTien=sanPham.getGiaBan()*soLuong;
    }

    public static class ListGioHang{

            public static ArrayList<GioHang> list=new ArrayList<>();
   }
}
