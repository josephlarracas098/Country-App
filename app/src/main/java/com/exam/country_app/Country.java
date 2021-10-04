package com.exam.country_app;

import org.json.JSONArray;

public class Country {

    private final String name;
    private final String capital;
    private final String flag;
    private final String region;
    private String abbreviation;


    public Country(String name,String flag, String capital,String region) {
        this.name = name;
        this.flag = flag;
        this.capital = capital;
        this.region = region;
        this.abbreviation = abbreviation;

    }

    public String getName() {
        return name;
    }


    public String getFlag() {
        return flag;
    }


    public String getCapital() {
        return capital;
    }


    public String getRegion() {
        return region;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
