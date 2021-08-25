package com.example.appbanmypham.model;

import java.util.ArrayList;
import java.util.List;

public class ThanhToan {
    String ten,sdt,diachi;
    List<SanPham> sanPhamList = new ArrayList<>();

    public ThanhToan(String ten, String sdt, String diachi, List<SanPham> sanPhamList) {
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.sanPhamList = sanPhamList;
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

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }
}
