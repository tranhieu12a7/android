package com.example.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.Main_Chitietsanpham;
import com.example.appbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
//bảng vẻ cho recyclerview

public class Sanpham_Adapter extends RecyclerView.Adapter<Sanpham_Adapter.ItemHolder> {
    //inport các method của Recyclerview Adapter
    Context context;
    ArrayList<SanPham> arrayListsanpham;

    public Sanpham_Adapter(Context context, ArrayList<SanPham> arrayListsanpham) {
        this.context = context;
        this.arrayListsanpham = arrayListsanpham;
    }

    @NonNull
    //khởi tạo view cho itemholder vì lúc đầu chưa có
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Khởi tạo View cho itemholder -> gán layout "list_item_recycaler_sanphamhot"
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycalerview_sanphamhot,null);
        ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }
    //set giá trị vào trong các imageview và textview trong view của itemholder
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int i) {
        SanPham sanPham = arrayListsanpham.get(i);
        holder.tv_tensp.setText(sanPham.getTen_sp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//format hiển thị giá tiền
        holder.tv_giahientai.setText("Giá : "+decimalFormat.format(sanPham.getGiahientai_sp())+" Đ ");
        holder.tv_giabandau.setText("Giá : "+decimalFormat.format(sanPham.getGiabandau_sp())+"Đ");
        holder.tv_giabandau.setPaintFlags(holder.tv_giabandau.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        //Đổ hình ảnh vào imgview
        Picasso.with(context).load(sanPham.getHinh_sp()).placeholder(R.drawable.load).error(R.drawable.error).into(holder.hinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arrayListsanpham.size();
    }
//khởi tạo itemholder (bắt buộc phải có trong Recycalerview)
    //Ánh xạ
    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView hinhsanpham;
        public TextView tv_tensp,tv_giahientai,tv_giabandau;

        public ItemHolder(final View itemView) {
            super(itemView);
           hinhsanpham =(ImageView) itemView.findViewById(R.id.imageview_sanphamhot);
           tv_tensp=(TextView)itemView.findViewById(R.id.tv_recyal_tensp);
           tv_giahientai=(TextView)itemView.findViewById(R.id.tv_recyal_giahientai);
            tv_giabandau=(TextView)itemView.findViewById(R.id.tv_recyal_giabandau);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, Main_Chitietsanpham.class);
                    intent.putExtra("idsanpham",arrayListsanpham.get(getAdapterPosition()).getId_sp());

                    context.startActivity(intent);
                }
            });

        }
    }
}

