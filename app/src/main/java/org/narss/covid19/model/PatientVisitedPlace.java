package org.narss.covid19.model;

public class PatientVisitedPlace {
    private String patientID;
    private int locationID;
    private String day;
    private int hour;
    private float lat;
    private float lon;

    public PatientVisitedPlace()
    {
        this.patientID = "";
        this.locationID = 0;
        this.day = "";
        this.hour = 0;
        this.lat = 0.0F;
        this.lon = 0.0F;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
