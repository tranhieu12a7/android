package com.example.appbanhang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.model.SanPham;
import com.example.appbanhang.server;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_Chitietsanpham extends AppCompatActivity {

    ImageView imageView_hinh;
    Toolbar toolbarchitiet;
    TextView textView_ten,textView_giabandau,textView_giahientai,textView_soluong,textView_gioithieu;
    Button button_themvaogiohang,button_cong,button_tru;
    Intent intent;
    String ten,gioithieu,hinh;
    Integer giabandau,giahientai;
    int khuyenmai;
    int id;
    GioHang gioHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__chitietsanpham);

        Anhxa();
        intent=getIntent();
        id= intent.getIntExtra("idsanpham",0);

        LoadDataChitietsanpham();
        sukien_button_themgiohang();
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=v.getId();
                switch (i)
                {
                    case R.id.button_cong:
                        int n1= Integer.parseInt(textView_soluong.getText().toString());
                        n1++;
                        textView_soluong.setText(String.valueOf(n1));
                        if(n1>9)
                            button_cong.setEnabled(false);
                        else {
                            button_tru.setEnabled(true);
                            button_cong.setEnabled(true);
                        }
                        break;
                    case R.id.button_tru:
                        int n2= Integer.parseInt(textView_soluong.getText().toString());
                        n2--;
                        textView_soluong.setText(String.valueOf(n2));
                        if(n2<=1)
                            button_tru.setEnabled(false);
                        else {
                            button_cong.setEnabled(true);
                            button_tru.setEnabled(true);
                        }
                        break;
                }
            }
        };
        button_cong.setOnClickListener(onClickListener);
        button_tru.setOnClickListener(onClickListener);
        setSupportActionBar(toolbarchitiet);

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



    private void sukien_button_themgiohang() {
        button_themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int soluong = Integer.parseInt(textView_soluong.getText().toString());
                if(MainActivity.arrayListgiohang.size()<=0){

                    Integer thanhtien=soluong*giahientai;
                    gioHang=new GioHang(id,ten,hinh,soluong,giahientai,thanhtien);
                    MainActivity.arrayListgiohang.add(gioHang);
                }
                else
                {
                    if(MainActivity.arrayListgiohang.size()>0)
                    {
                        boolean key=true;
                        for(int i=0;i<MainActivity.arrayListgiohang.size();i++)
                        {
                            if(MainActivity.arrayListgiohang.get(i).getIdsanpham()==id)
                            {
                                int sl=  MainActivity.arrayListgiohang.get(i).getSoluong();
                                MainActivity.arrayListgiohang.get(i).setSoluong(sl+soluong);
                                sl=MainActivity.arrayListgiohang.get(i).getSoluong();
                                MainActivity.arrayListgiohang.get(i).setTongtien(sl,MainActivity.arrayListgiohang.get(i).getDongia());
                                key=false;
                                break;
                            }

                        }
                        if(key==true)
                        {
                            int thanhtien=soluong*giahientai;
                            gioHang=new GioHang(id,ten,hinh,soluong,giahientai,thanhtien);
                            MainActivity.arrayListgiohang.add(gioHang);
                            key=false;

                        }
                    }
                }
                Intent intent =new Intent(getApplicationContext(),Main_Giohang.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void LoadDataChitietsanpham() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest_chitiet= new StringRequest(Request.Method.POST, server.DuongdanIP_sanpham_idsanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    JSONArray jsonArray ;
                    try {
                        jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                             id = jsonObject.getInt("id_sp");
                             ten = jsonObject.getString("ten_sp");
                             hinh = jsonObject.getString("hinh_sp");

                            khuyenmai = jsonObject.getInt("sp_khuyenmai");
                            giahientai = jsonObject.getInt("giahientai_sp");
                            giabandau=jsonObject.getInt("giabandau_sp");
                            gioithieu = jsonObject.getString("gioithieu_sp");


                            DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                            textView_ten.setText(ten);
                            textView_giabandau.setText("Gia :"+String.valueOf(decimalFormat.format(giabandau))+"D");
                            textView_giabandau.setPaintFlags(textView_giabandau.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                            textView_giahientai.setText("Gia :"+String.valueOf(decimalFormat.format(giahientai))+"D");
                            Picasso.with(getApplicationContext()).load(hinh).placeholder(R.drawable.load).error(R.drawable.error).into(imageView_hinh);
                            textView_gioithieu.setText(gioithieu);
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
                HashMap<String,String> hashMap=new HashMap<String,String>();
                hashMap.put("id", String.valueOf(id));
                return hashMap;
            }
        };
        requestQueue.add(stringRequest_chitiet);
    }

    private void Anhxa() {
        imageView_hinh=(ImageView)findViewById(R.id.imageview_chitiet);
        textView_ten=(TextView)findViewById(R.id.textview_chitiet_tensanpham);
        textView_giabandau=(TextView)findViewById(R.id.textview_chitiet_giabandau);
        textView_giahientai=(TextView)findViewById(R.id.textview_chitiet_giahientai);
        textView_soluong=(TextView)findViewById(R.id.textview_chitiet_soluong);
        textView_gioithieu=(TextView)findViewById(R.id.textview_chitiet_gioithieu);
        toolbarchitiet=(Toolbar)findViewById(R.id.toolbar_chitiet);


        button_themvaogiohang=(Button)findViewById(R.id.button_themvaogiohang);
        button_cong=(Button)findViewById(R.id.button_cong);
        button_tru=(Button)findViewById(R.id.button_tru);

    }
}
