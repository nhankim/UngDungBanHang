package com.example.administrator.ungdungbanhang.model;

public class LoaiSP {
    private String tenloaisanpham;
    private int hinhanhloaisanpham;

    public LoaiSP() {
    }

    public LoaiSP(String tenloaisanpham, int hinhanhloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public int getHinhanhloaisanpham() {
        return hinhanhloaisanpham;
    }

    public void setHinhanhloaisanpham(int hinhanhloaisanpham) {
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }
}
