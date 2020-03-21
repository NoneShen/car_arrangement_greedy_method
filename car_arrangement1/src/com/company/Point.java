package com.company;

public class Point {
    private int x;
    private int y;
    private int time_second;
    public Point(int x, int y,int time_second) {
        super();
        this.x = x;
        this.y = y;
        this.time_second=time_second;
    }
    public int getX() {
        return x;
    }
    public int get_time() {
        return time_second;
    }
    public void setTime_second(int time_second) {
        this.time_second = time_second;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y+"time="+time_second;
    }
}

