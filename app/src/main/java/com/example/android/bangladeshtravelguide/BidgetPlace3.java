package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class BidgetPlace3 extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter2 myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidget_place3);
        Button button = (Button) findViewById(R.id.threebtn);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                openActivity4();
            }
        });

        recyclerView = findViewById(R.id.rvhotel);
        int pos = getIntent().getIntExtra("jabi", 999);
        final City c = BudgetPlace2.result.get(pos);
        Log.e("3rd", c.getHotel() + pos + "\n" + c.toString());
        myadapter = new MyAdapter2(getApplicationContext(), c.getDetail());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
        final ArrayList<Room>a=c.getDetail();
        myadapter.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos) {
                TotalRequirement.hotelname=c.getHotel();
                TotalRequirement.roomname=a.get(pos).getRn();
                TotalRequirement.hotelprice=a.get(pos).getP();
                openActivity4();
                Toast.makeText(getApplicationContext(), "click on item" + pos, Toast.LENGTH_LONG).show();
            }
        });
    }

        public void openActivity4 () {
            Intent intent = new Intent(BidgetPlace3.this, DisplayTrip.class);
            startActivity(intent);
    }
    }
