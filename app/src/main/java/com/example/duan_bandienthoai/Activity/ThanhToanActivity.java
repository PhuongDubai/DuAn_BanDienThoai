package com.example.duan_bandienthoai.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.Util.Utils;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.dao.NguoiDungDao;
import com.example.duan_bandienthoai.mode.DienThoai;
import com.example.duan_bandienthoai.mode.NguoiDung;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttongtien, txtsdt, txtemail;
    EditText edtDiachi;
    Button btnDatHang;
    NguoiDung nguoiDung;
    NguoiDungDao nguoiDungDao;
    private ArrayList<NguoiDung> list = new ArrayList<NguoiDung>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_thanh_toan);
        initView();
        ActionToolBar();
    }


    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int tongtien = getIntent().getIntExtra("tongtien", 0);
        txttongtien.setText(decimalFormat.format(tongtien));


        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi = edtDiachi.getText().toString().trim();
                if (TextUtils.isEmpty(str_diachi)) {
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    //post data
                    Log.d("test", new Gson().toJson(Utils.manggiohang));

                }
            }
        });

    }

    private void initView() {
        toolbar = findViewById(R.id.toobbarthanhtoan);
        txttongtien = findViewById(R.id.txtTongTien_thanhtoan);
        txtsdt = findViewById(R.id.txtsodienthoai);
        txtemail = findViewById(R.id.txtemail);
        edtDiachi = findViewById(R.id.edtdiachi);
        btnDatHang = findViewById(R.id.btnDatHang);






    }
}