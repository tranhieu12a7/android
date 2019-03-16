package com.example.appbanhang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Nguoidung;
import com.example.appbanhang.server;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Main_Nguoidung extends AppCompatActivity {

    Button button_xacnhan;
    EditText editText_ten,editText_diachi,editText_sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__nguoidung);

        Anhxa();
        button_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveThongtin();
                Intent intent=new Intent(getApplicationContext(),Main_Giohang.class);
                finish();
                startActivity(intent);
            }
        });
        LoadThiongtin();
    }

    private void LoadThiongtin() {
        SharedPreferences sharedPreferences=getSharedPreferences("nguoidung",MODE_PRIVATE);
        if(sharedPreferences!=null)
        {
            Gson gson=new Gson();
            String object=sharedPreferences.getString("object_nguoidung","");
            Nguoidung nguoidung=gson.fromJson(object,Nguoidung.class);
            editText_ten.setText(nguoidung.getTen());
            editText_diachi.setText(nguoidung.getDiachi());
            editText_sdt.setText(nguoidung.getSdt());
        }
    }
    private void SaveThongtin() {



        final String ten=editText_ten.getText().toString();
        final String diachi=editText_diachi.getText().toString();
        final String sdt=editText_sdt.getText().toString().trim();
        SharedPreferences sharedPreferences=getSharedPreferences("nguoidung",MODE_PRIVATE);
        SharedPreferences.Editor e=sharedPreferences.edit();
        Gson gson=new Gson();


        String object=sharedPreferences.getString("object_nguoidung","");
        Nguoidung nguoidung=gson.fromJson(object,Nguoidung.class);
        if( sdt.equals(nguoidung.getSdt()))
        {
            Toast.makeText(getApplicationContext(),"Thong tin sdt nay da ton tai ban khong can phai dien thong tin nua",Toast.LENGTH_LONG).show();
        }
        else {


            final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server.DuongdanIP_Postnguoidung, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("kiemtra", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("tennguoidung", ten);
                    hashMap.put("diachinguoidung", diachi);
                    hashMap.put("sdtnguoidung", sdt);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
            e.clear();
            nguoidung = new Nguoidung(ten, diachi, sdt);
            String objectNguoidung = gson.toJson(nguoidung);
            e.putString("object_nguoidung", objectNguoidung);
            e.commit();


        }

    }

    private void Anhxa() {
        editText_ten=(EditText)findViewById(R.id.edittext_tennguoidung);
        editText_diachi=(EditText)findViewById(R.id.edittext_diachinguoidung);
        editText_sdt=(EditText)findViewById(R.id.edittext_sdtnguoidung);
        button_xacnhan=(Button)findViewById(R.id.button_xacnhan);
    }

}
