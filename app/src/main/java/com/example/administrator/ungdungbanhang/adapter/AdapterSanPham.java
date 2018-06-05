package com.example.administrator.ungdungbanhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterSanPham extends ArrayAdapter<SanPham> {

    Activity context;
    int resource;
    List<SanPham> objects;

    public AdapterSanPham(@NonNull Activity context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);

        ImageView imgItemSanpham = item.findViewById(R.id.imgSanPham);
        TextView txtItemTensanpham = item.findViewById(R.id.txtTenSanPham);
        TextView txtItemGiasanpham = item.findViewById(R.id.txtGiaSanPham);

        SanPham sanPham = this.objects.get(position);
        Picasso.with(this.context).load(sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgItemSanpham);
        txtItemTensanpham.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtItemGiasanpham.setText("Giá: " + decimalFormat.format(sanPham.getGiasanpham()) + " VNĐ");

        return item;
    }
}

