package org.narss.covid19.model;

/**
 * Created by Amira on 4/18/2020.
 */
public class Hospital {

    private String id;
    private String name;
    private String governorate;
    private double lat;
    private double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospital hospital = (Hospital) o;

        if (Double.compare(hospital.lat, lat) != 0) return false;
        if (Double.compare(hospital.lon, lon) != 0) return false;
        if (!id.equals(hospital.id)) return false;
        if (!name.equals(hospital.name)) return false;
        return governorate.equals(hospital.governorate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + governorate.hashCode();
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Hospital() {
        this.id = "";
        this.name = "";
        this.governorate = "";
        this.lat = 0.0;
        this.lon = 0.0;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
