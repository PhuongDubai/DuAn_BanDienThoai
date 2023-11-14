package com.example.duan_bandienthoai.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.duan_bandienthoai.Adapter.DienThoaiAdapter;
import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.mode.DienThoai;

import java.util.ArrayList;

public class DienThoaiActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView searchView;
    RecyclerView recyclerView;

    DienThoaiDao dao;
    DienThoaiAdapter adapter;
    private ArrayList<DienThoai> list = new ArrayList<DienThoai>();
    ArrayList<DienThoai> listtemp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        toolbar = findViewById(R.id.toobbar);
        recyclerView = findViewById(R.id.recycleview_dt);
        searchView = findViewById(R.id.btnSearch);

        dao = new DienThoaiDao(getApplicationContext());
        list = dao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DienThoaiAdapter(this, list);
        recyclerView.setAdapter(adapter);


        ActionToolBar();
        listtemp = (ArrayList<DienThoai>) dao.selectAll();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                for (DienThoai dt : listtemp) {
                    if (dt.getName().contains(newText.toString())) {
                        list.add(dt);
                    }
                }
                adapter.notifyDataSetChanged();

                return false;
            }
        });
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