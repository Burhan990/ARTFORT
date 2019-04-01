package com.example.ckbur.artfort;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class   HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


     TextView textView;

    HomeViewHolder home;

    DatabaseReference  counterRef;
    FirebaseDatabase firebaseDatabase;
    private List<HomeViewHolder>homeViewHolders;



   // private FirebaseAuth.AuthStateListener mAuthStateListener;

   // private Uri uri;

    private MessageAdapter mMessageAdapter;
    private ListView mMessageListView;

    private FirebaseStorage mFireBaseStorage;
    private StorageReference mChatPhotosStorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,Cart.class);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // Toolbar tool = (Toolbar) findViewById(R.id.toolBar);
       // toolbar.setTitle("Online Tracking System");
       // setSupportActionBar(tool);




        firebaseDatabase=FirebaseDatabase.getInstance();


       // mFireBaseAuth=FirebaseAuth.getInstance();
        mFireBaseStorage=FirebaseStorage.getInstance();
        mChatPhotosStorageReference=mFireBaseStorage.getReference().child("image");

        mMessageListView = (ListView) findViewById(R.id.messageListView);

        //category item showing from FireBase
        homeViewHolders = new ArrayList<>();

        counterRef = firebaseDatabase.getInstance().getReference("category");

        counterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    home=snapshot.getValue(HomeViewHolder.class);
                   homeViewHolders.add(home);

                    mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            Intent i = new Intent(HomeActivity.this, Product_Category.class);
                            i.putExtra("Id",homeViewHolders.get(position).getId());
                            i.putExtra("name",homeViewHolders.get(position).getName());
                            startActivity(i);
                        }
                    });


                }

                mMessageAdapter =new MessageAdapter(HomeActivity.this,R.layout.item_category,homeViewHolders);
                mMessageListView.setAdapter(mMessageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                Toast.makeText(HomeActivity.this, "error while reading data", Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {

            Intent i=new Intent(HomeActivity.this,Cart.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_log_out) {

            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // ...

                            Intent i=new Intent(HomeActivity.this,LogIn.class);
                            startActivity(i);
                            Toast.makeText(HomeActivity.this, "Sign Out SuccessFull!!", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    });



        } else if (id == R.id.nav_share) {



        } else if (id == R.id.nav_send) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/burhan.uddin.96155");

            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onStop() {
        super.onStop();

    }
}
