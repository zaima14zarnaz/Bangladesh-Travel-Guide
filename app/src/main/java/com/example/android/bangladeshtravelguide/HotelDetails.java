package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

public class HotelDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        recyclerView=findViewById(R.id.rvhotel);
        int pos=getIntent().getIntExtra("jabi na kn",999);
        City c=SecondActivity.result.get(pos);
        Log.e("3rd",c.getHotel()+pos+"\n"+c.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Myadapter(getApplicationContext(),c.getDetail()));


    }
}
