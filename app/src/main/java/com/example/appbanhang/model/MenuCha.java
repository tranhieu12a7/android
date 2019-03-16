package com.example.appbanhang.model;

public class MenuCha {
    public int id;
    public String hinh,ten;

    public MenuCha(int id, String hinh, String ten) {
        this.id = id;
        this.hinh = hinh;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public String getHinh() {
        return hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
