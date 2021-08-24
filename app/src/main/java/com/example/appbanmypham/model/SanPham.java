package com.example.appbanmypham.model;

public class SanPham {
    String idSP;
    String tenSP;
    int giaSP;
    String hinhAnh;
    String moTa;
    String idLoai;

    public SanPham(String idSP, String tenSP, int giaSP, String hinhAnh, String moTa, String idLoai) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.idLoai = idLoai;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(String idLoai) {
        this.idLoai = idLoai;
    }
}
