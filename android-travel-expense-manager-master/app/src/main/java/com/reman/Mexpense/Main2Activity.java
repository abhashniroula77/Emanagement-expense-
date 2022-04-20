package com.reman.Mexpense;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.reman.Mexpense.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et1 = (EditText) findViewById(R.id.editText);
        et1.setText(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void travel(View v) {
        String s = et1.getText().toString();
        if (s.equals(null) || s.equals("")) {
            Toast.makeText(this, "Enter The Title of Journey", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, NewTrips.class);
            i.putExtra("title", s);
            startActivity(i);
        }
    }

    /*@Override
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
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }*/

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.trips) {
            Intent i = new Intent(this, TripsList.class);
            startActivity(i);
        } else if (id == R.id.addexpense) {
            Intent i = new Intent(this, TripsList.class);
            i.putExtra("Add", "AddExpense");
            startActivity(i);
        } else if (id == R.id.day) {
            Intent i = new Intent(this, TripsList2.class);
            startActivity(i);
        } else if (id == R.id.category) {
            Intent i = new Intent(this, TripsList3.class);
            startActivity(i);
        } else if (id == R.id.trip) {
            Intent i = new Intent(this, TripsList1.class);
            startActivity(i);
        } else if (id == R.id.exit) {
            System.exit(0);
        } else if (id == R.id.upload_into_webservices) {
            SQLiteDatabase db = openOrCreateDatabase("Trips", MODE_APPEND, null);
            String q = "Select * from tripDetails";
            try {
                Cursor c = db.rawQuery(q, null);
                JSONArray array = new JSONArray();
                while (c.moveToNext()) {
                    JSONObject object = new JSONObject();

                    object.put("tripId", c.getString(0));
                    object.put("title", c.getString(1));
                    object.put("source", c.getString(2));
                    object.put("destination", c.getString(3));
                    object.put("Startdate", c.getString(4));
                    object.put("todate", c.getString(5));
                    object.put("ApprovedBudget", c.getDouble(6));
                    object.put("Balance", c.getDouble(7));
                    object.put("slct", c.getInt(8));
                    array.put(object);
                }
                db.close();
                Log.d("webservice", array.toString()+ "");
                Log.d("webservice", "Data uploaded successfully");
            } catch (Exception e) {
            }
        } else if (id == R.id.deleteall) {

            SQLiteDatabase db1 = openOrCreateDatabase("Trips", MODE_APPEND, null);
            SQLiteDatabase db2 = openOrCreateDatabase("TripDatabase", MODE_APPEND, null);
            int c1 = db1.delete("tripDetails", null, null);
            if (c1 > 0)
                Toast.makeText(getBaseContext(), "All items deleted successfully", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
