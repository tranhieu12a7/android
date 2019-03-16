package com.example.appbanhang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.AnimationUtilsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.BroadcastReceiver;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.Adapter_menucha;
import com.example.appbanhang.adapter.Sanpham_Adapter;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.model.MenuCha;
import com.example.appbanhang.model.SanPham;
import com.example.appbanhang.server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout_trangchu;
    RecyclerView recyclerView_sanphamhot;
    ViewFlipper viewFlipper_trangchu;
    ListView listView_menu;
    NavigationView navigationView_menu;
    Toolbar toolbar_trangchu;
    ArrayList<MenuCha> arrayList_menucha;
    Adapter_menucha adapter_menucha;
    ArrayList<SanPham> arrayLis_sanpham;
    Sanpham_Adapter sanpham_adapter;
   public static ArrayList<GioHang> arrayListgiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Actionbar();



        boolean kiemtraconnect=BroadcastReceiver.isConnected(getApplicationContext());
        if(kiemtraconnect==true) {
            ActionViewFipper();
            GetDataMenucha();
            GetSanPhamHM();
            MyOnClick_ListviewMenu();
        }
        else {
            Toast.makeText(getApplicationContext(),"vui long kiem tra ket noi internet",Toast.LENGTH_LONG).show();
        }



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

    private void MyOnClick_ListviewMenu() {
        listView_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch ((int) id){
                    case 1:
                        Intent intent_trangchu = new Intent(MainActivity.this,MainActivity.class);
                        drawerLayout_trangchu.closeDrawer(GravityCompat.START);
                        finish();
                        startActivity(intent_trangchu);
                        break;
                    case 2:
                        Intent intent_laptop_menucon=new Intent(MainActivity.this,DanhMucMenuCon.class);
                        intent_laptop_menucon.putExtra("idmenucha",(int)id);
                        intent_laptop_menucon.putExtra("tentoolbar","Lap top");
                        startActivity(intent_laptop_menucon);
                        drawerLayout_trangchu.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent_dienthoai_menucon=new Intent(MainActivity.this,DanhMucMenuCon.class);
                        intent_dienthoai_menucon.putExtra("idmenucha",(int)id);
                        intent_dienthoai_menucon.putExtra("tentoolbar","Dien thoai");
                        startActivity(intent_dienthoai_menucon);
                        drawerLayout_trangchu.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetSanPhamHM() {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.DuongdanIP_sanphamKM, new Response.Listener<JSONArray>() {
            @Override
            //trường hợp có giá trị trả về thì làm hành động dưới
            public void onResponse(JSONArray response)
            {
                //kiểm tra xem có giá trị từ csdl trả về không thông qua respone
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject;
                        try {
                            //tạo một json_object để hứng giá trị data và add vào trong mảng json_array
                            jsonObject=response.getJSONObject(i);
                            int id=jsonObject.getInt("id_sp");
                            String ten=jsonObject.getString("ten_sp");
                            String hinh=jsonObject.getString("hinh_sp");
                            int khuyenmai=jsonObject.getInt("sp_khuyenmai");
                            Integer giabandau=jsonObject.getInt("giabandau_sp");
                            Integer giahientai= jsonObject.getInt("giahientai_sp");
                            String gioithieu=jsonObject.getString("gioithieu_sp");
                            int id_menucon=jsonObject.getInt("id_menucon");
                            arrayLis_sanpham.add(new SanPham(id,ten,hinh,khuyenmai,giabandau,giahientai,gioithieu,id_menucon));
                            sanpham_adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            //trường hợp không có giá trị trả về
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error load san pham hot",Toast.LENGTH_SHORT).show();
            }
        });
        //câu lệnh thực hiện lại request
        requestQueue.add(jsonArrayRequest);
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
                            arrayList_menucha.add(new MenuCha(id_tam,hinh_tam,ten_tam));
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

    public void ActionViewFipper()
    {
        ArrayList<String> arrayList_quangcao=new ArrayList<String>();
        arrayList_quangcao.add("http://thoinet.vn/wp-content/uploads/2017/12/ca-nhan.jpg");
        arrayList_quangcao.add("http://samediavietnam.com/wp-content/uploads/2018/07/huawei-to-chuc-le-ra-mat-san-pham-moi-01-360x260.jpg");
        arrayList_quangcao.add("https://image.giaoducthoidai.vn/uploaded/dienns/2016_12_11/344259_twbl.jpg?width=500");
        for(int i =0;i<arrayList_quangcao.size();i++)
        {
            //khởi tạo imaview để lưu ảnh
            ImageView imageView=new ImageView(getApplicationContext());
            //đọc file ảnh từ internet về và gán vào imaview thông qua "into"
            Picasso.with(getApplicationContext()).load(arrayList_quangcao.get(i)).into(imageView);
            //căn chỉnh tự động kích thước imaview để cho vừa với viewfipper_trangchu
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //add vào trong imageview
            viewFlipper_trangchu.addView(imageView);
        }
        viewFlipper_trangchu.setFlipInterval(5000);//set thời gian lập ảnh
        viewFlipper_trangchu.setAutoStart(true);//tự động lập lại

        //set silder của ảnh từ trai chạy vào viewfipper
        Animation animation_slider_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slider_in_right);
        //set sider của ảnh từ phải đi ra viewfipper
        Animation animation_slider_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slider_out_right);
        viewFlipper_trangchu.setInAnimation(animation_slider_in);
        viewFlipper_trangchu.setOutAnimation(animation_slider_out);

    }
    public void Actionbar(){
              setSupportActionBar(toolbar_trangchu);//hàm hỗ trợ chuyển toolbar thành actionbar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//get để nút menu hiện ra
        toolbar_trangchu.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);//thay đổi icon nut menu
        toolbar_trangchu.setNavigationOnClickListener(new View.OnClickListener() {
            //set sự kiện nut menu
            @Override
            public void onClick(View v) {
                drawerLayout_trangchu.openDrawer(GravityCompat.START);//phương thức mở nút menu khi nhấn vào
            }
        });


    }
    public void Anhxa()
    {
        drawerLayout_trangchu=(DrawerLayout) findViewById(R.id.dv_layout_trangchu);
        viewFlipper_trangchu=(ViewFlipper) findViewById(R.id.vf_trangchu);
        listView_menu=(ListView)findViewById(R.id.lv_menu);
        navigationView_menu=(NavigationView)findViewById(R.id.navi_menu);
        toolbar_trangchu=(Toolbar)findViewById(R.id.tb_trangchu);
        recyclerView_sanphamhot=(RecyclerView)findViewById(R.id.recy_sanphamhot);

        arrayList_menucha=new ArrayList<MenuCha>();
        adapter_menucha=new Adapter_menucha(arrayList_menucha,getApplicationContext());
        listView_menu.setAdapter(adapter_menucha);

        arrayLis_sanpham=new ArrayList<SanPham>();
        sanpham_adapter=new Sanpham_Adapter(getApplicationContext(),arrayLis_sanpham);
        recyclerView_sanphamhot.setHasFixedSize(true);// set các các size sản phẩm cho chúng cùng độ cao và độ dài để cân đối hơn
        //set layout hiễn thị theo dạng nào ? và sét số cột, số hàng khi là linearLayoutManager thì
        recyclerView_sanphamhot.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //set cái adapter vào trong recyclerview
        recyclerView_sanphamhot.setAdapter(sanpham_adapter);

        if(arrayListgiohang!=null)
        {

        }
        else {
            arrayListgiohang=new ArrayList<>();
        }


    }

}
