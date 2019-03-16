package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.MenuCon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuCon_Adapter extends BaseAdapter {
    Context context;
    ArrayList<MenuCon> arrayListmenucon;

    public MenuCon_Adapter(Context context, ArrayList<MenuCon> arrayListmenucon) {
        this.context = context;
        this.arrayListmenucon = arrayListmenucon;
    }

    @Override
    public int getCount() {
        return arrayListmenucon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListmenucon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayListmenucon.get(position).getId();
    }
    public class ViewHolder{
        ImageView img_menucon;
        TextView tv_menucon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.grid_item_menucon,null);
            viewHolder.img_menucon=(ImageView)convertView.findViewById(R.id.imageview_gridview_menucon);
            viewHolder.tv_menucon=(TextView)convertView.findViewById(R.id.tv_gridview_tenmenucon);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        MenuCon menuCon= (MenuCon) getItem(position);
        viewHolder.tv_menucon.setText(menuCon.getTen());
        Picasso.with(context).load(menuCon.getHinh()).placeholder(R.drawable.load).error(R.drawable.error).into(viewHolder.img_menucon);
        return convertView;
    }
}
