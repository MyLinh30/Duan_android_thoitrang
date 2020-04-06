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
    //lấy những giá trị trong mảng
    public int getCount() {
        return arraymixidress.size();
    }

    @Override
    // lấy những thuộc tính trong mảng
    public Object getItem(int position) {
        return arraymixidress.get(position);
    }

    @Override
    //
    public long getItemId(int position) {
        return position;
    }
    public class  ViewHolder{
        public TextView txttenmixidress, txtgiamixidress, txtmotamixidress;
        public ImageView imgmixidress;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView ==null){
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_mixidress,null);
            viewHolder.txttenmixidress =(TextView) convertView.findViewById(R.id.textviewtensanpham);
            viewHolder.txtgiamixidress =(TextView) convertView.findViewById(R.id.textviewgiasanpham);
            viewHolder.txtmotamixidress =(TextView) convertView.findViewById(R.id.textviewmotamixidress);
            viewHolder.imgmixidress =(ImageView) convertView.findViewById(R.id.imageviewsanpham);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenmixidress.setText(sanpham.getName());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiamixidress.setText("Giá: "+decimalFormat.format(sanpham.getPrice())+ "Đ");
        viewHolder.txtmotamixidress.setMaxLines(2);
        viewHolder.txtmotamixidress.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotamixidress.setText(sanpham.getDesc());
        Picasso.with(context).load(sanpham.getLinkhinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgmixidress);
        return convertView;
    }
}
