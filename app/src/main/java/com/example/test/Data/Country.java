package com.example.test.Data;

import java.io.Serializable;
import java.util.Arrays;

public class Country implements Serializable {


    private String name;
    private String[] topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
    private String[] altSpellings;
    private String region;
    private String subregion;
    private String population;
    private String[] timezones;
    private String[] currencies;
    private String[] languages;






    public String getTopLevelDomains() {
        String s = "";
        for (String a : topLevelDomain) {
            s += a + "\t";
        }
        return s;
    }

    public String getSpellings() {
        String s = "";
        int count = 0;
        for (String a : altSpellings) {

            s += a + ". \t ";
            count++;
            if (count == 4) {
                return s;
            }
        }
        return s;
    }

    public String getTimezonesString() {
        String s = "";
        for (String a : timezones) {
            s += a + "\t";
        }
        return s;
    }

    public String getCurrenciesString() {
        String s = "";
        for (String a : currencies) {
            s += a + "\t";
        }
        return s;
    }

    public String getLanguagesString() {
        String s = "";
        for (String a : languages) {
            s += a + "\t";
        }
        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }



    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", topLevelDomain=" + Arrays.toString(topLevelDomain) +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + Arrays.toString(altSpellings) +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", timezones=" + Arrays.toString(timezones) +
                ", currencies=" + Arrays.toString(currencies) +
                ", languages=" + Arrays.toString(languages) +
                '}';
    }
}