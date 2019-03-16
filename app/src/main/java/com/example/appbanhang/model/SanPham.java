package com.example.appbanhang.model;

import java.math.BigInteger;

public class SanPham {
    int id_sp;
    String ten_sp,hinh_sp;
    int sp_khuyenmai;
    Integer giabandau_sp,giahientai_sp;
    String gioithieu_sp;
    int id_menucon;

    public SanPham(int id_sp, String ten_sp, String hinh_sp, int sp_khuyenmai, Integer giabandau_sp, Integer giahientai_sp, String gioithieu_sp, int id_menucon) {
        this.id_sp = id_sp;
        this.ten_sp = ten_sp;
        this.hinh_sp = hinh_sp;
        this.sp_khuyenmai = sp_khuyenmai;
        this.giabandau_sp = giabandau_sp;
        this.giahientai_sp = giahientai_sp;
        this.gioithieu_sp = gioithieu_sp;
        this.id_menucon = id_menucon;
    }

    public int getId_sp() {
        return id_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public String getHinh_sp() {
        return hinh_sp;
    }

    public int getSp_khuyenmai() {
        return sp_khuyenmai;
    }

    public Integer getGiabandau_sp() {
        return giabandau_sp;
    }

    public Integer getGiahientai_sp() {
        return giahientai_sp;
    }

    public String getGioithieu_sp() {
        return gioithieu_sp;
    }

    public int getId_menucon() {
        return id_menucon;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public void setHinh_sp(String hinh_sp) {
        this.hinh_sp = hinh_sp;
    }

    public void setSp_khuyenmai(int sp_khuyenmai) {
        this.sp_khuyenmai = sp_khuyenmai;
    }

    public void setGiabandau_sp(Integer giabandau_sp) {
        this.giabandau_sp = giabandau_sp;
    }

    public void setGiahientai_sp(Integer giahientai_sp) {
        this.giahientai_sp = giahientai_sp;
    }

    public void setGioithieu_sp(String gioithieu_sp) {
        this.gioithieu_sp = gioithieu_sp;
    }

    public void setId_menucon(int id_menucon) {
        this.id_menucon = id_menucon;
    }
}
