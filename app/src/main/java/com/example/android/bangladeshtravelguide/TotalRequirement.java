package com.example.android.bangladeshtravelguide;

public class TotalRequirement
{
    static String medium;

    static String trainName;
    static String departureTime;
    static String arrivalTime;
    static String seatType;

    static String flightName;

    static int transportFare = 0;
    static  int hotelprice=0;
    static String hotelname;
    static String roomname;
    TotalRequirement()
    {

    }


    TotalRequirement(String m,String a,String b,String c,String d,int tf)
    {

        medium = m;
        transportFare = tf;

        trainName  = a;
        departureTime = b;
        arrivalTime = c;
        seatType = d;
    }



}
