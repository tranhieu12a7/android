package com.example.appbanhang.model;

public class GioHang {
   int idsanpham;
   String tensanpham;
   String hinh;
   int soluong;
   Integer dongia;
   long tongtien;

    public GioHang(int idsanpham, String tensanpham, String hinh, int soluong, Integer dongia, long tongtien) {
        this.idsanpham = idsanpham;
        this.tensanpham = tensanpham;
        this.hinh = hinh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtien = tongtien;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDongia(Integer dongia) {
        this.dongia = dongia;
    }

    public void setTongtien(int soluong,Integer dongia) {

        this.tongtien = soluong*dongia;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public String getHinh() {
        return hinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public Integer getDongia() {
        return dongia;
    }

    public long getTongtien() {
        return tongtien;
    }
}
