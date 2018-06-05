package com.example.administrator.ungdungbanhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ungdungbanhang.R;
import com.example.administrator.ungdungbanhang.model.LoaiSP;

import java.util.List;

public class AdapterLoaiSP extends ArrayAdapter<LoaiSP> {
    @NonNull Activity context;
    int resource;
    @NonNull List<LoaiSP> objects;
    public AdapterLoaiSP(@NonNull Activity context, int resource, @NonNull List<LoaiSP> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        ImageView imgMenuRow = row.findViewById(R.id.imgMenuRow);
        TextView txtMenuRow = row.findViewById(R.id.txtMenuRow);

        LoaiSP loaiSP = this.objects.get(position);
        imgMenuRow.setImageResource(loaiSP.getHinhanhloaisanpham());
        txtMenuRow.setText(loaiSP.getTenloaisanpham());
        return row;
    }
}
