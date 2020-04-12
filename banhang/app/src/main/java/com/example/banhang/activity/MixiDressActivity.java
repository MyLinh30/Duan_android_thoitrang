package com.example.banhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MixiDressActivity extends AppCompatActivity {

    Toolbar toolbarmixidress;
    ListView lvmd;
    MixidressAdapter mixidressAdapter;
    ArrayList<Sanpham> mangmd;
    int idmixidress;
    int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixi_dress);
        AnhXa();
        if(check_connection.haveNetworkConnection(getApplicationContext())){
            Getidloaisp();
            ActionToolBar();
            GetData(page);
        }else{
            check_connection.ShortToast_Short(getApplicationContext()
                    ,"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void GetData(int Page) {
        RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
        String url ="http://192.168.1.7:8080/Server/getsanpham.php?page="+Page;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id =0;
                String namemaxi ="";
                int idtypeproduct =0;
                int pricemaxi=0;
                String colormaxi ="";
                String materialmaxi="";
                String linkmaxi ="";
                String description="";
                //int newpro=0;
                //int incollection=0;
                if (response !=null){
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject =jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("idsp");
                            namemaxi =jsonObject.getString("namesp");
                            idtypeproduct =jsonObject.getInt("idtype");
                            pricemaxi = jsonObject.getInt("price");
                            colormaxi =jsonObject.getString("color");
                            materialmaxi=jsonObject.getString("material");
                            linkmaxi =jsonObject.getString("link");
                            description=jsonObject.getString("desc");
                            //newpro =jsonObject.getInt("newmaxi");
                            //incollection =jsonObject.getInt("incollection");
                            mangmd.add(new Sanpham(id,namemaxi,idtypeproduct,pricemaxi,colormaxi,materialmaxi,linkmaxi,description));
                            mixidressAdapter.notifyDataSetChanged();
                        }
                    }catch (Exception e){
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
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idtype",String.valueOf(idmixidress));
                return param;
            }
        }; requestQueue.add(stringRequest);

    }

//    private void GetData(int Page) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        StringRequest stringRequest =new StringRequest(Request.Method.POST)
//    }


    private void ActionToolBar() {
        setSupportActionBar(toolbarmixidress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmixidress.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbarmixidress) {
    }

    private void Getidloaisp() {
        idmixidress = getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatrisanpham",idmixidress+"");
    }
    private void AnhXa() {
        toolbarmixidress =(Toolbar) findViewById(R.id.toolbarmixidress);
        lvmd =(ListView) findViewById(R.id.listviewmixidress);
        mangmd =new ArrayList<>();
        mixidressAdapter = new MixidressAdapter(getApplicationContext(),mangmd);
        lvmd.setAdapter(mixidressAdapter);

    }
}
