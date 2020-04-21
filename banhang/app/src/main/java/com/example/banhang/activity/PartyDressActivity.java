package com.example.banhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.banhang.R;
import com.example.banhang.adapter.MixidressAdapter;
import com.example.banhang.adapter.PartydressAdapter;
import com.example.banhang.model.Sanpham;

import java.util.ArrayList;

public class PartyDressActivity extends AppCompatActivity {
    Toolbar toolbarpartydress;
    ListView lvpartydress;
    PartydressAdapter partydressAdapter;
    ArrayList<Sanpham> mangpartydress;
    int idpartydress =0;
    int page =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_dress);
        AnhXa();
        Getidloaisp();

    }
    private void Getidloaisp() {
        idpartydress =getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("Giá trị loại sản phẩm:", idpartydress+"");

    }
    private void AnhXa() {
        toolbarpartydress = (Toolbar) findViewById(R.id.toolbarpartydress);
        lvpartydress = (ListView) findViewById(R.id.listviewpartydress);
        mangpartydress = new ArrayList<>();
        partydressAdapter = new PartydressAdapter(getApplicationContext(),mangpartydress);
        lvpartydress.setAdapter(partydressAdapter);
    }
}
