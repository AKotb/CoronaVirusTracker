package org.narss.covid19.model;

/**
 * Created by Amira on 4/18/2020.
 */
public class Hospital {

    /* Databse Schema
    `no.`	VARCHAR ( 80 ),
	`hospital_name_en`	VARCHAR ( 80 ),
	`hospital_name_ar`	VARCHAR ( 80 ),
	`governorat_en`	VARCHAR ( 80 ),
	`governorate_ar`	VARCHAR ( 80 ),
	`latitude`	FLOAT,
	`longitude`	FLOAT,
	`beds`	INTEGER,
	`icus`	INTEGER,
	`ventilators`	INTEGER,
	`doctors`	INTEGER,
	`nurssing_staff`	INTEGER,
	`total_cases`	INTEGER,
	`active_cases`	INTEGER,
	`mild_cases`	INTEGER,
	`critical_cases`	INTEGER,
	`closed_cases`	INTEGER,
	`recovered_cases`	INTEGER,
	`death_cases`	INTEGER
    */
    private String id;
    private String name;
    private String governorate;
    private double lat;
    private double lon;
    private String nameAr;
    private String governorateAr;
    private int beds;
    private int icus;
    private int ventilators;
    private int doctors;
    private int nurssingStaff;
    private int totalCases;
    private int activeCases;
    private int mildCases;
    private int criticalCases;
    private int closedCases;
    private int recoveredCases;
    private int deathCases;

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
        this.nameAr = "";
        this.governorateAr = "";
        this.beds = 0;
        this.icus = 0;
        this.ventilators = 0;
        this.doctors = 0;
        this.nurssingStaff = 0;
        this.totalCases = 0;
        this.activeCases = 0;
        this.mildCases = 0;
        this.criticalCases = 0;
        this.closedCases = 0;
        this.recoveredCases = 0;
        this.deathCases = 0;
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

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getGovernorateAr() {
        return governorateAr;
    }

    public void setGovernorateAr(String governorateAr) {
        this.governorateAr = governorateAr;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getIcus() {
        return icus;
    }

    public void setIcus(int icus) {
        this.icus = icus;
    }

    public int getVentilators() {
        return ventilators;
    }

    public void setVentilators(int ventilators) {
        this.ventilators = ventilators;
    }

    public int getDoctors() {
        return doctors;
    }

    public void setDoctors(int doctors) {
        this.doctors = doctors;
    }

    public int getNurssingStaff() {
        return nurssingStaff;
    }

    public void setNurssingStaff(int nurssingStaff) {
        this.nurssingStaff = nurssingStaff;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getMildCases() {
        return mildCases;
    }

    public void setMildCases(int mildCases) {
        this.mildCases = mildCases;
    }

    public int getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(int criticalCases) {
        this.criticalCases = criticalCases;
    }

    public int getClosedCases() {
        return closedCases;
    }

    public void setClosedCases(int closedCases) {
        this.closedCases = closedCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(int recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public int getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(int deathCases) {
        this.deathCases = deathCases;
    }
}
