package com.example.administrator.ungdungbanhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.activity.GioHangActivity;
import com.example.administrator.ungdungbanhang.activity.MainActivity;
import com.example.administrator.ungdungbanhang.model.GioHang;
import com.example.administrator.ungdungbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterItemGioHang extends ArrayAdapter<GioHang> {
    @NonNull
    Activity context;
    int resource;
    @NonNull List<GioHang> objects;

    public AdapterItemGioHang(@NonNull Activity context, int resource, @NonNull List<GioHang> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        ImageView imgSanPhamGioHang = row.findViewById(R.id.imgSanPhamGioHang);
        TextView txtTenSanPhamGioHang = row.findViewById(R.id.txtTenSanPhamGioHang);
        TextView txtGiaSanPhamGioHang = row.findViewById(R.id.txtGiaSanPhamGioHang);
        TextView txtSoLuongMua = row.findViewById(R.id.txtSoLuongMua);

        Picasso.with(this.context).load(MainActivity.dsSanPhamTrongGioHang.get(position).getHinhSanPham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgSanPhamGioHang);
        txtTenSanPhamGioHang.setText(MainActivity.dsSanPhamTrongGioHang.get(position).getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSanPhamGioHang.setText("Giá: " + decimalFormat.format(MainActivity.dsSanPhamTrongGioHang.get(position).getGiaSanPham()) + " VNĐ");
        txtSoLuongMua.setText("Số lượng: " + MainActivity.dsSanPhamTrongGioHang.get(position).getSoluong());


        return row;
    }
}
