package com.example.duan_bandienthoai.mode;

public class NguoiDung {
    private  String maNguoiDung;
    private String hoTen ;
    private String matKhau;

    public NguoiDung() {
    }


    public NguoiDung(String maNguoiDung, String hoTen, String matKhau) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

