package com.exam.country_app;

import org.json.JSONArray;

public class Country {

    private final String name;
    private final String capital;
    private final String flag;
    private final String region;
    private final String abbreviation;
    private final JSONArray calling_codes;
    private final int population;
    private final Currency[] currency;
    private final double[] latlng;
    private final String[] languages;
    private final JSONArray borders;


    public Country(String name, String flag, String capital, String region, String abbreviation, JSONArray calling_codes, int population, Currency[] currency, double[] latlng, String[] languages, JSONArray borders) {
        this.name = name;
        this.flag = flag;
        this.capital = capital;
        this.region = region;
        this.abbreviation = abbreviation;
        this.calling_codes = calling_codes;
        this.population = population;
        this.currency = currency;
        this.latlng = latlng;
        this.languages = languages;
        this.borders = borders;
    }

    public JSONArray getBorders() {
        return borders;
    }

    public String[] getLanguages() {
        return languages;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public Currency[] getCurrency() {
        return currency;
    }

    public int getPopulation() {
        return population;
    }

    public JSONArray getCalling_codes() {
        return calling_codes;
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
