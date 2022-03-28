package com.example.android.bangladeshtravelguide;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Plane extends Fragment {

    String arrival = "",departure = "",arrMonth = "",departMonth = "";
    String arrDate = "",departDate = "";
    int dp,dm;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    public static String rec;
    String t;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_plane,container,false);

        Button btn = (Button)view.findViewById(R.id.go);


        btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rec = "";

                EditText arrPlace = (EditText)view.findViewById(R.id.arrivalPlace);
                EditText depPlace = (EditText)view.findViewById(R.id.departPlace);
                EditText arrivalMonth = (EditText)view.findViewById(R.id.returnMonth);
                EditText arrivalDate = (EditText)view.findViewById(R.id.returnDate);
                EditText departM = (EditText)view.findViewById(R.id.depattMonth);
                EditText departD = (EditText)view.findViewById(R.id.departDate);


                arrival = arrPlace.toString();
                departure = depPlace.toString();
                arrMonth = arrivalMonth.toString();
                arrDate = arrivalDate.toString();
                departMonth = departM.toString();
                departDate = departD.toString();

                int dp = (int)arrDate.charAt(1) + 10*(int)arrDate.charAt(0);
                int dm = (int)departDate.charAt(1) + 10*(int)departDate.charAt(0);

                new doIt().execute();
                getWebsite();

                rec = rec + "csmr ";


                TextView textView = (TextView)view.findViewById(R.id.rec);
                textView.setText(t);


            }
        });
        return view;



  //      return view;
    }

    public class doIt extends AsyncTask<Void,Void,Void>{
        String words;
        StringBuilder boulder = new StringBuilder();
        String t;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://www.flynovoair.com/travelinfo/flight_schedules").get();

                String title = doc.title();
                String text = doc.text();
                boulder.append(title).append("\n").append(text).append("\n");

            }
            catch (Exception e)
            {
                e.printStackTrace();
                boulder.append("Error : ").append(e.getMessage()).append("\n");

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            t = t + boulder.toString();
        }

    }
    public void getWebsite()
    {

        final StringBuilder builder = new StringBuilder();
        final StringBuilder b = new StringBuilder();
        final StringBuilder act = new StringBuilder();

        while(true)
        {
            if(t.length() >= 3800) break;
        }


                    String actual = t.substring(1592,3774);
                    actual = actual.replaceAll("Flight No","");
                    actual = actual.replaceAll("Arrival","");
                    actual = actual.replaceAll("Departure","");
                    actual = actual.replaceAll("Days of Operation","");

                    String x = "Dhaka";
                    String y = "Saidpur";
                    int indexX = actual.indexOf(x);
                    int indexY = actual.indexOf(y);
                    if(indexX == indexY + x.length() + 3)
                    {
                        act.append("Found\n");
                    }
                    else
                    {
                        while (indexX >= 0)
                        {
                            indexX = actual.indexOf(x,indexX + x.length());

                            Boolean found = true;

                            for(int i = indexX + x.length() + 3 ,k = 0 ; k < y.length() ; i++,k++)
                            {
                                if(actual.charAt(i) != y.charAt(k))
                                {
                                    found = false;
                                    break;
                                }
                            }
                            if(found == true)
                            {
                                actual = actual.substring(indexY + y.length() + 5,actual.length());
                                act.append("Found\n");
                                break;
                            }
                        }
                    }



                    int date = 12;
                    String month = "Nov";

                    String reqDay = getday(date,month);

                    String records = new String();

                    records = getFlights(reqDay,actual);


                    act.append(reqDay).append("\n");
                    act.append(records).append("\n");

                    rec = rec + act.toString();





    }
    String getFlights(String day,String total)
    {
        String flights = new String();
        String temp = new String();
        int k = 0;
        for(int i = 0 ; i < total.length() ; i++)
        {
            if(k == 4)
            {
                k = 0;
                if(total.charAt(i) != 'V')
                {
                    flights = flights + temp;
                    break;
                }
                else
                {
                    if(temp.contains("Daily") || temp.contains(day)) {
                        flights = flights + temp + "|";
                        temp = "";
                    }
                }
            }
            if(total.charAt(i) == ' ')
            {
                k++;
            }
            temp = temp + total.charAt(i);


        }
        return flights;
    }

    ArrayList<String> initialize(ArrayList<String>months)
    {
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        return months;
    }

    ArrayList<String> initialize2(ArrayList<String>days)
    {
        days.add("Sun");
        days.add("Mon");
        days.add("Tues");
        days.add("Wed");
        days.add("Thurs");
        days.add("Fri");
        days.add("Sat");

        return days;
    }
    String getday(int date,String month)
    {
        ArrayList<String> months = new ArrayList<String>(12);
        ArrayList<String>days = new ArrayList<String>(7);
        months = initialize(months);
        days = initialize2(days);

        int p = months.indexOf(month);


        String gaps = new String();
        gaps = "144025036146";


        int gap = (int)gaps.charAt(p);

        int dayNum = (date + gap)%7;
        String reqDay = days.get(dayNum);

        return reqDay;


    }


}
