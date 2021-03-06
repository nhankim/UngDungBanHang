package com.example.administrator.ungdungbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class PhukienActivity extends AppCompatActivity {

    GridView gvPhuKien;
    Toolbar toolbarPhuKien;

    AdapterSanPham adapterSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phukien);
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
                Intent intent = new Intent(PhukienActivity.this,GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        gvPhuKien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhukienActivity.this,ChiTietSanPhamActivity.class);
                intent.putExtra("SanPham",MainActivity.dsSanPhamPhuKien.get(position));
                startActivity(intent);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarPhuKien);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhuKien.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbarPhuKien = findViewById(R.id.toolbarPhuKien);
        gvPhuKien = findViewById(R.id.gvPhuKien);

        adapterSanPham = new AdapterSanPham(PhukienActivity.this,R.layout.layout_sanpham,MainActivity.dsSanPhamPhuKien);
        gvPhuKien.setAdapter(adapterSanPham);
    }
}
