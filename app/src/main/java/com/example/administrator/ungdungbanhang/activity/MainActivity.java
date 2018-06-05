package com.example.administrator.ungdungbanhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.adapter.AdapterLoaiSP;
import com.example.administrator.ungdungbanhang.adapter.AdapterSanPhamMoi;
import com.example.administrator.ungdungbanhang.model.GioHang;
import com.example.administrator.ungdungbanhang.model.LoaiSP;
import com.example.administrator.ungdungbanhang.model.SanPham;
import com.example.administrator.ungdungbanhang.ultil.CheckInternet;
import com.example.administrator.ungdungbanhang.ultil.Connect;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarManHinhChinh;
    ViewFlipper viewFlipper;
    GridView gvSanPhanMoiNhat;
    ListView lvMenu;
    DrawerLayout drawerLayout;

    ArrayList<LoaiSP> dsLoaiSP;
    AdapterLoaiSP adapterLoaiSP;

    ArrayList<SanPham> dsSanPhamMoiNhat;
    AdapterSanPhamMoi adapterSanPhamMoiNhat;

    public static ArrayList<SanPham> dsSanPhamDienThoai;
    public static ArrayList<SanPham> dsSanPhamLaptop;
    public static ArrayList<SanPham> dsSanPhamPhuKien;

    public static ArrayList<GioHang> dsSanPhamTrongGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Kiểm tra xem có kết nối internet không
        if (CheckInternet.haveNetworkConnection(MainActivity.this))
        {
            addControls();
            ActionToolBar();
            SetViewFlipper();
            getDuLieuSanPhamMoiNhat();
            getDuLieuSanPham();
            addEvents();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);
            builder.setTitle("Lỗi kết nối Internet");
            builder.setMessage("Bạn hãy kiểm tra lại kết nối Internet");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }

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
                Intent intent = new Intent(MainActivity.this,GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        clickOnItemMenu();
        clickOnItemGridView();
    }

    private void clickOnItemGridView() {
        gvSanPhanMoiNhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ChiTietSanPhamActivity.class);
                intent.putExtra("SanPham",dsSanPhamMoiNhat.get(position));
                startActivity(intent);
            }
        });
    }

    private void clickOnItemMenu() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intentDienthoai = new Intent(MainActivity.this,DienThoaiActivity.class);
                        startActivity(intentDienthoai);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intentLaptop = new Intent(MainActivity.this,LaptopActivity.class);
                        startActivity(intentLaptop);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intentPhukien = new Intent(MainActivity.this,PhukienActivity.class);
                        startActivity(intentPhukien);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intentLienhe = new Intent(MainActivity.this,LienHeActivity.class);
                        startActivity(intentLienhe);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void getDuLieuSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Connect.url_sanpham,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null)
                        {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    int id = jsonObject.getInt("id");
                                    String tensanpham = jsonObject.getString("tensp");
                                    String hinhanhsanpham = jsonObject.getString("hinhanhsp");
                                    int giasanpham = jsonObject.getInt("giasp");
                                    String motasanpham = jsonObject.getString("motasp");
                                    int idLoaisanpham = jsonObject.getInt("idloaisp");
                                    if (idLoaisanpham == 1)
                                    {
                                        dsSanPhamDienThoai.add(new SanPham(id,tensanpham,hinhanhsanpham,giasanpham,motasanpham,idLoaisanpham));
                                    }
                                    else {
                                        if (idLoaisanpham == 2) {
                                            dsSanPhamLaptop.add(new SanPham(id,tensanpham,hinhanhsanpham,giasanpham,motasanpham,idLoaisanpham));
                                        }
                                        else {
                                            dsSanPhamPhuKien.add(new SanPham(id,tensanpham,hinhanhsanpham,giasanpham,motasanpham,idLoaisanpham));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Connect.url_sanphammoinhat,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null)
                        {
                            for (int i = 0; i < response.length(); i++)
                            {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    int id = jsonObject.getInt("id");
                                    String tensanpham = jsonObject.getString("tensp");
                                    String hinhanhsanpham = jsonObject.getString("hinhanhsp");
                                    int giasanpham = jsonObject.getInt("giasp");
                                    String motasanpham = jsonObject.getString("motasp");
                                    int idLoaisanpham = jsonObject.getInt("idloaisp");
                                    dsSanPhamMoiNhat.add(new SanPham(id,tensanpham,hinhanhsanpham,giasanpham,motasanpham,idLoaisanpham));
                                    adapterSanPhamMoiNhat.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Loi",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void SetViewFlipper() {
        ArrayList<String> arrHinh = new ArrayList<>();
        arrHinh.add("https://cdn4.tgdd.vn/Products/Images/42/157031/Slider/vi-vn-samsung-galaxy-a6-1.gif");
        arrHinh.add("https://cellphones.com.vn/sforum/wp-content/uploads/2017/10/iPhone-X-iPhone-8-Banner-web_rightslide_680X370-600x326.jpg");
        arrHinh.add("https://i.pinimg.com/originals/06/4f/69/064f69012271aa4566ddc10ea136e7ac.jpg");
        arrHinh.add("http://cache.media.techz.vn/upload/2017/03/30/tz-01490833363-image-1490833196-Screen%20Shot%202017-03-30%20at%206.57.51%20AM.png");
        arrHinh.add("http://www.laptopthienvuong.com/uploads/khai-ga-it/banner-dell-2.jpg");
        for (int i = 0; i < arrHinh.size(); i++)
        {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.with(MainActivity.this).load(arrHinh.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.in_right);
        Animation out = AnimationUtils.loadAnimation(MainActivity.this,R.anim.out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarManHinhChinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarManHinhChinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarManHinhChinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void addControls() {
        toolbarManHinhChinh = findViewById(R.id.toolbarManHinhChinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        gvSanPhanMoiNhat = findViewById(R.id.gvSanPhamMoiNhat);
        lvMenu = findViewById(R.id.lvMenu);
        drawerLayout = findViewById(R.id.drawerLayout);

        //set dữ liệu cho menu
        dsLoaiSP = new ArrayList<>();
        dsLoaiSP.add(new LoaiSP("Điện Thoại",R.drawable.cell_phone_icon));
        dsLoaiSP.add(new LoaiSP("Laptop",R.drawable.laptop_icon));
        dsLoaiSP.add(new LoaiSP("Phụ Kiện",R.drawable.phukien_icon));
        dsLoaiSP.add(new LoaiSP("Liên Hệ",R.drawable.lienhe));
        adapterLoaiSP = new AdapterLoaiSP(MainActivity.this,R.layout.menu_row,dsLoaiSP);
        lvMenu.setAdapter(adapterLoaiSP);

        dsSanPhamMoiNhat = new ArrayList<>();
        adapterSanPhamMoiNhat = new AdapterSanPhamMoi(MainActivity.this,R.layout.layout_sanphammoi,dsSanPhamMoiNhat);
        gvSanPhanMoiNhat.setAdapter(adapterSanPhamMoiNhat);

        dsSanPhamDienThoai = new ArrayList<>();
        dsSanPhamLaptop = new ArrayList<>();
        dsSanPhamPhuKien = new ArrayList<>();

        if (dsSanPhamTrongGioHang != null)
        {

        }
        else
        {
            dsSanPhamTrongGioHang = new ArrayList<>();
        }
    }
}
