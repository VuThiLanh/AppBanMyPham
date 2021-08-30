package com.example.appbanmypham.model;

import java.util.ArrayList;
import java.util.List;

public class ThanhToan {
    String ten,sdt,diachi;
    List<GioHang> gioHangs = new ArrayList<>();
    long tongtiem;

    public ThanhToan(String ten, String sdt, String diachi, List<GioHang> gioHangs, long tongtiem) {
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.gioHangs = gioHangs;
        this.tongtiem = tongtiem;
    }

    @Override
    public String toString() {
        return gioHangs.toString();
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public List<GioHang> getGioHangs() {
        return gioHangs;
    }

    public void setGioHangs(List<GioHang> gioHangs) {
        this.gioHangs = gioHangs;
    }

    public long getTongtiem() {
        return tongtiem;
    }

    public void setTongtiem(long tongtiem) {
        this.tongtiem = tongtiem;
    }
}
