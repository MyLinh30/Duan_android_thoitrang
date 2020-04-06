package com.example.banhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.R;
import com.example.banhang.adapter.MixidressAdapter;
import com.example.banhang.model.Sanpham;

import java.util.ArrayList;

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
        Getidloaisp();
        ActionToolBar();
       // GetData(page);
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
