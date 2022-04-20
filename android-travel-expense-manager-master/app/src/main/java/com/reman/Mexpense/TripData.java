package com.reman.Mexpense;

public class TripData {
    String TripId;
    String Title;
    String source;
    String destination;
    String Startdate;
    String todate;
    Double ApprovedBudget;
    Double Balance;
    int slct;

    public TripData() {
    }

    public String getTripId() {
        return TripId;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartdate() {
        return Startdate;
    }

    public void setStartdate(String startdate) {
        Startdate = startdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public Double getApprovedBudget() {
        return ApprovedBudget;
    }

    public void setApprovedBudget(Double approvedBudget) {
        ApprovedBudget = approvedBudget;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public int getSlct() {
        return slct;
    }

    public void setSlct(int slct) {
        this.slct = slct;
    }

    public TripData(String tripId, String title, String source, String destination, String startdate, String todate, Double approvedBudget, Double balance, int slct) {
        TripId = tripId;
        Title = title;
        this.source = source;
        this.destination = destination;
        Startdate = startdate;
        this.todate = todate;
        ApprovedBudget = approvedBudget;
        Balance = balance;
        this.slct = slct;
    }
}
