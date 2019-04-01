package com.example.ckbur.artfort;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Product_Category extends AppCompatActivity {
    private TextView textView;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private List<Product_category_model> product_category_models;

    private Product_category_adapter category_adapter;
    private ListView productlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__category);






        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("product");


        productlistview=(ListView) findViewById(R.id.product_category);




        Intent i=getIntent();
        long a=i.getLongExtra("Id",0);
        String categoryId=String.valueOf(a);
        String category_name=i.getStringExtra("name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(category_name);
        setSupportActionBar(toolbar);

        product_category_models = new ArrayList<>();

        Query query=databaseReference.orderByChild("productid").equalTo(categoryId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Product_category_model product=snapshot.getValue(Product_category_model.class);

                    product_category_models.add(product);

                    productlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i=new Intent(Product_Category.this,Product_Details_and_cart.class);
                          i.putExtra("productbaseId",product_category_models.get(position).getProductbaseid());
                           startActivity(i);
                        }
                    });



                }

                category_adapter =new Product_category_adapter(Product_Category.this,R.layout.item_product,product_category_models);
                productlistview.setAdapter(category_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Product_Category.this, "Error While data Reading", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
