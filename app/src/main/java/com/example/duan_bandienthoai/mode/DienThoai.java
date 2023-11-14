package com.example.duan_bandienthoai.mode;

public class DienThoai {
    private int id;
    private String name ;
    private int price;
    private String anh;

    public DienThoai() {
    }

    public DienThoai(int id, String name, int price, String anh) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.anh = anh;
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
}
