package com.example.duan_bandienthoai.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.Util.Utils;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.mode.DienThoai;
import com.example.duan_bandienthoai.mode.GioHang;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnThem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;

    DienThoai dienThoai;
    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();
initControl();
    }

    private void initControl() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themGioHang();
            }
        });
    }
    private void themGioHang(){
        if(Utils.manggiohang.size()>0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i =0 ; i< Utils.manggiohang.size(); i++){
                if(Utils.manggiohang.get(i).getIdsp()== dienThoai.getId()){
                    Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());
                    int gia = dienThoai.getPrice() * Utils.manggiohang.get(i).getSoluong();
                    Utils.manggiohang.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if(flag == false){
                int gia = dienThoai.getPrice() * soluong;
                GioHang gioHang = new GioHang();
                gioHang.setGiasp(gia);
                gioHang.setSoluong(soluong);
                gioHang.setIdsp(dienThoai.getId());
                gioHang.setTensp(dienThoai.getName());
                gioHang.setHinhsp(dienThoai.getAnh());
                Utils.manggiohang.add(gioHang);
            }
        }else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            int gia = dienThoai.getPrice();
            GioHang gioHang = new GioHang();
            gioHang.setGiasp(gia);
            gioHang.setSoluong(soluong);
            gioHang.setIdsp(dienThoai.getId());
            gioHang.setTensp(dienThoai.getName());
            gioHang.setHinhsp(dienThoai.getAnh());
            Utils.manggiohang.add(gioHang);
        }
        int totalItem= 0;
        for(int i =  0 ; i<Utils.manggiohang.size() ;i++){
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
        dienThoai = dienThoai = (DienThoai) getIntent().getSerializableExtra("chitiet");
        tensp.setText(dienThoai.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá: "+decimalFormat.format(dienThoai.getPrice()) +" VND");
        mota.setText(dienThoai.getMota());
        int img_id =((Activity)this).getResources().getIdentifier(dienThoai.getAnh(),"drawable",((Activity)this).getPackageName());
        imghinhanh.setImageResource(img_id);
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterSpin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterSpin);

    }


    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnThem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toobbar);
        badge = findViewById(R.id.menu_sl);
        FrameLayout frameLayout = findViewById(R.id.framegiohang);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(giohang);
            }
        });

        if(Utils.manggiohang != null){
            int totalItem= 0;
            for(int i =  0 ; i<Utils.manggiohang.size() ;i++){
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }

            badge.setText(String.valueOf(totalItem));
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.manggiohang != null){
            int totalItem= 0;
            for(int i =  0 ; i<Utils.manggiohang.size() ;i++){
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }

            badge.setText(String.valueOf(totalItem));
        }
    }
}