package com.example.ckbur.artfort;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<HomeViewHolder> {


    private Activity context;
    private int resource;
    private List<HomeViewHolder>list;
    public MessageAdapter(Context context, int resource, List<HomeViewHolder> objects) {
        super(context, resource, objects);

        this.context= (Activity) context;
        this.resource=resource;
        list=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();

        View v=inflater.inflate(resource,null);


        ImageView photoImageView = (ImageView)v.findViewById(R.id.category_image);
        TextView CategoryTextView = (TextView) v.findViewById(R.id.category_name);
        // idtext = (TextView)v.findViewById(R.id.id);

        HomeViewHolder message = getItem(position);

     //   boolean isPhoto = message.getUri() != null;
        //if (isPhoto) {

           // messageTextView.setVisibility(View.GONE);
            //photoImageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(list.get(position).getUri())
                    .into(photoImageView);

           // messageTextView.setVisibility(View.VISIBLE);
           // photoImageView.setVisibility(View.GONE);
            CategoryTextView.setText(list.get(position).getName());
            //long a=list.get(position).getId();
            //String b=String.valueOf(a);
            //idtext.setText(b);



        //}
        //authorTextView.setText(message.getName());



        return v;
    }
}
