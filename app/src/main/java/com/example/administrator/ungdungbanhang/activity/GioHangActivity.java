package com.example.administrator.ungdungbanhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.adapter.AdapterItemGioHang;
import com.example.administrator.ungdungbanhang.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {

    Toolbar toolbarGioHang;
    ListView lvGioHang;
    TextView txtThongBao;
    public static TextView txtGiaTien;
    Button btnXacNhan, btnTiepTucMuaHang;

    AdapterItemGioHang adapterItemGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        addControls();
        ActionToolBar();
        tongGia();
        addEvents();
    }

    private void addEvents() {
        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dsSanPhamTrongGioHang.size() <= 0){
                    Toast.makeText(GioHangActivity.this,"Bạn chưa có sản phẩm nào trong giỏ hàng",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(GioHangActivity.this,ThongTinKhachHangActivity.class);
                    startActivity(intent);
                }
            }
        });

        lvGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Xóa Sản Phẩm");
                builder.setMessage("Bạn muốn xóa sản phẩm này");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dsSanPhamTrongGioHang.remove(position);
                        adapterItemGioHang.notifyDataSetChanged();
                        tongGia();
                        if (MainActivity.dsSanPhamTrongGioHang.size() <= 0)
                        {
                            txtThongBao.setVisibility(View.VISIBLE);
                            adapterItemGioHang.notifyDataSetChanged();
                        }
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

                return true;
            }
        });
    }

    public static void tongGia() {
        long tongTien = 0;
        for (int i = 0; i < MainActivity.dsSanPhamTrongGioHang.size(); i++)
        {
            tongTien += MainActivity.dsSanPhamTrongGioHang.get(i).getGiaSanPham();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaTien.setText(decimalFormat.format(tongTien) + " VNĐ");
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbarGioHang = findViewById(R.id.toolbarGioHang);
        txtThongBao = findViewById(R.id.txtThongBao);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTiepTucMuaHang = findViewById(R.id.btnTiepTucMua);
        lvGioHang = findViewById(R.id.lvGioHang);

        adapterItemGioHang = new AdapterItemGioHang(GioHangActivity.this,R.layout.item_giohang,MainActivity.dsSanPhamTrongGioHang);
        lvGioHang.setAdapter(adapterItemGioHang);


        if (MainActivity.dsSanPhamTrongGioHang.size() <= 0)
        {
            txtThongBao.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        }
        else
        {
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }
}
