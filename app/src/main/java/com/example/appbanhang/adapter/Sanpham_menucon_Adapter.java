package com.example.appbanhang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sanpham_menucon_Adapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayListsanpham;

    public Sanpham_menucon_Adapter(Context context, ArrayList<SanPham> arrayListsanpham) {
        this.context = context;
        this.arrayListsanpham = arrayListsanpham;
    }

    @Override
    public int getCount() {
        return arrayListsanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListsanpham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayListsanpham.get(position).getId_sp();
    }

    public class ViewHolder{
        ImageView imageView_sanpham_menucon;
        TextView textView_sp_ten,textView_sp_giahientai,textView_sp_giabandau,textView_sp_gioithieu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null) {
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_sanpham, null);
                viewHolder.imageView_sanpham_menucon = (ImageView) convertView.findViewById(R.id.imageview_item_sanpham);
                viewHolder.textView_sp_ten = (TextView) convertView.findViewById(R.id.tv_item_tensanpham);
                viewHolder.textView_sp_giahientai = (TextView) convertView.findViewById(R.id.tv_item_giahientai);
                viewHolder.textView_sp_giabandau = (TextView) convertView.findViewById(R.id.tv_item_giabandau);

                viewHolder.textView_sp_gioithieu = (TextView) convertView.findViewById(R.id.tv_item_gioithieu);
                convertView.setTag(viewHolder);
            }
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
            SanPham s= (SanPham) getItem(position);
            viewHolder.textView_sp_ten.setText(s.getTen_sp());
             DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//format hiển thị giá tiền
            viewHolder.textView_sp_giahientai.setText("gia : "+decimalFormat.format(s.getGiahientai_sp())+"D");
            viewHolder.textView_sp_giabandau.setText("gia : "+decimalFormat.format(s.getGiabandau_sp())+"D");
            viewHolder.textView_sp_giabandau.setPaintFlags(viewHolder.textView_sp_giabandau.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

            viewHolder.textView_sp_gioithieu.setText(s.getGioithieu_sp());

            viewHolder.textView_sp_ten.setMaxLines(1);
            viewHolder.textView_sp_ten.setEllipsize(TextUtils.TruncateAt.END);
            viewHolder.textView_sp_gioithieu.setMaxLines(2);
            viewHolder.textView_sp_gioithieu.setEllipsize(TextUtils.TruncateAt.END);

            //load (duong url de lay hinh)  placeholder(hinh anh cho`) error (hinh anh loi) into(gan vao imageview can thiet)
            Picasso.with(context).load(s.getHinh_sp()).placeholder(R.drawable.load).error(R.drawable.error)
                    .into(viewHolder.imageView_sanpham_menucon,null);

        return convertView;
    }
}
