package com.example.appbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.Sanpham_menucon_Adapter;
import com.example.appbanhang.model.SanPham;
import com.example.appbanhang.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main_SanPham extends AppCompatActivity {

    ListView listView_sanpham_in_menucon;
    ArrayList<SanPham> arrayListsanpham;
    Toolbar toolbarsanpham;
    int i=0;
    Sanpham_menucon_Adapter sanpham_menucon_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__san_pham);
        listView_sanpham_in_menucon=(ListView)findViewById(R.id.listview_sanpham);
        toolbarsanpham=(Toolbar)findViewById(R.id.toobar_sanpham);

        arrayListsanpham=new ArrayList<SanPham>();


        sanpham_menucon_adapter=new Sanpham_menucon_Adapter(getApplicationContext(),arrayListsanpham);
        listView_sanpham_in_menucon.setAdapter(sanpham_menucon_adapter);

        final Intent intent=getIntent();
        i=  intent.getExtras().getInt("idmenucon");
        Getdatabase();
        listView_sanpham_in_menucon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(getApplicationContext(),Main_Chitietsanpham.class);
                intent1.putExtra("idsanpham",(int) id);
                startActivity(intent1);
            }
        });
        setSupportActionBar(toolbarsanpham);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_giohang:
                Intent intent=new Intent(getApplicationContext(),Main_Giohang.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Getdatabase() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,server.DuongdanIP_sanpham_menucon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    JSONArray jsonArray ;
                    try {
                        jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id_sp");
                            String ten = jsonObject.getString("ten_sp");
                            String hinh = jsonObject.getString("hinh_sp");

                            int khuyenmai = jsonObject.getInt("sp_khuyenmai");
                            Integer giahientai = jsonObject.getInt("giahientai_sp");
                            Integer giabandau=jsonObject.getInt("giabandau_sp");
                            String gioithieu = jsonObject.getString("gioithieu_sp");
                            int id_menucon = jsonObject.getInt("id_menucon");

                             giabandau = jsonObject.getInt("giabandau_sp");

                            arrayListsanpham.add(new SanPham(id,ten,hinh,khuyenmai,giabandau,giahientai,gioithieu,id_menucon));
                            sanpham_menucon_adapter.notifyDataSetChanged();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String,String>();
                param.put("id",String.valueOf(i));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
