package com.example.android.bangladeshtravelguide;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Budget extends AppCompatActivity {

    TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        myText = (TextView) findViewById(R.id.hagu);
        Button myButton = findViewById(R.id.btn);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute("https://api.myjson.com/bins/1fuw7m");
            }

        });


    }
    private class MyTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            StringBuilder re = new StringBuilder();

            JSONArray flights = jsonObj.optJSONArray("Flight");
            String res = new String();

            for(int i = 0 ; i < flights.length() ; i++)
            {
                JSONObject c = flights.optJSONObject(i);

                String source = c.optString("source");
                String dest = c.optString("dest");
                JSONArray list = c.optJSONArray("list");

                String depTime = new String();
                String arrTime = new String();
                String name = new String();
                String day = new String();

                for(int j = 0 ; j < list.length() ; j++)
                {
                    JSONObject x = list.optJSONObject(j);

                    name = x.optString("name");
                    depTime = x.optString("depTime");
                    arrTime = x.optString("arrTime");
                    day = x.optString("day");
                }

                res = res + source + " To " + dest + "\n";
                res = res + name + "\n" + depTime + "\n" + arrTime + "\n" + day;

            }
            myText.setText(res);

        }
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();

            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

    }
}
