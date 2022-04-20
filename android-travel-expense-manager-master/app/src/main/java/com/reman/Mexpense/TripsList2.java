package com.reman.Mexpense;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TripsList2 extends ListActivity
{
    ArrayList list1;
    ArrayList list2;
    ArrayList list3;
    View row;
    ImageButton bt1;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_trips_list);
        String q=null;
        Cursor c=null;
        String trip=null;

         db=openOrCreateDatabase("Trips",MODE_APPEND,null);
        q = "Select * from tripDetails";
        try {
            c=db.rawQuery(q,null);
            String title;
            String s;
            list1=new ArrayList();
            list2=new ArrayList();
            list3=new ArrayList();
            while(c.moveToNext())
            {
                title=c.getString(1);
                s=c.getString(4);
                trip=c.getString(0);
                list1.add(title);
                list2.add(s);
                list3.add(trip);
            }
            db.close();
            //bt1=(ImageButton)row.findViewById(R.id.imageButton);
            //registerForContextMenu(bt1);
            ArrayAdapter adapter=null;
            adapter=new MyAdapter(this,android.R.layout.simple_list_item_1,list1);
            setListAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(this,"No Scheduled Trips ",Toast.LENGTH_SHORT).show();
        }






    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Intent i=new Intent(this, DayExpense.class);
        i.putExtra("id",String.valueOf(list3.get(position)));
        startActivity(i);

    }
    class MyAdapter extends ArrayAdapter
    {

        public MyAdapter(Context context, int resource, List objects)
        {
            super(context, resource, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater li=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=li.inflate(R.layout.activity_trips_list,parent,false);
            ImageView v1=(ImageView)row.findViewById(R.id.imageView);
            TextView tv1=(TextView)row.findViewById(R.id.textView1);
            TextView tv2=(TextView)row.findViewById(R.id.textView2);

            tv1.setText(String.valueOf(list1.get(position)));
            tv2.setText(String.valueOf(list2.get(position)));
            if(position%2==0)
            {
                v1.setImageResource(R.drawable.hi);
            }
            else if((position%3==0)&&((position%2)!=0))
            {
                v1.setImageResource(R.drawable.hii);
            }
            else
            {
                v1.setImageResource(R.drawable.hiii);
            }
            return row;
            // return super.getView(position, convertView, parent);
        }

    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
//    {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.search_menu,menu);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {




                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override

            public boolean onClose() {



                return false;
            }
        });

        return super .onCreateOptionsMenu(menu);
    }


}
