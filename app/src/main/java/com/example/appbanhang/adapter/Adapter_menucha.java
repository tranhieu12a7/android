package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.MenuCha;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_menucha extends BaseAdapter {
    ArrayList<MenuCha> arraymenucha;
    Context context;

    public Adapter_menucha(ArrayList<MenuCha> arraymenucha, Context context) {
        this.arraymenucha = arraymenucha;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraymenucha.size();
    }

    @Override
    public Object getItem(int position) {
        return arraymenucha.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arraymenucha.get(position).getId();
    }
    public class ViewHolder{
        ImageView imv_menucha;
        TextView tv_menucha;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item_menu,null);
            viewHolder.imv_menucha=(ImageView)convertView.findViewById(R.id.imv_menucha);
            viewHolder.tv_menucha=(TextView)convertView.findViewById(R.id.tv_menucha);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        MenuCha m= (MenuCha) getItem(position);
        viewHolder.tv_menucha.setText(m.getTen());
        Picasso.with(context).load(m.getHinh()).placeholder(R.drawable.load).error(R.drawable.error)
                .into(viewHolder.imv_menucha,null);

        return convertView;
    }
}
