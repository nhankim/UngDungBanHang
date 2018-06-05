package com.example.administrator.ungdungbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.adapter.AdapterSanPham;
import com.example.administrator.ungdungbanhang.model.SanPham;

import java.util.ArrayList;

public class LaptopActivity extends AppCompatActivity {

    GridView gvLaptop;
    Toolbar toolbarLaptop;

    AdapterSanPham adapterSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        addControls();
        ActionToolBar();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                Intent intent = new Intent(LaptopActivity.this,GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        gvLaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LaptopActivity.this,ChiTietSanPhamActivity.class);
                intent.putExtra("SanPham",MainActivity.dsSanPhamLaptop.get(position));
                startActivity(intent);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarLaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbarLaptop = findViewById(R.id.toolbarLaptop);
        gvLaptop = findViewById(R.id.gvLaptop);

        adapterSanPham = new AdapterSanPham(LaptopActivity.this,R.layout.layout_sanpham,MainActivity.dsSanPhamLaptop);
        gvLaptop.setAdapter(adapterSanPham);
    }
}
