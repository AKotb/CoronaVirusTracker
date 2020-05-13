package org.narss.covid19.model;

/**
 * created by akotb on 5/5/2020
 */

public class Laboratory {

    private int labId;
    private String labNameEn;
    private String labNameAr;
    private String labGovernorate;
    private double labLat;
    private double labLon;
    private int labStartTime;
    private int labEndTime;
    private String labOffDays;
    private int labDailyTestsNumber;
    private int labTotalPerformedTests;
    private int labNegativeTestsNumber;
    private int labPositiveTestsNumber;


    public Laboratory() {
    }

    public Laboratory(int labId, String labNameEn, String labNameAr, String labGovernorate, double labLat, double labLon, int labStartTime, int labEndTime, String labOffDays, int labDailyTestsNumber, int labTotalPerformedTests, int labNegativeTestsNumber, int labPositiveTestsNumber) {
        this.labId = labId;
        this.labNameEn = labNameEn;
        this.labNameAr = labNameAr;
        this.labGovernorate = labGovernorate;
        this.labLat = labLat;
        this.labLon = labLon;
        this.labStartTime = labStartTime;
        this.labEndTime = labEndTime;
        this.labOffDays = labOffDays;
        this.labDailyTestsNumber = labDailyTestsNumber;
        this.labTotalPerformedTests = labTotalPerformedTests;
        this.labNegativeTestsNumber = labNegativeTestsNumber;
        this.labPositiveTestsNumber = labPositiveTestsNumber;
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

    public double getLabLat() {
        return labLat;
    }

    public void setLabLat(double labLat) {
        this.labLat = labLat;
    }

    public double getLabLon() {
        return labLon;
    }

    public void setLabLon(double labLon) {
        this.labLon = labLon;
    }

    public int getLabStartTime() {
        return labStartTime;
    }

    public void setLabStartTime(int labStartTime) {
        this.labStartTime = labStartTime;
    }

    public int getLabEndTime() {
        return labEndTime;
    }

    public void setLabEndTime(int labEndTime) {
        this.labEndTime = labEndTime;
    }

    public String getLabOffDays() {
        return labOffDays;
    }

    public void setLabOffDays(String labOffDays) {
        this.labOffDays = labOffDays;
    }

    public int getLabDailyTestsNumber() {
        return labDailyTestsNumber;
    }

    public void setLabDailyTestsNumber(int labDailyTestsNumber) {
        this.labDailyTestsNumber = labDailyTestsNumber;
    }

    public int getLabTotalPerformedTests() {
        return labTotalPerformedTests;
    }

    public void setLabTotalPerformedTests(int labTotalPerformedTests) {
        this.labTotalPerformedTests = labTotalPerformedTests;
    }

    public int getLabNegativeTestsNumber() {
        return labNegativeTestsNumber;
    }

    public void setLabNegativeTestsNumber(int labNegativeTestsNumber) {
        this.labNegativeTestsNumber = labNegativeTestsNumber;
    }

    public int getLabPositiveTestsNumber() {
        return labPositiveTestsNumber;
    }

    public void setLabPositiveTestsNumber(int labPositiveTestsNumber) {
        this.labPositiveTestsNumber = labPositiveTestsNumber;
    }
}
