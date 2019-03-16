package com.example.appbanhang.model;

public class MenuCon {
    int id;
    String ten,hinh;
    int id_menucha;

    public MenuCon(int id, String ten, String hinh, int id_menucha) {
        this.id = id;
        this.ten = ten;
        this.hinh = hinh;
        this.id_menucha = id_menucha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public void setId_menucha(int id_menucha) {
        this.id_menucha = id_menucha;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getHinh() {
        return hinh;
    }

    public int getId_menucha() {
        return id_menucha;
    }
}
