package com.example.appbanhang.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.Adapter_menucha;
import com.example.appbanhang.adapter.MenuCon_Adapter;
import com.example.appbanhang.model.MenuCha;
import com.example.appbanhang.model.MenuCon;
import com.example.appbanhang.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DanhMucMenuCon extends AppCompatActivity {

    ImageView imageView_icon_hinh_menucon;
    TextView tv_icon_ten_menucon;
    GridView gridView_menucon;
    ArrayList<MenuCon> arraymenucon;
    MenuCon_Adapter menuCon_adapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ListView listView_menu;

    ArrayList<MenuCha> arrayListmenucha;
    Adapter_menucha adapter_menucha;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhmuc_menucon);
        Anhxa();
        Intent intent=getIntent();
        i=  intent.getExtras().getInt("idmenucha");
        String s=intent.getExtras().getString("tentoolbar");
        toolbar.setTitle(s);
        GetdataGridview();

        gridView_menucon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent_danhmucmenucon_mainsanpham=new Intent(DanhMucMenuCon.this,Main_SanPham.class);
               intent_danhmucmenucon_mainsanpham.putExtra("idmenucon",(int)id);
               startActivity(intent_danhmucmenucon_mainsanpham);
            }
        });
        Actionbar();
        GetDataMenucha();
        MyOnClick_ListviewMenu();
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);//hàm hỗ trợ chuyển toolbar thành actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//get để nút menu hiện ra
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);//thay đổi icon nut menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            //set sự kiện nut menu
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);//phương thức mở nút menu khi nhấn vào
            }
        });

    }

    private void GetDataMenucha() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.DuongdanIP_menucha,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response!=null){
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject jsonObject;
                                try {
                                    jsonObject = response.getJSONObject(i);
                                    int id_tam=jsonObject.getInt("id_menucha");
                                    String hinh_tam=jsonObject.getString("hinh_menucha");
                                    String ten_tam=jsonObject.getString("ten_menucha");
                                    arrayListmenucha.add(new MenuCha(id_tam,hinh_tam,ten_tam));
                                    adapter_menucha.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error load menucha",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void MyOnClick_ListviewMenu() {
        listView_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch ((int) id){
                    case 1:
                        Intent intent_trangchu = new Intent(DanhMucMenuCon.this,MainActivity.class);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        startActivity(intent_trangchu);
                        break;
                    case 2:
                        Intent intent_laptop_menucon=new Intent(DanhMucMenuCon.this,DanhMucMenuCon.class);
                        intent_laptop_menucon.putExtra("idmenucha",(int)id);
                        intent_laptop_menucon.putExtra("tentoolbar","Lap top");
                        startActivity(intent_laptop_menucon);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case 3:
                        Intent intent_dienthoai_menucon=new Intent(DanhMucMenuCon.this,DanhMucMenuCon.class);
                        intent_dienthoai_menucon.putExtra("idmenucha",(int)id);
                        intent_dienthoai_menucon.putExtra("tentoolbar","Dien thoai");
                        startActivity(intent_dienthoai_menucon);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;

                }
            }
        });
    }
    private void GetdataGridview() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
         //phải gọi StringRequest để post lên server thay cho việc gọi JsonArrayRequest

        StringRequest stringRequest=new StringRequest(Request.Method.POST,server.DuongdanIP_menucon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            int id=jsonObject.getInt("id");
                            String ten=jsonObject.getString("ten");
                            String hinh=jsonObject.getString("hinh");
                            int id_menucha=jsonObject.getInt("id_menucha");
                            arraymenucon.add(new MenuCon(id,ten,hinh,id_menucha));
                            menuCon_adapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //phương thức post lên server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String,String>();
                param.put("idmenucha",String.valueOf(i));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {
        imageView_icon_hinh_menucon=(ImageView) findViewById(R.id.imageview_gridview_menucon);
        tv_icon_ten_menucon=(TextView)findViewById(R.id.tv_gridview_tenmenucon);
        gridView_menucon=(GridView)findViewById(R.id.gridview_menucon);
        toolbar=(Toolbar)findViewById(R.id.tbr_danhmuc_menucon);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_danhmuc_menucon);
        listView_menu=(ListView)findViewById(R.id.lv_menu);

        arrayListmenucha=new ArrayList<MenuCha>();
        adapter_menucha=new Adapter_menucha(arrayListmenucha,getApplicationContext());
        listView_menu.setAdapter(adapter_menucha);

        arraymenucon=new ArrayList<MenuCon>();
        menuCon_adapter=new MenuCon_Adapter(getApplicationContext(),arraymenucon);
        gridView_menucon.setAdapter(menuCon_adapter);
    }
}
