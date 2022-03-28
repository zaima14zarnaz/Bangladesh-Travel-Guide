package com.example.android.bangladeshtravelguide;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TransportChoose extends Activity {

    String arrival = "", departure = "", arrMonth = "", departMonth = "";
    String arrDate = "", departDate = "",way = "";
    int dp, dm;

    public static String rec;

    public String records;

    TableLayout planeTableLayoutGo;
    TableLayout planeTableLayoutRet;
    TableLayout trainGo;
    TableLayout trainRet;

    ArrayList<String> planeRoutesGo = new ArrayList<String>();
    ArrayList<String> planeRoutesRet = new ArrayList<String>();
    ArrayList<String> trainRoutesGo = new ArrayList<String>();
    ArrayList<String> trainRoutesRet = new ArrayList<String>();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_choose);


        final ImageView plane = (ImageView) findViewById(R.id.plane);
        final ImageView train = (ImageView) findViewById(R.id.train);

        planeTableLayoutGo = (TableLayout) findViewById(R.id.one);
        planeTableLayoutRet = (TableLayout) findViewById(R.id.two);
        trainGo = (TableLayout) findViewById(R.id.three);
        trainRet = (TableLayout) findViewById(R.id.four);

        final RadioButton one = (RadioButton) findViewById(R.id.oneWay);
        final RadioButton round = (RadioButton) findViewById(R.id.roundTrip);

        one.setChecked(true);
        round.setChecked(false);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.one);


        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (round.isChecked()) {
                    one.setChecked(false);
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (one.isChecked()) {
                    round.setChecked(false);
                }
            }
        });


        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                plane.setColorFilter(Color.parseColor("#1565C0"));
                train.setColorFilter(Color.parseColor("#FFFFFF"));

                trainGo.setVisibility(View.GONE);
                trainRet.setVisibility(View.GONE);
                planeTableLayoutGo.setVisibility(View.VISIBLE);
                planeTableLayoutRet.setVisibility(View.VISIBLE);

                removePlaneRows();


                EditText arrPlace = (EditText) findViewById(R.id.arrivalPlace);
                EditText depPlace = (EditText) findViewById(R.id.departPlace);
                EditText arrivalMonth = (EditText) findViewById(R.id.returnMonth);
                EditText arrivalDate = (EditText) findViewById(R.id.returnDate);
                EditText departM = (EditText) findViewById(R.id.depattMonth);
                EditText departD = (EditText) findViewById(R.id.departDate);

                Boolean oneWay = false;

                RadioButton radioButton = (RadioButton) findViewById(R.id.oneWay);
                RadioButton roundTrip = (RadioButton) findViewById(R.id.roundTrip);
                if (radioButton.isChecked()) {
                    oneWay = true;
                }


                arrival = arrPlace.getText().toString();
                departure = depPlace.getText().toString();
                arrMonth = arrivalMonth.getText().toString();
                arrDate = arrivalDate.getText().toString();
                departMonth = departM.getText().toString();
                departDate = departD.getText().toString();


                if ((!roundTrip.isChecked() && !radioButton.isChecked()) || arrival.equals("") || departure.equals("") || arrMonth.equals("") || arrDate.equals("") || departMonth.equals("") || departDate.equals("")) {
                    fieldEmpty();
                } else {

                    arrival = processInput(arrival);
                    departure = processInput(departure);
                    arrMonth = processInput(arrMonth);
                    arrDate = processInput(arrDate);
                    departMonth = processInput(departMonth);
                    departDate = processInput(departDate);

                    arrival = findDistrict(arrival);
                    departure = findDistrict(departure);

                    if(arrival.equals("Invalid") || departure.equals("Invalid"))
                    {
                        if(arrival.equals("Invalid")) Toast.makeText(getBaseContext(),"Invalid source",Toast.LENGTH_LONG).show();
                        else if(arrival.equals("Invalid")) Toast.makeText(getBaseContext(),"Invalid destination",Toast.LENGTH_LONG).show();
                    }
                    else {


                        dp = 0;
                        dm = 0;


                        dp = Integer.parseInt(arrDate);
                        dm = Integer.parseInt(departDate);

                        way = new String();
                        if (oneWay) way = "one";
                        else way = "round";

                        new TransportChoose.MyTask2().execute("https://api.myjson.com/bins/i6fai");




                        planeRoutesRet.clear();
                        planeRoutesGo.clear();
                    }

                }
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rec = "";
                planeTableLayoutGo.setVisibility(View.GONE);
                planeTableLayoutRet.setVisibility(View.GONE);
                trainGo.setVisibility(View.VISIBLE);
                trainRet.setVisibility(View.VISIBLE);

                train.setColorFilter(Color.parseColor("#1565C0"));
                plane.setColorFilter(Color.parseColor("#FFFFFF"));

                removeRows();


                EditText arrPlace = (EditText) findViewById(R.id.arrivalPlace);
                EditText depPlace = (EditText) findViewById(R.id.departPlace);
                EditText arrivalMonth = (EditText) findViewById(R.id.returnMonth);
                EditText arrivalDate = (EditText) findViewById(R.id.returnDate);
                EditText departM = (EditText) findViewById(R.id.depattMonth);
                EditText departD = (EditText) findViewById(R.id.departDate);


                arrival = arrPlace.getText().toString();
                departure = depPlace.getText().toString();
                arrMonth = arrivalMonth.getText().toString();
                arrDate = arrivalDate.getText().toString();
                departMonth = departM.getText().toString();
                departDate = departD.getText().toString();

                Boolean oneWay = false;

                RadioButton radioButton = (RadioButton) findViewById(R.id.oneWay);
                RadioButton roundTrip = (RadioButton) findViewById(R.id.roundTrip);
                if (radioButton.isChecked()) {
                    oneWay = true;
                }

                if ((!roundTrip.isChecked() && !radioButton.isChecked()) || arrival.equals("") || departure.equals("") || arrMonth.equals("") || arrDate.equals("") || departMonth.equals("") || departDate.equals("")) {
                    fieldEmpty();
                } else {
                    arrival = processInput(arrival);
                    departure = processInput(departure);
                    arrMonth = processInput(arrMonth);
                    arrDate = processInput(arrDate);
                    departMonth = processInput(departMonth);
                    departDate = processInput(departDate);

                    int dp = 0;
                    int dx = 0;
                    dp = Integer.parseInt(arrDate);
                    dx = Integer.parseInt(departDate);


                    getWebsiteTrain(departure, arrival, dx, departMonth, oneWay);

                    for (int i = 0; i < trainRoutesGo.size(); i++) {
                        addToTrainTableGo(trainRoutesGo.get(i));
                    }
                    if (!oneWay) {
                        for (int i = 0; i < trainRoutesRet.size(); i++) {
                            addToTrainTableRet(trainRoutesRet.get(i));
                        }
                    }


                    trainRoutesGo.clear();
                    trainRoutesRet.clear();

                }


            }
        });

    }

    class MyTask2 extends AsyncTask<String, String, String> {


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

            String arrDay = getday(dp,arrMonth);
            String depDay = getday(dm,departMonth);

            Boolean goFound = false;
            Boolean retFound = false;


            for(int i = 0 ; i < flights.length() ; i++)
            {
                JSONObject c = flights.optJSONObject(i);

                String source = c.optString("source");
                String dest = c.optString("dest");

                if(source.equals(departure) && dest.equals(arrival))
                {
                    JSONArray list = c.optJSONArray("list");

                    String depTime = new String();
                    String arrTime = new String();
                    String name = new String();
                    String day = new String();

                    for(int j = 0 ; j < list.length() ; j++) {
                        JSONObject x = list.optJSONObject(j);

                        name = x.optString("name");
                        depTime = x.optString("depTime");
                        arrTime = x.optString("arrTime");
                        day = x.optString("day");


                        if (day.contains(depDay) || day.equals("Daily")) {
                            goFound = true;

                            addToPlaneTableGo(name, depTime, arrTime, day);
                        }
                    }



                }

                if(goFound) break;




            }

            if(way.equals("round"))
            {
                for(int i = 0 ; i < flights.length() ; i++)
                {
                    JSONObject c = flights.optJSONObject(i);

                    String source = c.optString("source");
                    String dest = c.optString("dest");

                    if(source.equals(arrival) && dest.equals(departure))
                    {
                        JSONArray list = c.optJSONArray("list");

                        String depTime = new String();
                        String arrTime = new String();
                        String name = new String();
                        String day = new String();

                        for(int j = 0 ; j < list.length() ; j++) {
                            JSONObject x = list.optJSONObject(j);

                            name = x.optString("name");
                            depTime = x.optString("depTime");
                            arrTime = x.optString("arrTime");
                            day = x.optString("day");


                            if (day.contains(arrDay) || day.equals("Daily")) {

                                addToPlaneTableRet(name, depTime, arrTime, day);
                                retFound = true;
                            }
                        }

                    }

                    if(retFound) break;


                }
            }


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


    public void getWebsiteTrain(final String x, final String y, final int date, final String month, final Boolean oneWay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder b = new StringBuilder();
                final StringBuilder act = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.travelonebd.com/index.php/top-tourist-spot/coxs-bazar-beach/18-bangladesh-railway-schedule").get();
                    String title = doc.title();
                    String text = doc.text();
                    builder.append(title).append("\n").append(text).append("\n");


                    String t = builder.toString();

                    String actual = t.substring(2196, 6520);

                    actual = actual.replaceAll(" Train no", "");
                    actual = actual.replaceAll(" Name", "");
                    actual = actual.replaceAll(" Offday", "");
                    actual = actual.replaceAll(" From Departure", "");
                    actual = actual.replaceAll(" To Arrival ", "");
                    actual = actual.replaceAll(" Intercity", "");
                    actual = actual.replaceAll(" Trains ", "");
                    actual = actual.replaceAll("Broad ", "");
                    actual = actual.replaceAll(" Gauge", "");
                    actual = actual.replaceAll(" Dual", "");
                    actual = actual.replaceAll("Dhaka Cantt", "Dhakacantt");
                    actual = actual.replaceAll("Thakurgaon road", "Thakurgaonroad");
                    actual = actual.replaceAll("Dewanganj Bazar", "Dewanganjbazar");
                    actual = actual.replaceAll("Goalonda hat", "Goalondahat");
                    actual = actual.replaceAll("Bharamaputra", "Bharama putra");
                    actual = actual.replaceAll("Nilsagar", "Nil sagar");
                    actual = actual.replaceAll("Silk city express", "Silkcity express");
                    actual = actual.replaceAll("Chittagong", "Chattogram");


                    String reqDay = getday(date, month) + "day";

                    actual = findTrains(x, y, actual, oneWay, reqDay);

                    act.append(actual);


                } catch (Exception e) {
                    e.printStackTrace();
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //textView.setText(act.toString());
                    }
                });
            }

        }).start();
    }

    String findTrains(String x, String y, String actual, Boolean oneWay, String day) {
        String temp = new String();
        String total = new String();
        int spaces = 0;

        for (int i = 0; i < actual.length(); i++) {
            if (actual.charAt(i) == '(' || actual.charAt(i) == ')' || actual.charAt(i) == '&') {
                continue;
            }
            if (spaces == 8 || (spaces == 7 && actual.charAt(i) == '7' && actual.charAt(i + 1) != ':')) {
                if (spaces == 8) temp = "8" + temp;
                else temp = "7" + temp;
                if (temp.contains(x) && temp.contains(y) && !temp.contains(day)) {
                    if (!oneWay) {
                        total = total + temp + "|" + "\n";
                        if (temp.indexOf(x) < temp.indexOf(y)) {
                            trainRoutesGo.add(temp);
                        } else {
                            trainRoutesRet.add(temp);
                        }
                    } else {
                        if (temp.indexOf(x) < temp.indexOf(y)) {
                            total = total + temp + "|" + "\n";
                            trainRoutesGo.add(temp);
                        }
                    }
                }
                temp = "";
                spaces = 0;
            }
            if (actual.charAt(i) == ' ') {
                spaces = spaces + 1;
            }
            temp = temp + actual.charAt(i);
        }

        return total;

    }

    public void getWebsite(final String x, final String y, final int date, final String month, final Boolean oneWay) {

    }

    String getFlights(String day, String total, Boolean oneWay) {
        String flights = new String();
        String temp = new String();
        int k = 0;
        for (int i = 0; i < total.length(); i++) {
            if (k == 4) {
                k = 0;
                if (total.charAt(i) != 'V') {
                    flights = flights + temp;
                    if (oneWay) {
                        planeRoutesGo.add(temp);
                    } else {
                        planeRoutesRet.add(temp);
                    }
                    break;
                } else {
                    if (temp.contains("Daily") || temp.contains(day)) {
                        flights = flights + temp + "|";
                        if (oneWay) {
                            planeRoutesGo.add(temp);
                        } else {
                            planeRoutesRet.add(temp);
                        }
                        temp = "";
                    }
                }
            }
            if (total.charAt(i) == ' ') {
                k++;
            }
            temp = temp + total.charAt(i);


        }
        return flights;
    }

    ArrayList<String> initialize(ArrayList<String> months) {
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

    ArrayList<String> initialize2(ArrayList<String> days) {
        days.add("Sun");
        days.add("Mon");
        days.add("Tues");
        days.add("Wed");
        days.add("Thurs");
        days.add("Fri");
        days.add("Sat");

        return days;
    }

    String getday(int date, String month) {
        ArrayList<String> months = new ArrayList<String>(12);
        ArrayList<String> days = new ArrayList<String>(7);
        months = initialize(months);
        days = initialize2(days);

        int p = months.indexOf(month);


        String gaps = new String();
        gaps = "144025036146";


        int gap = (int) gaps.charAt(p);

        int dayNum = (date + gap) % 7;
        String reqDay = days.get(dayNum + 1);

        return reqDay;


    }

    public void fieldEmpty() {
        Toast.makeText(getBaseContext(), "All fields must be filled up", Toast.LENGTH_LONG).show();
    }

    public String processInput(String x) {
        x = x.replaceAll(" ", "");
        x = x.toLowerCase();
        char[] array = x.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return x = new String(array);
    }

    public void addToTrainTableGo(String temp) {


        TableRow row = new TableRow(this);
        TableRow seperatorRow = new TableRow(this);
        TextView name = new TextView(this);
        TextView offDay = new TextView(this);
        TextView depTime = new TextView(this);
        TextView arrTime = new TextView(this);

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(params);

        TableRow.LayoutParams seperator = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        seperator.setMargins(3, 3, 3, 3);

        name.setLayoutParams(seperator);
        offDay.setLayoutParams(seperator);
        arrTime.setLayoutParams(seperator);
        depTime.setLayoutParams(seperator);

        name.setPadding(15, 8, 15, 8);
        offDay.setPadding(15, 8, 15, 8);
        depTime.setPadding(15, 8, 15, 8);
        arrTime.setPadding(15, 8, 15, 8);

        String blue = "#81D4FA";
        String white = "#FFFFFF";

        name.setTextSize(16);
        offDay.setTextSize(16);
        depTime.setTextSize(16);
        arrTime.setTextSize(16);

        name.setBackgroundColor(Color.parseColor(blue));
        offDay.setBackgroundColor(Color.parseColor(blue));
        depTime.setBackgroundColor(Color.parseColor(blue));
        arrTime.setBackgroundColor(Color.parseColor(blue));

        name.setTextColor(Color.parseColor(white));
        offDay.setTextColor(Color.parseColor(white));
        depTime.setTextColor(Color.parseColor(white));
        arrTime.setTextColor(Color.parseColor(white));


        if (temp.charAt(0) == '8') {
            String temp2 = temp.substring(1, temp.length());
            String split[] = temp2.split(" ");
            String day = split[3].toString();
            day = day.substring(0, day.length() - 3);

            name.setText(split[1].toString() + " " + split[2].toString());
            offDay.setText(day);
            depTime.setText(split[5]);
            arrTime.setText(split[7]);

            row.addView(name);
            row.addView(offDay);
            row.addView(depTime);
            row.addView(arrTime);

            trainGo.addView(row);
        } else if (temp.charAt(0) == '7') {
            String temp2 = temp.substring(1, temp.length());
            String split[] = temp2.split(" ");

            name.setText(split[1].toString() + " " + split[2].toString());
            offDay.setText("   ");
            depTime.setText(split[4]);
            arrTime.setText(split[6]);

            row.addView(name);
            row.addView(offDay);
            row.addView(depTime);
            row.addView(arrTime);

            trainGo.addView(row);
            trainGo.addView(seperatorRow);
        }


    }

    public void addToTrainTableRet(String temp) {


        TableRow row = new TableRow(this);
        TextView name = new TextView(this);
        TextView offDay = new TextView(this);
        TextView depTime = new TextView(this);
        TextView arrTime = new TextView(this);

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(params);

        TableRow.LayoutParams seperator = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        seperator.setMargins(3, 3, 3, 3);

        name.setLayoutParams(seperator);
        offDay.setLayoutParams(seperator);
        arrTime.setLayoutParams(seperator);
        depTime.setLayoutParams(seperator);

        name.setPadding(15, 8, 15, 8);
        offDay.setPadding(15, 8, 15, 8);
        depTime.setPadding(15, 8, 15, 8);
        arrTime.setPadding(15, 8, 15, 8);

        name.setTextSize(16);
        offDay.setTextSize(16);
        depTime.setTextSize(16);
        arrTime.setTextSize(16);

        String blue = "#90CAF9";
        String white = "#FFFFFF";

        name.setBackgroundColor(Color.parseColor(blue));
        offDay.setBackgroundColor(Color.parseColor(blue));
        depTime.setBackgroundColor(Color.parseColor(blue));
        arrTime.setBackgroundColor(Color.parseColor(blue));

        name.setTextColor(Color.parseColor(white));
        offDay.setTextColor(Color.parseColor(white));
        depTime.setTextColor(Color.parseColor(white));
        arrTime.setTextColor(Color.parseColor(white));


        if (temp.charAt(0) == '8') {
            String temp2 = temp.substring(1, temp.length());
            String split[] = temp2.split(" ");
            String day = split[3].toString();
            day = day.substring(0, day.length() - 3);

            name.setText(split[1].toString() + " " + split[2].toString());
            offDay.setText(day);
            depTime.setText(split[5]);
            arrTime.setText(split[7]);

            row.addView(name);
            row.addView(offDay);
            row.addView(depTime);
            row.addView(arrTime);

            trainRet.addView(row);
        } else if (temp.charAt(0) == '7') {
            String temp2 = temp.substring(1, temp.length());
            String split[] = temp2.split(" ");

            name.setText(split[1].toString() + " " + split[2].toString());
            offDay.setText("   ");
            depTime.setText(split[4]);
            arrTime.setText(split[6]);

            row.addView(name);
            row.addView(offDay);
            row.addView(depTime);
            row.addView(arrTime);

            trainRet.addView(row);
        }
    }

    public void removeRows() {
        int childCount = trainGo.getChildCount();
        if (childCount > 1) {
            trainGo.removeViews(1, childCount - 1);
        }

        childCount = trainRet.getChildCount();
        if (childCount > 1) {
            trainRet.removeViews(1, childCount - 1);
        }
    }

    public void addToPlaneTableGo(String flight,String fly,String land,String dayOfOperation)
    {
        TableRow row = new TableRow(this);
        TextView name = new TextView(this);
        TextView offDay = new TextView(this);
        TextView depTime = new TextView(this);
        TextView arrTime = new TextView(this);

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(params);

        TableRow.LayoutParams seperator = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        seperator.setMargins(3, 3, 3, 3);

        name.setLayoutParams(seperator);
        offDay.setLayoutParams(seperator);
        arrTime.setLayoutParams(seperator);
        depTime.setLayoutParams(seperator);

        name.setPadding(15, 8, 15, 8);
        offDay.setPadding(15, 8, 15, 8);
        depTime.setPadding(15, 8, 15, 8);
        arrTime.setPadding(15, 8, 15, 8);

        name.setTextSize(16);
        offDay.setTextSize(16);
        depTime.setTextSize(16);
        arrTime.setTextSize(16);

        String blue = "#90CAF9";
        String white = "#FFFFFF";

        name.setBackgroundColor(Color.parseColor(blue));
        offDay.setBackgroundColor(Color.parseColor(blue));
        depTime.setBackgroundColor(Color.parseColor(blue));
        arrTime.setBackgroundColor(Color.parseColor(blue));

        name.setTextColor(Color.parseColor(white));
        offDay.setTextColor(Color.parseColor(white));
        depTime.setTextColor(Color.parseColor(white));
        arrTime.setTextColor(Color.parseColor(white));




            name.setText(flight);
            offDay.setText(fly);
            depTime.setText(land);
            arrTime.setText(dayOfOperation);

            row.addView(name);
            row.addView(offDay);
            row.addView(depTime);
            row.addView(arrTime);

            planeTableLayoutGo.addView(row);


    }

    public void addToPlaneTableRet(String flight,String fly,String land,String dayOfOperation)
    {
        TableRow row = new TableRow(this);
        TextView name = new TextView(this);
        TextView offDay = new TextView(this);
        TextView depTime = new TextView(this);
        TextView arrTime = new TextView(this);

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(params);

        TableRow.LayoutParams seperator = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        seperator.setMargins(3, 3, 3, 3);

        name.setLayoutParams(seperator);
        offDay.setLayoutParams(seperator);
        arrTime.setLayoutParams(seperator);
        depTime.setLayoutParams(seperator);

        name.setPadding(15, 8, 15, 8);
        offDay.setPadding(15, 8, 15, 8);
        depTime.setPadding(15, 8, 15, 8);
        arrTime.setPadding(15, 8, 15, 8);

        name.setTextSize(16);
        offDay.setTextSize(16);
        depTime.setTextSize(16);
        arrTime.setTextSize(16);

        String blue = "#90CAF9";
        String white = "#FFFFFF";

        name.setBackgroundColor(Color.parseColor(blue));
        offDay.setBackgroundColor(Color.parseColor(blue));
        depTime.setBackgroundColor(Color.parseColor(blue));
        arrTime.setBackgroundColor(Color.parseColor(blue));

        name.setTextColor(Color.parseColor(white));
        offDay.setTextColor(Color.parseColor(white));
        depTime.setTextColor(Color.parseColor(white));
        arrTime.setTextColor(Color.parseColor(white));




        name.setText(flight);
        offDay.setText(fly);
        depTime.setText(land);
        arrTime.setText(dayOfOperation);

        row.addView(name);
        row.addView(offDay);
        row.addView(depTime);
        row.addView(arrTime);

        planeTableLayoutRet.addView(row);

    }

    public void removePlaneRows()
    {
        int childCount = planeTableLayoutGo.getChildCount();
        if (childCount > 1) {
            planeTableLayoutGo.removeViews(1, childCount - 1);
        }

        childCount = planeTableLayoutRet.getChildCount();
        if (childCount > 1) {
            planeTableLayoutRet.removeViews(1, childCount - 1);
        }

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
}
