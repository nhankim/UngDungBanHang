package com.example.administrator.ungdungbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {

    EditText edtTen, edtEmail, edtPhone, edtDiaChi;
    Button btnXacNhan, btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edtTen.getText().toString();
                final String email = edtEmail.getText().toString();
                final String sodienthoai = edtPhone.getText().toString();
                final String diachi = edtDiaChi.getText().toString();
                if (ten.length() > 0 && email.length() > 0 && sodienthoai.length() > 0 && diachi.length() > 0) {
                    //gửi dữ liệu lên internet
                    RequestQueue requestQueue = Volley.newRequestQueue(ThongTinKhachHangActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Connect.url_thongtinkhachhang,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(final String madonhang) {
                                    Log.d("madonhang",madonhang);
                                    if (Integer.parseInt(madonhang) > 0){
                                        RequestQueue queue = Volley.newRequestQueue(ThongTinKhachHangActivity.this);
                                        StringRequest request = new StringRequest(Request.Method.POST, Connect.url_chitietdonhang,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        if (response.equals("1")){
                                                            MainActivity.dsSanPhamTrongGioHang.clear();
                                                            Toast.makeText(ThongTinKhachHangActivity.this,"Bạn đã mua hàng thành công",Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(ThongTinKhachHangActivity.this,MainActivity.class);
                                                            startActivity(intent);
                                                        }else {
                                                            Toast.makeText(ThongTinKhachHangActivity.this,"Lỗi",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {

                                                    }
                                                }){
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                JSONArray jsonArray = new JSONArray();
                                                for (int i = 0; i < MainActivity.dsSanPhamTrongGioHang.size(); i++){
                                                    JSONObject jsonObject = new JSONObject();
                                                    try {
                                                        jsonObject.put("madonhang",madonhang);
                                                        jsonObject.put("masanpham",MainActivity.dsSanPhamTrongGioHang.get(i).getId());
                                                        jsonObject.put("tensanpham",MainActivity.dsSanPhamTrongGioHang.get(i).getTenSanPham());
                                                        jsonObject.put("giasanpham",MainActivity.dsSanPhamTrongGioHang.get(i).getGiaSanPham());
                                                        jsonObject.put("soluongsanpham",MainActivity.dsSanPhamTrongGioHang.get(i).getSoluong());
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    jsonArray.put(jsonObject);
                                                }
                                                HashMap<String,String> hashMap = new HashMap<>();
                                                hashMap.put("json",jsonArray.toString());
                                                return hashMap;
                                            }
                                        };
                                        queue.add(request);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("email",email);
                            hashMap.put("sodienthoai",sodienthoai);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(ThongTinKhachHangActivity.this, "Dữ liệu rỗng. Hãy kiểm tra lại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addControls() {
        edtTen = findViewById(R.id.edtTen);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}
