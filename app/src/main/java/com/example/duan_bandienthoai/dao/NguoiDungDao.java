package com.example.duan_bandienthoai.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_bandienthoai.database.DbHelper;
import com.example.duan_bandienthoai.mode.NguoiDung;

import java.util.ArrayList;
import java.util.List;



public class NguoiDungDao {

    private SQLiteDatabase db;
    private final DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public NguoiDungDao(Context context) {
        dbHelper = new DbHelper(context);

        db = dbHelper.getWritableDatabase();
        sharedPreferences = context.getSharedPreferences("thongtin", Context.MODE_PRIVATE);
    }
    public boolean  insert(NguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("manguoidung", obj.getMaNguoiDung());
        values.put("hoten", obj.getHoTen());
        values.put("matkhau", obj.getMatKhau());
        long row = db.insert("NGUOIDUNG",null,values);
        return (row>0);
    }
    public int updatePass(NguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("manguoidung", obj.getMaNguoiDung());
        values.put("hoten", obj.getHoTen());
        values.put("matkhau", obj.getMatKhau());
        return db.update("NGUOIDUNG", values, "manguoidung = ?", new String[]{obj.getMaNguoiDung()});
    }
    public int delete(String id) {
        return db.delete("NGUOIDUNG", "manguoidung = ?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<NguoiDung> getData(String sql, String... seclectionArgs) {
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, seclectionArgs);
        while (cursor.moveToNext()) {
            NguoiDung obj = new NguoiDung();

            obj.setMaNguoiDung(cursor.getString(cursor.getColumnIndex("manguoidung")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoten")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            list.add(obj);

        }
        return list;
    }
    public List<NguoiDung> getAll() {
        String sql = "SELECT * FROM NGUOIDUNG";
        return getData(sql);
    }
    public NguoiDung getID(String id) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE manguoidung = ?";
        List<NguoiDung> list = getData(sql, id);
        return list.get(0);
    }
    public boolean checkLogin(String matt, String matkhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE manguoidung=? AND matkhau=?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            // luu
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // lưu giá trị
            // lấy giá trị của mã tt
            editor.putString("manguoidung", cursor.getString(0));
            // lưu loại tài khoản
            editor.putString("loaitaikhoan", cursor.getString(3));
            editor.commit();

            return true;
        } else {
            return false;
        }

//    @SuppressLint("SuspiciousIndentation")
//    public int checkLogin(String id, String password) {
//        String sql = "SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?";
//        List<ThuThu> list = getData(sql, id, password);
//        if (list.size() == 0)
//            return -1;
//            return 1;
//    }
    }
    public String ForgotPassword(String email) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT matkhau FROM NGUOIDUNG WHERE manguoidung = ? ", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);

        } else {
            return "";
        }
    }

}
