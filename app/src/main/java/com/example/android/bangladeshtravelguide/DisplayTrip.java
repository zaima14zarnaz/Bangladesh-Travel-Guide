package com.example.android.bangladeshtravelguide;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayTrip extends AppCompatActivity {

    TextView destination;
    TextView transportCost;
    TextView hotelCost;
    TextView vistingCost;
    TextView seatType;
    TextView seatCost;
    TextView depart;
    TextView arrival;
    TextView transportName;
    TextView hotel;
    TextView roomName;
    TextView price;
    TextView totalCost;
    ImageView transportImage;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trip);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        destination = (TextView)findViewById(R.id.dest);
        transportCost = (TextView)findViewById(R.id.transportCost);
        hotelCost = (TextView)findViewById(R.id.hotelCost);
        vistingCost = (TextView)findViewById(R.id.vistingCost);
        seatType = (TextView)findViewById(R.id.seatType);
        seatCost = (TextView)findViewById(R.id.seatCost);
        depart = (TextView)findViewById(R.id.dtime);
        arrival = (TextView)findViewById(R.id.atime);
        transportName = (TextView)findViewById(R.id.nameOfTransport);
        hotel=(TextView)findViewById(R.id.nameOfHotel);
        roomName=(TextView)findViewById(R.id.roomName);
        price=(TextView)findViewById(R.id.roomCost);
        totalCost=(TextView)findViewById((R.id.totalCost));
        transportImage = (ImageView) findViewById(R.id.transportImage);

        String hotelName = processName(TotalRequirement.hotelname);
        hotel.setText("Hotel Name : "+hotelName);
        String rn = processName(TotalRequirement.roomname);
        roomName.setText(rn);
        price.setText("BDT "+TotalRequirement.hotelprice);
        hotelCost.setText("BDT "+TotalRequirement.hotelprice);

        destination.setText(PlaceName.placeName);
        int c = TotalRequirement.transportFare;
        transportCost.setText("BDT "+TotalRequirement.transportFare);
        depart.setText("Departure : " + TotalRequirement.departureTime);
        arrival.setText("Arrival : " + TotalRequirement.arrivalTime);
        String med = TotalRequirement.medium;
        if(med.equals("Train")) {
            transportImage.setImageResource(R.drawable.budgettrain);
            transportName.setText("Train Name : " + TotalRequirement.trainName);
            seatType.setText("Seat Type : " + TotalRequirement.seatType);
          seatCost.setText("BDT "+TotalRequirement.transportFare);
        }
        else
        {
            transportImage.setImageResource(R.drawable.budgetplane);
            transportName.setText("Flight Name : " + TotalRequirement.flightName);
            seatType.setText("Seat Type : " + TotalRequirement.seatType);

           seatCost.setText("BDT "+TotalRequirement.transportFare);
        }

        total=TotalRequirement.hotelprice+TotalRequirement.transportFare;
      totalCost.setText("BDT "+total);
    }

    public String processName(String name)
    {
        String x = new String();
        String split[] = name.split(" ");
        for(int i = 0 ; i < split.length ; i++)
        {
            String temp = split[i].toString();
            temp = temp.toLowerCase();
            String temp2 = temp.substring(1,temp.length());
            temp = temp.substring(0,1).toUpperCase();
            temp = temp + temp2;
            x = x + temp + " ";
        }
        return x;
    }
}
