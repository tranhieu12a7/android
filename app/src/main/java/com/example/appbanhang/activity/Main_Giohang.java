package com.example.appbanhang.activity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.appbanhang.adapter.Giohang_Adapter;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.model.Nguoidung;
import com.example.appbanhang.server;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_Giohang extends AppCompatActivity {


    ListView listView;
    Button button_thongtinkhach,button_thanhtoan,button_tieptucmuahang;
    Giohang_Adapter giohang_adapter;
    TextView textView_tongtien,textView_tongsoluong;
    String sdt="0";
    long tongtien=0;
    Integer tongsoluong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__giohang);

        Anhxa();
        button_tieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        button_thongtinkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_thongtinkhac=new Intent(getApplicationContext(),Main_Nguoidung.class);
                startActivity(intent_thongtinkhac);
                finish();
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("nguoidung",MODE_PRIVATE);
        if(sharedPreferences!=null)
        {
            Gson gson=new Gson();
            String object=sharedPreferences.getString("object_nguoidung","");
            Nguoidung nguoidung=gson.fromJson(object,Nguoidung.class);
            sdt=nguoidung.getSdt();

        }
        LoadtongtienAndtongsoluong(MainActivity.arrayListgiohang);

        Sukienthanhtoan();
        Sukienxoa();



    }

    private void Sukienxoa() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();
                final Dialog dialog=new Dialog(Main_Giohang.this);
                dialog.setContentView(R.layout.dialog_xoasp_giohang);


                TextView textViewDialogten=(TextView)dialog.findViewById(R.id.dialog_tensp);
                textViewDialogten.setText(MainActivity.arrayListgiohang.get(position).getTensanpham().toString());

                final EditText editTextDialogsoluong=(EditText)dialog.findViewById(R.id.edittex_dialog_soluong);
                editTextDialogsoluong.setText(String.valueOf(MainActivity.arrayListgiohang.get(position).getSoluong()));
                dialog.show();
                Button buttonDialogxacnhan=(Button)dialog.findViewById(R.id.button_dialog_xacnhan);
                buttonDialogxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int sl = Integer.parseInt(editTextDialogsoluong.getText().toString());
                        if (sl >100) {
                            Toast.makeText(getApplicationContext(),"so luong qua lon xin loi ban khong the tiep nhan duoc",Toast.LENGTH_SHORT).show();
                            sl=100;
                        }
                        MainActivity.arrayListgiohang.get(position).setSoluong(sl);
                        MainActivity.arrayListgiohang.get(position).setTongtien(MainActivity.arrayListgiohang.get(position).getSoluong(),
                                MainActivity.arrayListgiohang.get(position).getDongia());
                        giohang_adapter.notifyDataSetChanged();
                        double tt = 0, tsl = 0;
                        for (int i = 0; i < MainActivity.arrayListgiohang.size(); i++) {
                            tt += MainActivity.arrayListgiohang.get(i).getTongtien();
                            tsl += MainActivity.arrayListgiohang.get(i).getSoluong();
                        }
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
                        textView_tongtien.setText("Tong : " + decimalFormat.format(tt) + " D");
                        textView_tongsoluong.setText("Tong SL : " + decimalFormat.format(tsl) + " D");
                        dialog.dismiss();
                    }
                });
                Button buttonDialogxoa=(Button)dialog.findViewById(R.id.button_dialog_xoa);
                buttonDialogxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.arrayListgiohang.remove(position);
                        giohang_adapter.notifyDataSetChanged();
                        double tt=0,tsl=0;
                        for(int i=0;i<MainActivity.arrayListgiohang.size();i++){
                            tt+= MainActivity.arrayListgiohang.get(i).getTongtien();
                            tsl+= MainActivity.arrayListgiohang.get(i).getSoluong();
                        }
                        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                        textView_tongtien.setText("Tong : "+decimalFormat.format(tt)+" D");
                        textView_tongsoluong.setText("Tong SL : "+decimalFormat.format(tsl)+" D");

                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
    }

    private void Sukienthanhtoan() {
        button_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.DuongdanIP_Postdonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            if(madonhang.equals("0"))
                            {

                                Toast.makeText(getApplicationContext(),"xin loi dat hang that bai, vui long nhap lai thong tin",Toast.LENGTH_LONG).show();
                            }
                            else {


                                RequestQueue requestQueue1=Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest1= new StringRequest(Request.Method.POST, server.DuongdanIP_Postchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(!response.equals("0"))
                                        {
                                            Toast.makeText(getApplicationContext(), "dat hang thanh cong", Toast.LENGTH_SHORT).show();
                                            MainActivity.arrayListgiohang.clear();
                                            finish();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for (int i=0;i<MainActivity.arrayListgiohang.size();i++)
                                        {
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("iddonhang",madonhang);
                                                jsonObject.put("idsanpham",MainActivity.arrayListgiohang.get(i).getIdsanpham());
                                                jsonObject.put("soluong",MainActivity.arrayListgiohang.get(i).getSoluong());
                                                jsonObject.put("dongia",MainActivity.arrayListgiohang.get(i).getDongia());
                                                jsonObject.put("thanhtien",MainActivity.arrayListgiohang.get(i).getTongtien());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }

                                        HashMap<String,String>hashMap=new HashMap<String,String>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("sdtnguoidung",sdt);
                            hashMap.put("soluong", String.valueOf(tongsoluong));
                            hashMap.put("thanhtien", String.valueOf(tongtien));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);



            }
        });
    }


    private void LoadtongtienAndtongsoluong(ArrayList<GioHang> gioHangs) {

        for(int i=0;i<gioHangs.size();i++){
            tongtien+=gioHangs.get(i).getTongtien();
            tongsoluong+=gioHangs.get(i).getSoluong();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        textView_tongtien.setText("Tong : "+decimalFormat.format(tongtien)+" D");
        textView_tongsoluong.setText("Tong SL : "+decimalFormat.format(tongsoluong)+" D");




    }

    private void Anhxa() {
        listView =(ListView)findViewById(R.id.listview_giohang);
        button_thongtinkhach=(Button)findViewById(R.id.button_thongtinkhach);
        button_thanhtoan=(Button)findViewById(R.id.button_thanhtoan);
        button_tieptucmuahang=(Button)findViewById(R.id.button_tieptucmuahang);
        textView_tongtien=(TextView)findViewById(R.id.tv_tongtien);
        textView_tongsoluong=(TextView)findViewById(R.id.tv_tongsoluong);


        ArrayList<GioHang> gioHangs= MainActivity.arrayListgiohang;
        giohang_adapter=new Giohang_Adapter(getApplicationContext(),gioHangs);
        listView.setAdapter(giohang_adapter);
    }
}
