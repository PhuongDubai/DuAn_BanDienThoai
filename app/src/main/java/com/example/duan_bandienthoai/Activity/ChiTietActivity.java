package com.example.duan_bandienthoai.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.mode.DienThoai;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnThem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();

    }

    private void initData() {
        DienThoai dienThoai = (DienThoai) getIntent().getSerializableExtra("chitiet");
        tensp.setText(dienThoai.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá: "+decimalFormat.format(dienThoai.getPrice()) +" VND");
        mota.setText(dienThoai.getMota());
        int img_id =((Activity)this).getResources().getIdentifier(dienThoai.getAnh(),"drawable",((Activity)this).getPackageName());
        imghinhanh.setImageResource(img_id);

    }


    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnThem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toobbar);
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
    }


}