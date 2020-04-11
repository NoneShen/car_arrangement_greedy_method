package com.company;

import java.util.Vector;

import static com.company.AdditionalFunctions.sort_array;

public class GreedySchedulingAlgorithm extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip){
        int size=car_list.car_list.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int[] index;
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            a[i]=calculate_distance(car.position,trip.getSrc());
        }
        index=sort_array(a);
        int temp=0;
        //check if this car is carrying a customer
        while(car_list.car_list.get(index[temp]).getTime_stamp_arrive_des()>trip.getStartTime()){
            temp++;
        }
        int time_from_car_to_customer=calculate_distance( car_list.car_list.get(index[temp]).getPosition(),trip.getSrc());
        //  System.out.println(Des.get_time()+" "+time_from_car_to_customer+"  "+time);
        trip.calculate_end_time(time_from_car_to_customer);
        car_list.car_list.get(index[temp]).setTime_stamp_arrive_des(trip.getEnd_time());
        car_list.car_list.get(index[temp]).setPosition(trip.getDest());
        return index[temp];
    }
    static void simulate(Car_list car_list, Customer_demand demand, int n){
        Trip trip_next=new Trip();
        int index=0;//the index of customer
        int total_cost=0;
        System.out.println("Version 1 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                trip_next=demand.trip_list.get(index);
            }
            if(trip_next.getStartTime()==time){
                index++;
                int car_index=TripSchedule(car_list,trip_next);
                total_cost+=car_list.car_list.get(car_index).getTime_stamp_arrive_des()-time;
            }
        }
        System.out.println("Total Cost: "+total_cost);
        System.out.println("*************************************************************************************************");
    }
}
