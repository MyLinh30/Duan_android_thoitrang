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

public class PartydressAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arraypartydress;

    public PartydressAdapter(Context context, ArrayList<Sanpham> arraypartydress) {
        this.context = context;
        this.arraypartydress = arraypartydress;
    }
    // get so dong trong bang ve
    @Override
    public int getCount() {
        return arraypartydress.size();
    }

    @Override
    public Object getItem(int position) {
        return arraypartydress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class  ViewHolder{
        public TextView txttenpartydress,txtgiapartydress,txtmotapartydress;
        public ImageView imgpartydress;
    }
    // khi run lan dau thì view sẽ là null thì sẽ ánh xạ id
    // khi run lan hai không cần gán id lần nữa tại vi dã có sãn chi can getTag lại
    // lam nhu vay sẽ nhanh hơn
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView ==null)
        {
            viewHolder =new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_mixidress,null);
            viewHolder.txttenpartydress=(TextView) convertView.findViewById(R.id.textviewtenpartydress);
            viewHolder.txtgiapartydress=(TextView) convertView.findViewById(R.id.textviewgiapartydress);
            viewHolder.txtmotapartydress=(TextView) convertView.findViewById(R.id.textviewmotapartydress);
            viewHolder.imgpartydress = (ImageView) convertView.findViewById(R.id.imageviewpartydress);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenpartydress.setText(sanpham.getName());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiapartydress.setText("Giá: "+decimalFormat.format(sanpham.getPrice())+ " Đ");
        viewHolder.txtmotapartydress.setText(sanpham.getDesc());
        viewHolder.txtmotapartydress.setMaxLines(2);
        viewHolder.txtmotapartydress.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotapartydress.setText(sanpham.getDesc());
        Picasso.with(context).load(sanpham.getLinkhinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgpartydress);
        return convertView;
    }
}
