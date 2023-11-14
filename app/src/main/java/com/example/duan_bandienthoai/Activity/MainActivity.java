package com.example.duan_bandienthoai.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.duan_bandienthoai.Adapter.DienThoaiAdapter;
import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.dao.NguoiDungDao;

import com.example.duan_bandienthoai.mode.DienThoai;
import com.example.duan_bandienthoai.mode.NguoiDung;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    TextView tenNguoiDung;
    NguoiDungDao nguoiDungDao;
    NguoiDung nguoiDung;
    NavigationView navigationView;
    RecyclerView rcv;
    DienThoaiDao dao;
    DienThoaiAdapter adapter;
    private ArrayList<DienThoai> list = new ArrayList<DienThoai>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toobbar_manhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);
        rcv = findViewById(R.id.recycleview);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);

        dao = new DienThoaiDao(getApplicationContext());
        list= dao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        adapter = new DienThoaiAdapter(this,list);
        rcv.setAdapter(adapter);




        navigationView.setItemIconTintList(null);
        View headerView  =navigationView.getHeaderView(0);
        tenNguoiDung = headerView.findViewById(R.id.userNGuoidung);
        Intent intent= getIntent();
        String user = intent.getStringExtra("user");
        nguoiDungDao = new NguoiDungDao(this);
        NguoiDung ng = nguoiDungDao.getID(user);
        String username = ng.getHoTen();
        tenNguoiDung.setText("Welcome "+ username+"!");

        ActionBar();
        ActionViewFlipper();
        getEvenClick();
    }
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }
    private void getEvenClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.trangchu) {
                    startActivity(new Intent(MainActivity.this,DienThoaiActivity.class));
                } else if (item.getItemId() == R.id.danhsach) {
                    startActivity(new Intent(MainActivity.this,DienThoaiActivity.class));
                }
                getSupportActionBar().setTitle(item.getTitle());
                return true;
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String loaitk = sharedPreferences.getString("loaitaikhoan","");
        if (!loaitk.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.themsanpham).setVisible(false);
        }



    }


}