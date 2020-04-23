package com.company;

public class Car {
    public Point position;
    public double time_stamp_arrive_des;
    public double speed;
    public int fuel_consumption;
    Car(){

    }
    Car(Point position){
        this.position=position;
        time_stamp_arrive_des=0;
        speed=1.0;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getTime_stamp_arrive_des() {
        return time_stamp_arrive_des;
    }

    public void setTime_stamp_arrive_des(double time_stamp_arrive_des) {
        this.time_stamp_arrive_des = time_stamp_arrive_des;
    }
}
