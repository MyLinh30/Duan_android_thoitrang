package com.example.banhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.R;
import com.example.banhang.adapter.MixidressAdapter;
import com.example.banhang.model.Sanpham;
import com.example.banhang.ultil.Server;
import com.example.banhang.ultil.check_connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MixiDressActivity extends AppCompatActivity {
    Toolbar toolbarmixidress;
    ListView lvmixidress;
    MixidressAdapter mixidressAdapter;
    ArrayList<Sanpham> mangmixidress;
    int idmixidress =0;
    int page =1;
    View footerview;
    boolean isloading =false;
    boolean limitadata =false;
    myHandler myHandler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixi_dress);
        AnhXa();
        if(check_connection.haveNetworkConnection(getApplicationContext())){
            Getidloaisp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            check_connection.ShortToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại Internet");
            finish();
        }

        
    }

    private void LoadMoreData() {
        lvmixidress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanphammixidress",mangmixidress.get(position));
                startActivity(intent);
            }
        });
        lvmixidress.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount!=0 && isloading==false && limitadata==false){
                    isloading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdanmixidress+ String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id =0;
                String tenmixidress = "";
                int idspmixidress =0;
                int giamixidress = 0;
                String maumixidress = "";
                String chatlieumixidress = "";
                String linkmixidress ="";
                String motamixidress ="";
                int newmaxi =0;
                int incollection =0;
                //reponse tra ve mot mang Jsonarray no luon co mot gia tri la mot cap ngoac vuong
                // khi het dl tra ve mot cap ngoac vuong là 2 phan tu
                // co nghia la het phan tu roi
                if(response !=null && response.length() !=2){
                    lvmixidress.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i =0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id =jsonObject.getInt("idsp");
                            tenmixidress = jsonObject.getString("namesp");
                            idspmixidress =jsonObject.getInt("idtype");
                            giamixidress =jsonObject.getInt("price");
                            maumixidress = jsonObject.getString("color");
                            chatlieumixidress = jsonObject.getString("material");
                            linkmixidress =jsonObject.getString("link");
                            motamixidress = jsonObject.getString("desc");
                            mangmixidress.add(new Sanpham(id,tenmixidress,idspmixidress,giamixidress,maumixidress,chatlieumixidress,linkmixidress,motamixidress));
                            mixidressAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    limitadata= true;
                    lvmixidress.removeFooterView(footerview);
                    check_connection.ShortToast_Short(getApplicationContext(),"Đã load hết dữ liệu");

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idtype",String.valueOf(idmixidress));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbarmixidress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmixidress.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Getidloaisp() {
        idmixidress =getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("Giá trị loại sản phẩm:", idmixidress+"");

    }

    private void AnhXa() {
        toolbarmixidress = (Toolbar) findViewById(R.id.toolbarmixidress);
        lvmixidress = (ListView) findViewById(R.id.listviewmixidress);
        mangmixidress = new ArrayList<>();
        mixidressAdapter = new MixidressAdapter(getApplicationContext(),mangmixidress);
        lvmixidress.setAdapter(mixidressAdapter);
        //gan them layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview= inflater.inflate(R.layout.progressbar,null);
        myHandler = new myHandler();
    }

    public class myHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvmixidress.addFooterView(footerview);
                    break;
                case 1:
                    // page ++ thuc hien function truoc roi moi cong
                    //++page cong bien page xong moi thuc hien function
                    GetData(++page);
                    isloading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
}
