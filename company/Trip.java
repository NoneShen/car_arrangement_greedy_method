package com.company;


import static com.company.TripSchedulingAlgorithm.calculate_distance;

public class Trip {

    private Point src;
    private Point Dest;
    private int startTime;
    private int revenue;
    private double cost_trip;
    private double Customer_satisfaction_penalty;

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    private double end_time;
    private double waiting_time;

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

    public double getCost_trip() {
        return cost_trip;
    }

    public void setCost_trip(int cost_trip) {
        this.cost_trip = cost_trip;
    }

    public double getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public void calculate_end_time(double waiting_time, Car car) {
        this.end_time =revenue/car.speed+waiting_time+startTime;
        this.waiting_time=waiting_time;
        if(waiting_time<600){
            Customer_satisfaction_penalty=0;
        }if(waiting_time>=600&&waiting_time<1200){
            Customer_satisfaction_penalty=waiting_time/60;
        }else{
            Customer_satisfaction_penalty=waiting_time*2/60;
        }
    }
    public void calculate_cost(double cost_on_way, Car car) {
        this.cost_trip=cost_on_way+revenue/car.speed;
    }


    public double getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    public double getCustomer_penalty() {
        return Customer_satisfaction_penalty;
    }

    Trip(){

    }

    Trip(Point src, Point Dest, int startTime){
        this.src=src;
        this.Dest=Dest;
        this.startTime=startTime;
        cost_trip=0;
        revenue=calculate_distance(src,Dest);
        end_time=0;
        waiting_time=0;
    }

}
