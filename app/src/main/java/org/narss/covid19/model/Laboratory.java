package org.narss.covid19.model;

/**
 * created by akotb on 5/5/2020
 */

public class Laboratory {

    private int labId;
    private String labNameEn;
    private String labNameAr;
    private String labGovernorate;
    private String labLat;
    private String labLon;


    public Laboratory() {

    }

    public Laboratory(int labId, String labNameEn, String labNameAr, String labGovernorate, String labLat, String labLon) {
        this.labId = labId;
        this.labNameEn = labNameEn;
        this.labNameAr = labNameAr;
        this.labGovernorate = labGovernorate;
        this.labLat = labLat;
        this.labLon = labLon;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabNameEn() {
        return labNameEn;
    }

    public void setLabNameEn(String labNameEn) {
        this.labNameEn = labNameEn;
    }

    public String getLabNameAr() {
        return labNameAr;
    }

    public void setLabNameAr(String labNameAr) {
        this.labNameAr = labNameAr;
    }

    public String getLabGovernorate() {
        return labGovernorate;
    }

    public void setLabGovernorate(String labGovernorate) {
        this.labGovernorate = labGovernorate;
    }

    public String getLabLat() {
        return labLat;
    }

    public void setLabLat(String labLat) {
        this.labLat = labLat;
    }

    public String getLabLon() {
        return labLon;
    }

    public void setLabLon(String labLon) {
        this.labLon = labLon;
    }
}
