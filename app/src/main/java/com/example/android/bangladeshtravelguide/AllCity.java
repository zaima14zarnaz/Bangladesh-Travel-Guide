package com.example.android.bangladeshtravelguide;

import java.util.ArrayList;

public class AllCity {
    ArrayList<City>cities;

    public AllCity(ArrayList<City> cities) {
        this.cities = cities;
    }

    public AllCity() {
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
