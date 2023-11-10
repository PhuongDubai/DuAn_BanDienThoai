package com.example.duan_bandienthoai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "BanDienThoai", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(manguoidung text primary key , hoten text , matkhau text,loaitaikhoan text)";
        db.execSQL(dbNguoiDung);
        db.execSQL("INSERT INTO NGUOIDUNG VALUES ('thuthu01' , 'Nguyễn Văn A','123','nhanvien'),('admin' , 'Nguyễn Công Phương','1','admin') ");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG ");
            onCreate(db);


        }
    }
}
