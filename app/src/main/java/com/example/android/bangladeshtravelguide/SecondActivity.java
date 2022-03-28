package com.example.android.bangladeshtravelguide;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity{

    public static ArrayList<City>mCity=new ArrayList<>();
    public  static ArrayList<AllCity>allCity=new ArrayList<>();
    public static ArrayList<City>result=new ArrayList<>();
    public static int  c=0;
    public int flag=0;
    public String place;
    ListView listView;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView=(ListView) findViewById(R.id.listView);
        place=getIntent().getStringExtra("hotel");
        Log.e("knhoyna",place);
        MyTask myTask=new MyTask();
        myTask.execute("https://api.myjson.com/bins/6f0ba");



    }
    public class MyTask extends AsyncTask<String, String, String> {

        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://api.myjson.com/bins/6f0ba");
                //"https://api.androidhive.info/contacts/"
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringbuffer = new StringBuffer();
                String line = "";

                while((line = bufferedReader.readLine()) != null) {
                    stringbuffer.append(line);
                }

                String s = stringbuffer.toString();

                JSONArray jo = new JSONArray(s);

                for(int o=0;o<jo.length();o++) {
                    JSONObject jsonObject = jo.getJSONObject(o);
                    JSONArray jsonArray= jsonObject.getJSONArray("city");

                    StringBuffer lastbuffer = new StringBuffer();
                    String name;
                    String hotel, des;
                    int price;
                    Integer age;
                    ArrayList<City> temp= new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //JSONObject o=new JSONObject();
                        // hotel =o.optString("hotel");
                        ArrayList<Room> arrayList = new ArrayList<>();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        hotel = obj.getString("hotel");
                        JSONArray array = obj.getJSONArray("item");
                        // lastbuffer.append("HOTEL:"+hotel+"\n");
                        for (int j = 0; j < array.length(); j++) {
                            JSONObject obj1 = array.getJSONObject(j);

                            name = obj1.getString("name");
                            des = obj1.getString("description");
                            price = obj1.getInt("price");
                            // lastbuffer.append("\nROOM NAME:"+name+"\nPRICE:"+price+"\n");
                            Room rm = new Room(name, des, price);
                            Log.e("city",name+price+"\n");
                            arrayList.add(rm);
                        }
                        mCity.add(new City(hotel, arrayList));
                        temp.add(new City(hotel,arrayList));

                    }
                    allCity.add(new AllCity(temp));
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return "no data";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //ListView listView =(ListView) findViewById(R.id.listView);
            ArrayList<City>cities=new ArrayList<>();

            if(place.equals("Khulna") || place.equals("khulna") ){
                 cities=allCity.get(0).getCities();
            }
            else  if(place.equals("Sylhet") || place.equals("sylhet")) {
                cities=allCity.get(1).getCities();
            }
            else  if(place.equals("Chittagong") || place.equals("chittagong")) {
                cities=allCity.get(2).getCities();
            }
            else  if(place.equals("Cox's bazaar") || place.equals("cox's bazaar")) {
                cities=allCity.get(3).getCities();
            }
            else  if(place.equals("Rajshahi") || place.equals("rajshahi")) {
                cities=allCity.get(4).getCities();
            }
            else  if(place.equals("Dhaka") || place.equals("dhaka")) {
                cities=allCity.get(5).getCities();
            }
            else  if(place.equals("Barisal") || place.equals("barisal")) {
                cities=allCity.get(6).getCities();
            }
            else  if(place.equals("Rangpur") || place.equals("rangpur")) {
                cities=allCity.get(7).getCities();
            }
            else  if(place.equals("Jessore") || place.equals("jessore")) {
                cities=allCity.get(8).getCities();
            }
            else  if(place.equals("Rangamati")|| place.equals("rangamati")) {
                cities=allCity.get(9).getCities();
            }
            Log.e("place","iuiuiuere");

            ArrayList<String> arrayList=new ArrayList<>();

            //String[] names = new String[c];

            for (int i = 0; i <cities.size(); i++) {
                City c=cities.get(i);
                result.add(c);
                arrayList.add(c.getHotel());
                Log.e("hotel",c.getHotel());

            }
            adapter = new ArrayAdapter<String>(SecondActivity.this,android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    City c=result.get(position);
                    Intent intent=new Intent(SecondActivity.this,HotelDetails.class);
                    Log.e("pos",c.getHotel()+position);
                    intent.putExtra("jabi na kn",position);
                    startActivity(intent);

                }
            });
            adapter.notifyDataSetChanged();

            //t1.setText(s);
        }
    }
}

