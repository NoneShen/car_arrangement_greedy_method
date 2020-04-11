package com.company;


import static com.company.TripSchedulingAlgorithm.calculate_distance;

public class Trip {

    private Point src;
    private Point Dest;
    private int startTime;
    private int revenue;
    private int cost_trip;

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    private int end_time;
    private int waiting_time;

    public Point getSrc() {
        return src;
    }

    public void setSrc(Point src) {
        this.src = src;
    }

    public Point getDest() {
        return Dest;
    }

    public void setDest(Point dest) {
        Dest = dest;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getCost_trip() {
        return cost_trip;
    }

    public void setCost_trip(int cost_trip) {
        this.cost_trip = cost_trip;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public void calculate_end_time(int waiting_time) {
        this.end_time = cost_trip+waiting_time+startTime;
        this.waiting_time=waiting_time;
    }


    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    Trip(){

    }

    Trip(Point src, Point Dest, int startTime){
        this.src=src;
        this.Dest=Dest;
        this.startTime=startTime;
        cost_trip=calculate_distance(src,Dest);
        revenue=calculate_distance(src,Dest);
        end_time=0;
        waiting_time=0;
    }

}
