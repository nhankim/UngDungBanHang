package com.example.administrator.ungdungbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.model.GioHang;
import com.example.administrator.ungdungbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarChiTietSanPham;
    ImageView imgSanPhamChiTiet;
    TextView txtTenSanPhamChiTiet, txtGiaSanPhamChiTiet, txtMoTaSanPhamChiTiet, txtSoLuongMua;
    Button btnThemVaoGioHang, btnTang, btnGiam;

    int id = 0;
    String tenSanPham = "";
    int giaSanPham = 0;
    String hinhAnhSanPham = "";
    String moTaChiTiet = "";
    int idLoaiSanPham = 0;

    int soLuong = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        addControls();
        ActionToolBar();
        getInformation();
        addEvents();
    }

    private void getInformation() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("SanPham");
        id = sanPham.getId();
        tenSanPham = sanPham.getTensanpham();
        giaSanPham = sanPham.getGiasanpham();
        hinhAnhSanPham = sanPham.getHinhanhsanpham();
        moTaChiTiet = sanPham.getMotasanpham();
        idLoaiSanPham = sanPham.getIdloaisanpham();

        Picasso.with(ChiTietSanPhamActivity.this).load(hinhAnhSanPham)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgSanPhamChiTiet);
        txtTenSanPhamChiTiet.setText(tenSanPham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSanPhamChiTiet.setText("Giá: " + decimalFormat.format(giaSanPham) + " VNĐ");
        txtMoTaSanPhamChiTiet.setText(moTaChiTiet);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuGioHang:
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarChiTietSanPham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTietSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addEvents() {
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                txtSoLuongMua.setText("" + soLuong);
                if (soLuong == 5) {
                    btnTang.setVisibility(View.INVISIBLE);
                }
                if (soLuong < 5) {
                    btnGiam.setVisibility(View.VISIBLE);
                }
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong--;
                txtSoLuongMua.setText("" + soLuong);
                if (soLuong == 1){
                    btnGiam.setVisibility(View.INVISIBLE);
                }
                if (soLuong > 1){
                    btnTang.setVisibility(View.VISIBLE);
                }
            }
        });
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dsSanPhamTrongGioHang.size() > 0)
                {
                    int sl = Integer.parseInt(txtSoLuongMua.getText().toString());
                    boolean tontai = false;
                    for (int i = 0; i < MainActivity.dsSanPhamTrongGioHang.size(); i++)
                    {
                        if (MainActivity.dsSanPhamTrongGioHang.get(i).getId() == id)
                        {
                            MainActivity.dsSanPhamTrongGioHang.get(i).setSoluong(MainActivity.dsSanPhamTrongGioHang.get(i).getSoluong() + sl);
                            if (MainActivity.dsSanPhamTrongGioHang.get(i).getSoluong() > 5) {
                                MainActivity.dsSanPhamTrongGioHang.get(i).setSoluong(5);
                            }
                            MainActivity.dsSanPhamTrongGioHang.get(i).setGiaSanPham(giaSanPham * MainActivity.dsSanPhamTrongGioHang.get(i).getSoluong());
                            tontai = true;
                        }
                    }
                    if (tontai == false)
                    {
                        int soluong = Integer.parseInt(txtSoLuongMua.getText().toString());
                        long tongGia = soluong * giaSanPham;
                        MainActivity.dsSanPhamTrongGioHang.add(new GioHang(id,tenSanPham,tongGia,hinhAnhSanPham,soluong));
                    }
                }else
                {
                    int soluong = Integer.parseInt(txtSoLuongMua.getText().toString());
                    long tongGia = soluong * giaSanPham;
                    MainActivity.dsSanPhamTrongGioHang.add(new GioHang(id,tenSanPham,tongGia,hinhAnhSanPham,soluong));
                }

                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        toolbarChiTietSanPham = findViewById(R.id.toolbarChiTietSanPham);
        imgSanPhamChiTiet = findViewById(R.id.imgSanPhamChiTiet);
        txtTenSanPhamChiTiet = findViewById(R.id.txtTenSanPhamChiTiet);
        txtGiaSanPhamChiTiet = findViewById(R.id.txtGiaSanPhamChiTiet);
        txtMoTaSanPhamChiTiet = findViewById(R.id.txtMoTaSanPhamChiTiet);
        txtSoLuongMua = findViewById(R.id.txtSoLuongMua);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);

        txtSoLuongMua.setText("" + soLuong);

    }
}


