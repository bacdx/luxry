package com.example.luxrystore.model;

public class KhachHang {
    private String id;
    private String name;
    private String std;

    public KhachHang(){

    }
    public KhachHang(String id, String name, String std) {
        this.id = id;
        this.name = name;
        this.std = std;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }
}
