package com.example.android.bangladeshtravelguide;

import java.util.ArrayList;


public class City {

    String hotel;
    ArrayList<Room>detail;

    public City(String hotel, ArrayList<Room> detail) {
        this.hotel = hotel;
        this.detail = detail;
    }

    public String getHotel() {
        return hotel;
    }

    public ArrayList<Room> getDetail() {
        return detail;
    }


    @Override
    public String toString() {
        String a="";
        for(int i=0;i<detail.size();i++)
        {
            a+=detail.get(i).getRn()+"\n";
        }
        return a;
    }
}
