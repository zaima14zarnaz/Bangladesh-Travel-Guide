package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BudgetPlace1 extends AppCompatActivity {

    public String source = "";
    public String dest = "";
    public String date = "";
    public String month = "";
    public String day = "";
    public int d = 0;

    public String promo = "";
    public String excursion = "";
    public String saver = "";
    public String flexible = "";
    public String discounted = "";

    TextView textView;


    ArrayList<String> trainRoutesGo = new ArrayList<String>();
    ArrayList<String> flightNames = new ArrayList<String>();
    ArrayList<String> fly = new ArrayList<String>();
    ArrayList<String> land = new ArrayList<String>();
    ArrayList<String> dayOfOperation = new ArrayList<String>();

    LinearLayout linearLayout;
    LinearLayout linearLayoutTrain;
    TableLayout trainPrice;

    String myFlight;
    String myTrain;
    String departTime;
    String arrivalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_place1);


        linearLayout = (LinearLayout)findViewById(R.id.planeList);
        linearLayoutTrain = (LinearLayout)findViewById(R.id.trainList);
        trainPrice  =  (TableLayout)findViewById(R.id.planePrice);

        trainPrice.setVisibility(View.GONE);

        final LinearLayout planeFare = (LinearLayout)findViewById(R.id.planeFare);
        planeFare.setVisibility(View.GONE);

        TableRow t1 = (TableRow)findViewById(R.id.first);
        TableRow t2 = (TableRow)findViewById(R.id.sec);
        TableRow t3 = (TableRow)findViewById(R.id.third);
        TableRow t4 = (TableRow)findViewById(R.id.fourth);
        TableRow t5 = (TableRow)findViewById(R.id.fifth);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement tq = new TotalRequirement("Train",myTrain,departTime,arrivalTime,"AC Class Berth",1093);
                openActivity2();
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement tq = new TotalRequirement("Train",myTrain,departTime,arrivalTime,"AC Class Seat",731);
                openActivity2();
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement tq = new TotalRequirement("Train",myTrain,departTime,arrivalTime,"Snigdha",610);
                openActivity2();
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement tq = new TotalRequirement("Train",myTrain,departTime,arrivalTime,"First Class Berth",635);
                openActivity2();
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement tq = new TotalRequirement("Train",myTrain,departTime,arrivalTime,"First Class",425);
                openActivity2();
            }
        });



        final RelativeLayout myPromo = (RelativeLayout) findViewById(R.id.PromoLayout);
        final RelativeLayout myEx = (RelativeLayout) findViewById(R.id.ExcursionLayout);
        final RelativeLayout mySaver = (RelativeLayout) findViewById(R.id.SaverLayout);
        final RelativeLayout myDiscount = (RelativeLayout) findViewById(R.id.DiscountedLayout);
        final RelativeLayout myFlex = (RelativeLayout) findViewById(R.id.FlexibleLayout);

        myPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement.medium = "Plane";
                TotalRequirement.flightName = myFlight;
                TotalRequirement.departureTime = departTime;
                TotalRequirement.arrivalTime = arrivalTime;
                TotalRequirement.seatType = "Promo";
                TextView p  = (TextView)findViewById(R.id.promo);
                TotalRequirement.transportFare = Integer.parseInt(p.getText().toString());
                myPromo.setBackgroundColor(Color.parseColor("#43A047"));
                mySaver.setBackgroundColor(Color.parseColor("#8BC34A"));
                myDiscount.setBackgroundColor(Color.parseColor("#8BC34A"));
                myFlex.setBackgroundColor(Color.parseColor("#8BC34A"));
                myEx.setBackgroundColor(Color.parseColor("#8BC34A"));
                openActivity2();


            }
        });

        myEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement.medium = "Plane";
                TotalRequirement.flightName = myFlight;
                TotalRequirement.departureTime = departTime;
                TotalRequirement.arrivalTime = arrivalTime;
                TotalRequirement.seatType = "Excursion";
                TextView p  = (TextView)findViewById(R.id.excursion);
                TotalRequirement.transportFare = Integer.parseInt(p.getText().toString());
                myEx.setBackgroundColor(Color.parseColor("#43A047"));
                myPromo.setBackgroundColor(Color.parseColor("#8BC34A"));
                mySaver.setBackgroundColor(Color.parseColor("#8BC34A"));
                myDiscount.setBackgroundColor(Color.parseColor("#8BC34A"));
                myFlex.setBackgroundColor(Color.parseColor("#8BC34A"));

                openActivity2();
            }
        });

        mySaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement.medium = "Plane";
                TotalRequirement.flightName = myFlight;
                TotalRequirement.departureTime = departTime;
                TotalRequirement.arrivalTime = arrivalTime;
                TotalRequirement.seatType = "Saver";
                TextView p  = (TextView)findViewById(R.id.saver);
                TotalRequirement.transportFare = Integer.parseInt(p.getText().toString());
                mySaver.setBackgroundColor(Color.parseColor("#43A047"));
                myEx.setBackgroundColor(Color.parseColor("#8BC34A"));
                myPromo.setBackgroundColor(Color.parseColor("#8BC34A"));
                myDiscount.setBackgroundColor(Color.parseColor("#8BC34A"));
                myFlex.setBackgroundColor(Color.parseColor("#8BC34A"));
                openActivity2();
            }
        });

        myDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement.medium = "Plane";
                TotalRequirement.flightName = myFlight;
                TotalRequirement.departureTime = departTime;
                TotalRequirement.arrivalTime = arrivalTime;
                TotalRequirement.seatType = "Discounted";
                TextView p  = (TextView)findViewById(R.id.discounted);
                TotalRequirement.transportFare = Integer.parseInt(p.getText().toString());
                myDiscount.setBackgroundColor(Color.parseColor("#43A047"));
                mySaver.setBackgroundColor(Color.parseColor("#8BC34A"));
                myEx.setBackgroundColor(Color.parseColor("#8BC34A"));
                myPromo.setBackgroundColor(Color.parseColor("#8BC34A"));
                myFlex.setBackgroundColor(Color.parseColor("#8BC34A"));
                openActivity2();
            }
        });

        myFlex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalRequirement.medium = "Plane";
                TotalRequirement.flightName = myFlight;
                TotalRequirement.departureTime = departTime;
                TotalRequirement.arrivalTime = arrivalTime;
                TotalRequirement.seatType = "Flexible";
                TextView p  = (TextView)findViewById(R.id.flexible);
                TotalRequirement.transportFare = Integer.parseInt(p.getText().toString());
                myFlex.setBackgroundColor(Color.parseColor("#43A047"));
                mySaver.setBackgroundColor(Color.parseColor("#8BC34A"));
                myEx.setBackgroundColor(Color.parseColor("#8BC34A"));
                myPromo.setBackgroundColor(Color.parseColor("#8BC34A"));
                myDiscount.setBackgroundColor(Color.parseColor("#8BC34A"));
                openActivity2();

            }
        });





        final ImageView plane = (ImageView)findViewById(R.id.plane);
        final ImageView train = (ImageView)findViewById(R.id.train);




        textView = (TextView)findViewById(R.id.voot2);

        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planeFare.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                linearLayoutTrain.setVisibility(View.VISIBLE);
                trainPrice.setVisibility(View.GONE);


                removeRows();
                removePlaneRows();

                linearLayout.setVisibility(View.VISIBLE);

                plane.setColorFilter(Color.parseColor("#00695C"));
                train.setColorFilter(Color.parseColor("#FFFFFF"));

                EditText one = (EditText)findViewById(R.id.source);
                EditText two = (EditText)findViewById(R.id.date);
                EditText three = (EditText)findViewById(R.id.month);

                source = one.getText().toString();
                date = two.getText().toString();
                month = three.getText().toString();
                PlaceName place = new PlaceName();
                dest = place.placeName;


                if(!source.equals("") || !date.equals("") || !month.equals(""))
                {
                    source = processInput(source);
                    dest = processInput(dest);
                    date = processInput(date);
                    month = processInput(month);

                    source = findDistrict(source);
                    dest = findDistrict(dest);

                    d = Integer.parseInt(date);


                    new BudgetPlace1.MyTask2().execute("https://api.myjson.com/bins/1a3ul2");

                }
                else
                {
                    Toast.makeText(getBaseContext(),"All the fields must be filled",Toast.LENGTH_LONG).show();
                }

            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planeFare.setVisibility(View.GONE);

                linearLayout.setVisibility(View.GONE);
                linearLayoutTrain.setVisibility(View.VISIBLE);
                trainPrice.setVisibility(View.GONE);

                removeRows();

                linearLayoutTrain.setVisibility(View.VISIBLE);


                train.setColorFilter(Color.parseColor("#00695C"));
                plane.setColorFilter(Color.parseColor("#FFFFFF"));

                EditText one = (EditText)findViewById(R.id.source);
                EditText two = (EditText)findViewById(R.id.date);
                EditText three = (EditText)findViewById(R.id.month);

                source = one.getText().toString();
                date = two.getText().toString();
                month = three.getText().toString();
                PlaceName place = new PlaceName();
                dest = place.placeName;
                if(!source.equals("") || !date.equals("") || !month.equals(""))
                {
                    source = processInput(source);
                    dest = processInput(dest);
                    date = processInput(date);
                    month = processInput(month);

                    d = Integer.parseInt(date);

                    getWebsiteTrain(source,dest,d,month);

                    for (int i = 0; i < trainRoutesGo.size(); i++) {
                        addToTrainTableGo(trainRoutesGo.get(i));
                    }

                    trainRoutesGo.clear();


                }
                else
                {
                    Toast.makeText(getBaseContext(),"All the fields must be filled",Toast.LENGTH_LONG).show();
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
                textView.setText("Error");
                e.printStackTrace();
            }

          //  textView.setText(result);

            StringBuilder re = new StringBuilder();

            JSONArray flights = jsonObj.optJSONArray("Flight");
            String res = new String();

            day = getday(d,month);

            Boolean goFound = false;




            for(int i = 0 ; i < flights.length() ; i++) {
                JSONObject c = flights.optJSONObject(i);

                String s = c.optString("source");
                String d = c.optString("dest");


                if (s.equals(source) && d.equals(dest)) {
                    JSONArray list = c.optJSONArray("list");

                    String depTime = new String();
                    String arrTime = new String();
                    String name = new String();
                    String TheDay = new String();


                    for (int j = 0; j < list.length(); j++) {
                        JSONObject x = list.optJSONObject(j);

                        name = x.optString("name");
                        depTime = x.optString("depTime");
                        arrTime = x.optString("arrTime");
                        TheDay = x.optString("day");


                        if (TheDay.contains(day) || TheDay.equals("Daily")) {
                            goFound = true;

                            addToPlaneTableGo(name, depTime, arrTime, TheDay);
                        }


                    }

                    JSONObject prices = c.optJSONObject("prices");
                    promo = prices.optString("Promo");
                    excursion = prices.optString("Excursion");
                    saver = prices.optString("Saver");
                    discounted = prices.optString("Discounted");
                    flexible = prices.optString("Flexible");


                }


                if (goFound) break;


            }

            TextView a = (TextView)findViewById(R.id.promo);
            TextView b = (TextView)findViewById(R.id.excursion);
            TextView c = (TextView)findViewById(R.id.saver);
            TextView d = (TextView)findViewById(R.id.discounted);
            TextView e= (TextView)findViewById(R.id.flexible);

            a.setText(promo);
            b.setText(excursion);
            c.setText(saver);
            d.setText(flexible);
            e.setText(discounted);



          //  textView.setText(res);



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
                textView.setText("Error");
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

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

    public void openActivity2()
    {
        Intent intent = new Intent(BudgetPlace1.this,BudgetPlace2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void getWebsiteTrain(final String x, final String y, final int date, final String month) {
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

                    actual = findTrains(x, y, actual, reqDay);

                    act.append(actual);


                } catch (Exception e) {
                    e.printStackTrace();
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // textView.setText(act.toString());
                    }
                });
            }

        }).start();
    }

    String findTrains(String x, String y, String actual, String day) {
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

                        if (temp.indexOf(x) < temp.indexOf(y)) {
                            trainRoutesGo.add(temp);
                            total = total + temp;

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

    public void addToTrainTableGo(String temp) {





        if (temp.charAt(0) == '8') {
            String temp2 = temp.substring(1, temp.length());
            final String split[] = temp2.split(" ");

            TextView textView = new TextView(this);
            textView.setText(split[1] + " " + split[2]);
            TextView textView2 = new TextView(this);
            textView2.setText(split[5]+"                                            "+split[7]);
            TextView textView3 = new TextView(this);
            textView3.setText("Departure                                 Arrival");

            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor("#000000"));

            textView.setPadding(16,16,8,8);
            textView2.setPadding(16,0,0,8);
            textView3.setPadding(16,16,0,8);

            LinearLayout planeItem  = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,8,8,8);
            planeItem.setLayoutParams(params);

            planeItem.setPadding(16,16,16,16);
            planeItem.setOrientation(LinearLayout.VERTICAL);
            planeItem.setBackgroundColor(Color.parseColor("#FFFFFF"));

            planeItem.addView(textView);
            planeItem.addView(textView3);
            planeItem.addView(textView2);

            planeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayoutTrain.setVisibility(View.GONE);
                    myTrain = split[1].toString() + " " + split[2].toString();
                    departTime = split[5].toString();
                    arrivalTime = split[7].toString();
                    trainPrice.setVisibility(View.VISIBLE);
                }
            });


            linearLayoutTrain.addView(planeItem);


        } else if (temp.charAt(0) == '7') {
            String temp2 = temp.substring(1, temp.length());
            final String split[] = temp2.split(" ");

            TextView textView = new TextView(this);
            textView.setText(split[1] + " " + split[2]);
            TextView textView2 = new TextView(this);
            textView2.setText(split[4]+"                                            "+split[6]);
            TextView textView3 = new TextView(this);
            textView3.setText("Departure                                 Arrival");

            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor("#000000"));

            textView.setPadding(16,16,8,8);
            textView2.setPadding(16,0,0,8);
            textView3.setPadding(16,16,0,8);

            LinearLayout planeItem  = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,8,8,8);
            planeItem.setLayoutParams(params);

            planeItem.setPadding(16,16,16,16);
            planeItem.setOrientation(LinearLayout.VERTICAL);
            planeItem.setBackgroundColor(Color.parseColor("#FFFFFF"));

            planeItem.addView(textView);
            planeItem.addView(textView3);
            planeItem.addView(textView2);

            planeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.GONE);
                    myTrain = split[1].toString() + " " + split[2].toString();
                    departTime = split[4].toString();
                    arrivalTime = split[6].toString();
                    trainPrice.setVisibility(View.VISIBLE);
                }
            });


            linearLayoutTrain.addView(planeItem);


        }


    }

    public void removeRows() {
        linearLayoutTrain.setVisibility(View.GONE);
        if(linearLayoutTrain.getChildCount() >= 1)
        {
            linearLayoutTrain.removeAllViewsInLayout();
        }

    }

    public void addToPlaneTableGo(final String flight, final String fly1, final String land1, String dayOfOperation1)
    {
       /* flightNames.add(flight);
        fly.add(fly1);
        land.add(land1);
        dayOfOperation.add(dayOfOperation1);*/


       TextView textView = new TextView(this);
       textView.setText(flight);
       TextView textView2 = new TextView(this);
       textView2.setText(fly1+"                                            "+land1);
       TextView textView3 = new TextView(this);
       textView3.setText("Departure                                 Arrival");

       textView.setTextSize(20);
       textView.setTextColor(Color.parseColor("#000000"));

       textView.setPadding(16,16,8,8);
       textView2.setPadding(16,0,0,8);
       textView3.setPadding(16,16,0,8);

       LinearLayout planeItem  = new LinearLayout(this);
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       params.setMargins(8,8,8,8);
       planeItem.setLayoutParams(params);

       planeItem.setPadding(16,16,16,16);
       planeItem.setOrientation(LinearLayout.VERTICAL);
       planeItem.setBackgroundColor(Color.parseColor("#FFFFFF"));

       planeItem.addView(textView);
       planeItem.addView(textView3);
       planeItem.addView(textView2);

       planeItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               linearLayout.setVisibility(View.GONE);
               trainPrice.setVisibility(View.GONE);
               LinearLayout planeFare = (LinearLayout)findViewById(R.id.planeFare);
               planeFare.setVisibility(View.VISIBLE);
               myFlight = flight;
               departTime = fly1;
               arrivalTime = land1;
           }
       });




       linearLayout.addView(planeItem);


    }

    public void removePlaneRows() {
        linearLayout.setVisibility(View.GONE);
        if(linearLayout.getChildCount() >= 1)
        {
            linearLayout.removeAllViewsInLayout();
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
