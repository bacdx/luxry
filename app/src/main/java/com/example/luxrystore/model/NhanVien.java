package com.example.luxrystore.model;

public class NhanVien {
    private String id;
    private String name;
    private String std;
    private String user;
    private String passWord;

    public NhanVien(String id, String name, String std, String user, String passWord) {
        this.id = id;
        this.name = name;
        this.std = std;
        this.user = user;
        this.passWord = passWord;
    }

    public NhanVien() {
    }

    public NhanVien(String name, String std, String user, String passWord) {
        this.name = name;
        this.std = std;
        this.user = user;
        this.passWord = passWord;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
