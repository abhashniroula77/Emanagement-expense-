package com.reman.Mexpense;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.reman.Mexpense.R;

import java.util.ArrayList;
import java.util.List;

public class TripsList extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ArrayList list1;
    ArrayList list2;
    ArrayList list3;
    View row;
    ImageButton bt1;
    String st;
    TextView tv1;
    ListView lv;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        String q=null;
        Cursor c=null;
        String trip=null;

        lv = (ListView) findViewById(R.id.lv_list);
        Intent in = getIntent();
         st = in.getStringExtra("Add");

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
            lv.setOnItemClickListener(this);
            lv.setAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(this,"No Scheduled Trips ",Toast.LENGTH_SHORT).show();
        }






    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        try{  if(st.equals("AddExpense")){
            TextView tv1=(TextView)row.findViewById(R.id.textView1);
            Intent i=new Intent(this,Expenses.class);
            i.putExtra("id",String.valueOf(list3.get(position)));
            i.putExtra("tit",String.valueOf(list1.get(position)));
            startActivity(i);
        }
        else {
            Intent i = new Intent(this, Viewdetails.class);
            i.putExtra("id", String.valueOf(list3.get(position)));
            startActivity(i);
        }
        }catch (Exception e){
            Intent i = new Intent(this, Viewdetails.class);
            i.putExtra("id", String.valueOf(list3.get(position)));
            startActivity(i);
        }
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
   /* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }*/

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
                String q = "Select * from tripDetails where Title like ?";
                db=openOrCreateDatabase("Trips",MODE_APPEND,null);

                try {
                    Cursor c=db.rawQuery(q,new String[]{"%"+newText+"%"});
                    String title;
                    String s;
                    list1=new ArrayList();
                    list2=new ArrayList();
                    list3=new ArrayList();
                    while(c.moveToNext())
                    {
                        title=c.getString(1);
                        s=c.getString(4);
                        String trip=c.getString(0);
                        list1.add(title);
                        list2.add(s);
                        list3.add(trip);
                    }
                    db.close();
                    //bt1=(ImageButton)row.findViewById(R.id.imageButton);
                    //registerForContextMenu(bt1);
                    ArrayAdapter adapter=null;
                    adapter=new MyAdapter(getBaseContext(),android.R.layout.simple_list_item_1,list1);
                    lv.setOnItemClickListener(TripsList.this);
                    lv.setAdapter(adapter);
                }
                catch(Exception e)
                {
                    Toast.makeText(getBaseContext(),"No Scheduled Trips "+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override

            public boolean onClose() {

                return false;
            }
        });

        return true;
    }


    protected void onPause() {
       super.onPause();
      finish();
   }


}
