package com.example.duan_bandienthoai.mode;

import java.io.Serializable;

public class DienThoai implements Serializable {
    private int id;
    private String name ;
    private int price;
    private String anh;
    private String mota;

    public DienThoai() {
    }

    public DienThoai(int id, String name, int price, String anh, String mota) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.anh = anh;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
