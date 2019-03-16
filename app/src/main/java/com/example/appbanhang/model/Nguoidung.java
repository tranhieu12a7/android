package com.example.appbanhang.model;

public class Nguoidung {

    String ten,diachi,sdt;

    public Nguoidung( String ten, String diachi, String sdt) {

        this.ten = ten;
        this.diachi = diachi;
        this.sdt = sdt;
    }





    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSdt() {
        return sdt;
    }
}
