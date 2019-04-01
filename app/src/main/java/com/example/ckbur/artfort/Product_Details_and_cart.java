package com.example.ckbur.artfort;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.squareup.picasso.Picasso;

public class Product_Details_and_cart extends AppCompatActivity {

    //private EditText nameEditText,ageEditText,genderEditText;
    //private Button addButton,displayAllDataButtonId;

    MyDatabaseHelper myDatabaseHelper;




    TextView product_Name,product_Price,product_Description;
    ImageView product_Image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton BtnCart;
    ElegantNumberButton numberButton;

    Product_category_model details;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details_and_cart);



        myDatabaseHelper=new MyDatabaseHelper(this);

        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();


        Intent i=getIntent();
        long a=i.getLongExtra("productbaseId",0);
        String productbaseid=String.valueOf(a);



        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("product");


        numberButton=(ElegantNumberButton)findViewById(R.id.number_button1);
        product_Price=(TextView)findViewById(R.id.product_price1);
        product_Name=(TextView)findViewById(R.id.product_name1);


        BtnCart=(FloatingActionButton)findViewById(R.id.btnCart1);

        BtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname=product_Name.getText().toString();
                String price=product_Price.getText().toString();
                String quantity=String.valueOf(numberButton.getNumber());

                long rowId=  myDatabaseHelper.insertData(pname,price,quantity);

                if (rowId==-1){
                    Toast.makeText(Product_Details_and_cart.this, "Product adding fail", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(Product_Details_and_cart.this, "Product successfully added To the Cart", Toast.LENGTH_SHORT).show();



                }


            }
        });


        product_Description=(TextView)findViewById(R.id.product_description1);

        product_Image=(ImageView)findViewById(R.id.product_image1);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        Query query=databaseReference.orderByChild("productmatchid").equalTo(productbaseid);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 details=dataSnapshot.getValue(Product_category_model.class);


                //Glide.with(getBaseContext()).load(details.getUri()).into(product_Image);
                Picasso.get().load(details.getUri()).into(product_Image);
                product_Name.setText(details.getPname());
                product_Price.setText(details.getPrice());
                product_Description.setText(details.getDescription());

                collapsingToolbarLayout.setTitle(details.getPname());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
