package com.example.banhang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhang.R;
import com.example.banhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MixidressAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraymixidress;

    public MixidressAdapter(Context context, ArrayList<Sanpham> arraymixidress) {
        this.context = context;
        this.arraymixidress = arraymixidress;
    }

    @Override
    public int getCount() {
        return arraymixidress.size();
    }

    @Override
    public Object getItem(int position) {
        return arraymixidress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class  ViewHolder{
        public TextView txttenmixidress,txtgiamixidress,txtmotamixi;
        public ImageView imgmixidress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView ==null)
        {
            viewHolder =new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_mixidress,null);
            viewHolder.txttenmixidress=(TextView) convertView.findViewById(R.id.textviewtenmixidress);
            viewHolder.txtgiamixidress=(TextView) convertView.findViewById(R.id.textviewgiamixidress);
            viewHolder.txtmotamixi=(TextView) convertView.findViewById(R.id.textviewmotamixidress);
            viewHolder.imgmixidress = (ImageView) convertView.findViewById(R.id.imageviewmixidress);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenmixidress.setText(sanpham.getName());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiamixidress.setText("Giá: "+decimalFormat.format(sanpham.getPrice())+ " Đ");
        viewHolder.txtmotamixi.setText(sanpham.getDesc());
        viewHolder.txtmotamixi.setMaxLines(2);
        viewHolder.txtmotamixi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamixi.setText(sanpham.getDesc());
        Picasso.with(context).load(sanpham.getLinkhinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgmixidress);
        return convertView;
    }
}
