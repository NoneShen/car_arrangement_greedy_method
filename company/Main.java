package com.company;
import java.util.*;

import static com.company.AdditionalFunctions.*;
import static com.company.TripSchedulingAlgorithm.*;

public class Main {
    public static void main(String[] args) {
        int k = 1;//car
        //0-29 electronic car 30-99 normal car
        int n = 10;//customer
        //Create n random customers and their destination
        Customer_demand demand=new Customer_demand();
        Customer_demand demand1=new Customer_demand();
        List<Integer> time_list=randomList(1,3600,n);
        for (int j = 0; j < n; j++) {
            Point p = new Point(random_num(), random_num());
            Point p1 = new Point(random_num(), random_num());
            Trip t=new Trip(p,p1,time_list.get(j));
            Trip t1=new Trip(p,p1,time_list.get(j));
            demand.trip_list.add(t);
            demand1.trip_list.add(t1);
        }
        Car_list car_list=new Car_list();
        Car_list car_list1=new Car_list();
        for(int i=0;i<k;i++){
            Point p = new Point(random_num(), random_num());
            Car car=new Car(p);
            Car car1=new Car(p);
            car_list.car_list.add(car);
            car_list1.car_list.add(car1);
        }
      //  GreedySchedulingAlgorithm.simulate(car_list,demand,n);
        // GreedySchedulingPlusAlgorithm.simulate(car_list,demand,n);
       // GreedySchedulingPlusPreferZoneAlgorithm instance1=new GreedySchedulingPlusPreferZoneAlgorithm(k);
       //  GreedySchedulingPlusPreferZoneAlgorithm.simulate(car_list1,demand,n);
        //GreedySchedulingPlusElectronicCarsAlgorithm.simulate(car_list,demand,n);

        GreedySchedulingPlusWaitingTimeAlgorithm.simulate(car_list,demand,n,1800);


    }
}
