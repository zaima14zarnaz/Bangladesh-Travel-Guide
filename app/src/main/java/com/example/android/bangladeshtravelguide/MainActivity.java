package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView search = (ImageView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.placeName);
                String placeName = editText.getText().toString();





                if(!placeName.equals(""))
                {
                    placeName = processInput(placeName);
                    PlaceName placeName1 = new PlaceName(placeName);
                    Intent i = new Intent(MainActivity.this,BudgetPlace1.class);
                    startActivity(i);

                }


            }
        });

        int images[] = {R.drawable.candles,R.drawable.candles2,R.drawable.candles3,R.drawable.candle4,R.drawable.candle5,R.drawable.candle7};

        v_flipper = findViewById(R.id.v_flipper);

        for(int i = 0 ; i < images.length ; i++)
        {
            flipperImages(images[i]);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_tab1) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new Tab1()).commit();
        }
        else if (id == R.id.nav_tab2) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new Tab2()).commit();
        }
        else if (id == R.id.nav_tab3) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new Tab3()).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void flipperImages(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public String findDistrict(String district)
    {

        String divison = new String();
        DistrictWiseDivison districtWiseDivison = new DistrictWiseDivison();

        if(district.equals("Coxs' Bazar")) divison = "Coxs' Bazar";
        else if(district.equals("Saidput")) divison = "Saidpur";

        else if(districtWiseDivison.Dhaka.contains(district)) divison = "Dhaka";
        else if(districtWiseDivison.Chattogram.contains(district)) divison = "Chattogram";
        else if(districtWiseDivison.Jassore.contains(district)) divison = "Jassore";
        else if(districtWiseDivison.Rajshahi.contains(district)) divison = "Rajshahi";
        else if(districtWiseDivison.Barisal.contains(district)) divison = "Barisal";
        else if(districtWiseDivison.Sylhet.contains(district)) divison = "Sylhet";
        else divison = "Invalid";


        return divison;

    }

    public String processInput(String x) {
        x = x.replaceAll(" ", "");
        x = x.toLowerCase();
        char[] array = x.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return x = new String(array);
    }
}
