package com.reman.Mexpense;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TripExpense extends AppCompatActivity
{
    String id;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    SQLiteDatabase db1;
    SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_expense);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        tv4=(TextView)findViewById(R.id.textView4);
        tv5=(TextView)findViewById(R.id.textView5);
        tv6=(TextView)findViewById(R.id.textView6);
        tv7=(TextView)findViewById(R.id.textView7);
        tv8=(TextView)findViewById(R.id.textView8);
        tv9=(TextView)findViewById(R.id.textView9);
        db1=openOrCreateDatabase("Trips",MODE_APPEND, null);
        db2=openOrCreateDatabase("TripDatabase",MODE_APPEND, null);
        Cursor c1=db1.rawQuery("Select * from tripDetails where TripId="+"'"+id+"'",null);
        while(c1.moveToNext())
        {
            tv1.append(":"+c1.getString(1));
            tv2.append("\t\t\t: "+c1.getString(2));
            tv3.append("\t\t: "+c1.getString(3));
            tv4.append("\t\t: "+c1.getString(4));
            tv5.append("\t\t: "+c1.getString(5));
            tv6.append("\t\t: "+c1.getString(6));
            tv8.append("\t\t " + ":"+c1.getString(7));
            tv9.append("\t\t " + ":"+c1.getString(8));
        }
        Cursor c2=db2.rawQuery("select sum(Amount) from expenses where TripId="+"'"+id+"'",null);
        while(c2.moveToNext())
        {
            tv7.append("\t\t\t: "+c2.getString(0));
        }

    }
}
