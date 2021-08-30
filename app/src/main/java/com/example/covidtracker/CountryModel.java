package com.example.covidtracker;

public class CountryModel {
    String flag,country, cases, death, recovered, critical, active, todayCases, todayDeath, todayRecovered;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String death, String recovered, String critical, String active, String todayCases, String todayDeath, String todayRecovered) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.death = death;
        this.recovered = recovered;
        this.critical = critical;
        this.active = active;
        this.todayCases = todayCases;
        this.todayDeath = todayDeath;
        this.todayRecovered = todayRecovered;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }
}