package com.clinic.frontclinic.domain;

public class CovidInfo {
    private int confirmed;
    private int recovered;
    private int deaths;
    private String lastUpdate;

    public CovidInfo() {
    }

    public CovidInfo(int confirmed, int recovered, int deaths, String lastUpdate) {
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.lastUpdate = lastUpdate;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
