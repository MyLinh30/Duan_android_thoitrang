package com.example.banhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.R;
import com.example.banhang.adapter.SanphamAdapter;
import com.example.banhang.adapter.loaispAdapter;
import com.example.banhang.model.Sanpham;
import com.example.banhang.model.loaisp;
import com.example.banhang.ultil.Server;
import com.example.banhang.ultil.check_connection;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<loaisp> mangloaisp;
    loaispAdapter loaispAdapter;
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    int id;
    String tenloaisp="";
    String hinhanhsp="";
    //private Object SanphamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        //kiem tra ket noi
        if(check_connection.haveNetworkConnection(getApplicationContext())){
            Action();
            ActionViewFlipper();
            GetDLloaisp();
            GetDLsanphammoinhat();
            CatchOnItemListView();
        }else{
            check_connection.ShortToast_Short(getApplicationContext()
                    ,"Bạn hãy kiểm tra lại kết nối");
            finish();
        }


    }

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,MixiDressActivity.class);
                            //pushextra truyền man hinh sang mot man hinh khac
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,PartyDressActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,MiniDressActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,RompersActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            //chuyen man hinh
                            Intent intent =new Intent(MainActivity.this,ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

            }
        });
    }

    private void GetDLsanphammoinhat(){
        RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              if(response !=null){
                  int ID =0;
                  String tensanpham ="";
                  int Idtype =0;
                  Integer giasanpham = 0;
                  String mausanpham ="";
                  String chatlieusanpham="";
                  String hinhanhsanpham="";
                  String motasanpham="";
                  for (int i=0;i<response.length();i++){
                      try {
                          JSONObject jsonObject =response.getJSONObject(i);
                          ID =jsonObject.getInt("idpro");
                          tensanpham =jsonObject.getString("name");
                          Idtype =jsonObject.getInt("idtype");
                          giasanpham=jsonObject.getInt( "price");
                          mausanpham =jsonObject.getString("color");
                          chatlieusanpham=jsonObject.getString("material");
                          hinhanhsanpham =jsonObject.getString("link");
                          motasanpham =jsonObject.getString("desc");
                          mangsanpham.add(new Sanpham(ID,tensanpham,Idtype,giasanpham,mausanpham,chatlieusanpham,hinhanhsanpham,motasanpham));
                          sanphamAdapter.notifyDataSetChanged();
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }

                  }
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDLloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new
                JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!= null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tenloaisp=jsonObject.getString("tenloaisp");
                            hinhanhsp=jsonObject.getString("hinhanhsp");
                            mangloaisp.add(new loaisp(id,tenloaisp,hinhanhsp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(5,new loaisp(0,"Liên hệ","https://cdn3.vectorstock.com/i/1000x1000/59/82/book-student-icon-blue-vector-19895982.jpg"));
                    mangloaisp.add(6,new loaisp(0,"Thông tin","https://icons.iconarchive.com/icons/custom-icon-design/flatastic-5/512/Female-user-info-icon.png"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                check_connection.ShortToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper(){
        ArrayList<String> mang =new ArrayList<>();
        mang.add("https://topchuan.com/wp-content/uploads/2019/09/Kissy-Shop-2.jpg");
        mang.add("https://www.hetgia.com/wp-content/uploads/2018/06/Kissy-Shop.jpg");
        mang.add("https://luxury-inside.vn/data/uploads/2019/07/h-and-amp-m-store.jpg");
        mang.add("https://www2.hm.com/content/dam/hm-magazine-2019/editors-picks/19_11_Acc_Rome_6.jpg");
        for(int i=0;i<mang.size();i++){
            ImageView imageView =new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mang.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation anim_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation anim_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(anim_in);
        viewFlipper.setOutAnimation(anim_out);
    }
    private void Action(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void AnhXa(){
        toolbar =(Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper =(ViewFlipper) findViewById(R.id.viewflippermanhinhchinh);
        recyclerView =(RecyclerView) findViewById(R.id.recyclerviewmanhinhchinh);
        navigationView =(NavigationView) findViewById(R.id.navigationmanhinhchinh);
        listView= (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerlayoutmanhinhchinh);
        mangloaisp =new ArrayList<>();
        mangloaisp.add(0,new loaisp(0,"Trang chính","https://png.pngtree.com/png-vector/20190121/ourlarge/pngtree-vector-house-icon-png-image_332900.jpg"));
        loaispAdapter =new loaispAdapter(mangloaisp,getApplicationContext());
        listView.setAdapter(loaispAdapter);
        mangsanpham =new ArrayList<>();
        sanphamAdapter =new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamAdapter);


    }
}
