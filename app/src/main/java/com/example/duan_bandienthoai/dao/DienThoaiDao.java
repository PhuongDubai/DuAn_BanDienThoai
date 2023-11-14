package com.example.duan_bandienthoai.dao;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_bandienthoai.database.DbHelper;
import com.example.duan_bandienthoai.mode.DienThoai;

import java.util.ArrayList;

public class DienThoaiDao {
    DbHelper helper;

    public DienThoaiDao(Context context) {
        helper = new DbHelper(context);
    }

    public ArrayList<DienThoai> selectAll() {
        ArrayList<DienThoai> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DIENTHOAI", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    DienThoai dt = new DienThoai();
                    dt.setId(cursor.getInt(0));
                    dt.setName(cursor.getString(1));
                    dt.setPrice(cursor.getInt(2));
                    dt.setAnh(cursor.getString(3));
                    list.add(dt);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
}
