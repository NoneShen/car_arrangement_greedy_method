package com.company;

public class Car {
    public Point position;
    public int time_stamp_arrive_des;
    Car(){

    }
    Car(Point position){
        this.position=position;
        time_stamp_arrive_des=0;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getTime_stamp_arrive_des() {
        return time_stamp_arrive_des;
    }

    public void setTime_stamp_arrive_des(int time_stamp_arrive_des) {
        this.time_stamp_arrive_des = time_stamp_arrive_des;
    }
}
