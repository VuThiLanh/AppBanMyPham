package com.example.appbanmypham.model;

public class GioHang {
    String tenSP;
    long giaSP;
    String hinhAnh;
    long soLuongMua;

    public GioHang(String tenSP, long giaSP, String hinhAnh, long soLuongMua) {
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

    public long getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(long giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public long getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(long soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return tenSP;
    }
}
