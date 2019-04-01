package com.example.ckbur.artfort;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

public class order_Adapter extends ArrayAdapter<ordermodel> {

    private Activity context;
    private int resource;
    private List<ordermodel>list;

    public order_Adapter(Context context, int resource,List<ordermodel> objects) {
        super(context, resource, objects);

        this.context= (Activity) context;
        this.resource=resource;
        this.list=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(resource,null);

        ordermodel pos=getItem(position);

        TextView textproductname=(TextView)v.findViewById(R.id.cartitemname);
        TextView textproductprice=(TextView)v.findViewById(R.id.cartitemprice);
        TextDrawable textDrawable=TextDrawable.builder().buildRound(""+list.get(position).getQuantity(),Color.RED);

        ImageView textquantity=(ImageView)v.findViewById(R.id.cartitemcount);

        textproductname.setText(list.get(position).getPname());
        textproductprice.setText(list.get(position).getPrice());
        textquantity.setImageDrawable(textDrawable);

        return v;
    }
}
