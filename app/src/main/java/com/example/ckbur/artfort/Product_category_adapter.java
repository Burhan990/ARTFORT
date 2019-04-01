package com.example.ckbur.artfort;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Product_category_adapter extends ArrayAdapter<Product_category_model> {

    private Activity context;
    private int resource;
    private List<Product_category_model> list;
    public Product_category_adapter(Context context, int resource, List<Product_category_model> objects) {
        super(context, resource, objects);

        this.context= (Activity) context;
        this.resource=resource;
        list=objects;

    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {



        LayoutInflater inflater=context.getLayoutInflater();

        View v=inflater.inflate(resource,null);


        ImageView category_product_ImageView = (ImageView)v.findViewById(R.id.category_product_image);
        TextView category_product_name_TextView = (TextView) v.findViewById(R.id.category_product_name);
        TextView category_product_quality_TextView=(TextView)v.findViewById(R.id.category_product_quality);
        TextView category_product_price_TextView=(TextView)v.findViewById(R.id.category_product_price);
        TextView category_product_stock_TextView=(TextView)v.findViewById(R.id.category_product_stock);
        TextView category_product_discount_TextView=(TextView)v.findViewById(R.id.category_product_discount);

        Product_category_model pos = getItem(position);

        Glide.with(context)
                .load(list.get(position).getUri())
                .into(category_product_ImageView);

        category_product_name_TextView.setText(list.get(position).getPname());
        category_product_quality_TextView.setText(list.get(position).getQuality());
        category_product_price_TextView.setText(list.get(position).getPrice());
        category_product_stock_TextView.setText(list.get(position).getStock());
        category_product_discount_TextView.setText(list.get(position).getDiscount());


        return v;
    }
}
