package com.example.ckbur.artfort;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Cart extends AppCompatActivity implements View.OnClickListener {


    private ListView list_cart;

    MyDatabaseHelper myDatabaseHelper;
    List<ordermodel> ordermodels;
    private order_Adapter order_adapter;

    private Button place_orderrr;

    private TextView total;

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText address;
    EditText phone;
    int multiplication,total_price=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

         address=(EditText)findViewById(R.id.address);
         phone=(EditText)findViewById(R.id.phone);

        total=(TextView)findViewById(R.id.total);

        place_orderrr=(Button)findViewById(R.id.button_place_order);


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Order");



        place_orderrr.setOnClickListener(this);

        ordermodels=new ArrayList<>();


        list_cart=(ListView)findViewById(R.id.list_cart);

        myDatabaseHelper=new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
        Cursor cursor=myDatabaseHelper.displayalldata();

        if (cursor.getCount()==0){

            Toast.makeText(this, "there is no data", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()){

            multiplication =0;

            ordermodel item=new ordermodel();

            item.setPname(cursor.getString(1));
            item.setPrice(cursor.getString(2));
            item.setQuantity(cursor.getString(3));
            ordermodels.add(item);
            String a=cursor.getString(2);
            String b=cursor.getString(3);

           int aa=Integer.parseInt(a);
           int bb=Integer.parseInt(b);

           multiplication =aa*bb;

           total_price=total_price+ multiplication;



        }

       order_adapter=new order_Adapter(Cart.this,R.layout.cart_layout,ordermodels);
        list_cart.setAdapter(order_adapter);

        String Total=String.valueOf(total_price);
        total.setText(Total);


    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.button_place_order){

           showAlterDialog();

           //list_cart.setAdapter(null);
        }

    }

    private void showAlterDialog() {


        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.dialog_signin, null);

        final EditText input1 = (EditText) textEntryView.findViewById(R.id.address);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.phone);


        //input1.setText("DefaultValue", TextView.BufferType.EDITABLE);
        //input2.setText("DefaultValue", TextView.BufferType.EDITABLE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.ic_shopping_cart_black_24dp).setTitle("One More Step!").setView(textEntryView).setPositiveButton("ORDER",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                        place_order request=new place_order(

                                input1.getText().toString(),input2.getText().toString(),total.getText().toString(),ordermodels


                        );
                        reference.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                        new MyDatabaseHelper(getBaseContext()).CleanCart();

                        Toast.makeText(Cart.this, "Thank You, Order placed", Toast.LENGTH_SHORT).show();
                        finish();
                        //Toast.makeText(Cart.this, "TextEntry 1 Entered"+input1.getText().toString(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Cart.this, "TextEntry 2 Entered"+input2.getText().toString(), Toast.LENGTH_SHORT).show();
                        /* User clicked OK so do some stuff */
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        /*
                         * User clicked cancel so do some stuff
                         */
                    }
                });
        alert.show();


    }
}
