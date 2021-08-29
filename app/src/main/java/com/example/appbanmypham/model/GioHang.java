package com.example.appbanmypham.model;

public class GioHang {
    String tenSP;
    int giaSP;
    String hinhAnh;
    int soLuongMua;

    public GioHang(String tenSP, int giaSP, String hinhAnh, int soLuongMua) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhAnh = hinhAnh;
        this.soLuongMua = soLuongMua;
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

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return tenSP;
    }
}
