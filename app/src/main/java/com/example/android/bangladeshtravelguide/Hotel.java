package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Hotel extends AppCompatActivity {
    Button b1;
    EditText e1;
    public String s=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel);
        b1=(Button)findViewById(R.id.clash);
        e1=(EditText)findViewById(R.id.clash2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=e1.getText().toString();



                Intent intent=new Intent(Hotel.this,SecondActivity.class);
                intent.putExtra("hotel",s);
                startActivity(intent);

            }
        });

    }

}
