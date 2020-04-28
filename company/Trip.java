package com.company;


import static com.company.TripSchedulingAlgorithm.calculate_distance;

public class Trip {

    public int trip_id;
    public int shared_with;
    private Point src;
    private Point Dest;
    private int startTime;
    private int revenue;
    private double cost_trip;
    private double Customer_satisfaction_penalty;

    private double timestamp_picked_up;
    private int car_assigned_id;
    private double end_time;
    private double waiting_time;

    public double getPick_up_waiting_time() {
        return pick_up_waiting_time;
    }

    private double pick_up_waiting_time;

    public int getCar_assigned_id() {
        return car_assigned_id;
    }

    public void setCar_assigned_id(int car_assigned_id) {
        this.car_assigned_id = car_assigned_id;
    }

    public double getTimestamp_picked_up() {
        return timestamp_picked_up;
    }

    public void setTimestamp_picked_up(double pick_up_waiting_time,double last_trip_remain_time) {
        this.pick_up_waiting_time=pick_up_waiting_time;
        this.waiting_time=pick_up_waiting_time+last_trip_remain_time;
        this.timestamp_picked_up = waiting_time+startTime;
    }
    public void calculate_end_timestamp(Car_list car_list, int car_assigned_id){
        this.end_time=timestamp_picked_up+revenue/car_list.car_list.get(car_assigned_id).speed;
        this.car_assigned_id=car_assigned_id;
    }


    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }



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

    public void setCost_trip(double cost_trip) {
        this.cost_trip = cost_trip;
    }

    public double getEnd_time() {
        return end_time;
    }

    public void setEnd_time(double end_time) {
        this.end_time = end_time;
    }

    public void calculate_penalty() {
        if(waiting_time<600){
            Customer_satisfaction_penalty=0;
        }
        else if(waiting_time>=600&&waiting_time<1200){
            Customer_satisfaction_penalty=waiting_time/60;
        }
        else{
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

    Trip(Point src, Point Dest, int startTime,int trip_id){
        this.src=src;
        this.Dest=Dest;
        this.startTime=startTime;
        cost_trip=0;
        revenue=calculate_distance(src,Dest);
        end_time=0;
        waiting_time=0;
        shared_with=-1;
        car_assigned_id=0;
        this.trip_id=trip_id;
    }

}
