package com.example.appbanhang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Giohang_Adapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang>arrayList;


    public Giohang_Adapter(Context context, ArrayList<GioHang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getIdsanpham();
    }

    public class ViewHolder{
        ImageView imageView_giohang;
        TextView textView_tensp,textView_gia,textView_soluong;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.list_item_giohang,null);
            viewHolder.textView_tensp=(TextView)convertView.findViewById(R.id.tv_giohangitem_tensanpham);
            viewHolder.textView_soluong=(TextView)convertView.findViewById(R.id.tv_giohangitem_soluong);

            viewHolder.textView_gia=(TextView)convertView.findViewById(R.id.tv_giohangitem_giahientai);
            viewHolder.imageView_giohang=(ImageView)convertView.findViewById(R.id.imageview_giohangitem_sanpham);

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        GioHang g=arrayList.get(position);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//format hiển thị giá tiền
        viewHolder.textView_tensp.setText(g.getTensanpham());
        viewHolder.textView_tensp.setMaxLines(1);
        viewHolder.textView_tensp.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textView_gia.setText("gia : "+decimalFormat.format(g.getDongia())+" D");
        viewHolder.textView_soluong.setText(String.valueOf(g.getSoluong()));

        Picasso.with(context).load(g.getHinh()).placeholder(R.drawable.load).error(R.drawable.error).into(viewHolder.imageView_giohang);


        return convertView;
    }
}
