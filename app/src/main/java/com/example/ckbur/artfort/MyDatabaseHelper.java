package com.example.ckbur.artfort;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Artfort.db";
    private static final String TABLE_NAME="order_details";

    private static final String ID="_id";
    private static final String PNAME="PName";
    private static final String PRICE="Price";
    private static final String QUANTITY="Quantity";
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PNAME+" VARCHAR(255),"+PRICE+" INTEGER,"+QUANTITY+" INTEGER)";


    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;


    private static final String CLEAR="DELETE FROM "+TABLE_NAME;
    private long rowId;

    private final static int VERSION_NUMBER=1;

    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);

        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        try{

           // Toast.makeText(context, "onCreate is created", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Exception:"+e, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String pname,String price,String quantity){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(PNAME,pname);
        contentValues.put(PRICE,price);
        contentValues.put(QUANTITY,quantity);
        rowId =sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;

    }

    public void CleanCart(){

    SQLiteDatabase db=this.getReadableDatabase();
    db.execSQL(CLEAR);
    rowId=1;


    }


    public Cursor displayalldata(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL,null);

        return cursor;

    }
}
