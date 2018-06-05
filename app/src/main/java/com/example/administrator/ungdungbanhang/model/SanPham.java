package com.example.administrator.ungdungbanhang.model;

import java.io.Serializable;

public class SanPham implements Serializable{
    private int id;
    private String tensanpham;
    private String hinhanhsanpham;
    private int giasanpham;
    private String motasanpham;
    private int idloaisanpham;

    public SanPham() {
    }

    public SanPham(int id, String tensanpham, String hinhanhsanpham, int giasanpham, String motasanpham, int idloaisanpham) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.giasanpham = giasanpham;
        this.motasanpham = motasanpham;
        this.idloaisanpham = idloaisanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public int getIdloaisanpham() {
        return idloaisanpham;
    }

    public void setIdloaisanpham(int idloaisanpham) {
        this.idloaisanpham = idloaisanpham;
    }
}
