package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlaneRoutes extends AppCompatActivity {

    String arrival = "",departure = "",arrMonth = "",departMonth = "";
    String arrDate = "",departDate = "";
    int dp,dm;

    public static String rec;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_routes);


        Button btn = (Button)findViewById(R.id.go);


        btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rec = "";

                EditText arrPlace = (EditText)findViewById(R.id.arrivalPlace);
                EditText depPlace = (EditText)findViewById(R.id.departPlace);
                EditText arrivalMonth = (EditText)findViewById(R.id.returnMonth);
                EditText arrivalDate = (EditText)findViewById(R.id.returnDate);
                EditText departM = (EditText)findViewById(R.id.depattMonth);
                EditText departD = (EditText)findViewById(R.id.departDate);


                arrival = arrPlace.toString();
                departure = depPlace.toString();
                arrMonth = arrivalMonth.toString();
                arrDate = arrivalDate.toString();
                departMonth = departM.toString();
                departDate = departD.toString();

                int dp = (int)arrDate.charAt(1) + 10*(int)arrDate.charAt(0);
                int dm = (int)departDate.charAt(1) + 10*(int)departDate.charAt(0);


            }
        });

    }






}
