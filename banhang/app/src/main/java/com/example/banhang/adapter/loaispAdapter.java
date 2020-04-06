package com.example.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.R;
import com.example.banhang.model.loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class loaispAdapter extends BaseAdapter {
   ArrayList<loaisp> arrayListloaisp;

    public loaispAdapter(ArrayList<loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    Context context;
    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
   public class ViewHolder{
      TextView textViewloaisp;
      ImageView imageViewloaisp;
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView ==null){
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.textViewloaisp= (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imageViewloaisp=(ImageView) convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();

        }
        loaisp loaisp = (loaisp) getItem(position);
        viewHolder.textViewloaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageViewloaisp);
        return convertView;
    }
}
