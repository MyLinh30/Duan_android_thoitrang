package com.example.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.R;
import com.example.banhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }
    public  ItemHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder =new ItemHolder(v);
        return itemHolder;
    }
    public void onBindViewHolder(ItemHolder holder ,int postition){
        Sanpham sanpham =arraysanpham.get(postition);
        holder.txttensanpham.setText(sanpham.getName());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getPrice())+ "Đ");
        Picasso.with(context).load(sanpham.getLinkhinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhsanpham);
    }
    public int getItemCount(){
        return arraysanpham.size();
    }
    //@Override
    //public ItemHolder onCreateViewHolder ()


    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham =(ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txtgiasanpham =(TextView) itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham = (TextView) itemView.findViewById(R.id.textviewtensanpham);
        }
    }
}

