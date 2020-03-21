package com.company;

public class Car extends Point {
    public Car(int x, int y, int time_second) {
        super(x, y, time_second);
    }

    public int getPrefered_zone() {
        return prefered_zone;
    }

    public void setPrefered_zone(int prefered_zone) {
        this.prefered_zone = prefered_zone;
    }

    private int prefered_zone;

}
