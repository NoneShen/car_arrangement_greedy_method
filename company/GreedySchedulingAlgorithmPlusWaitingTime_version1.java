package com.company;

import java.util.Vector;

import static com.company.AdditionalFunctions.sort_array;

public class GreedySchedulingAlgorithmPlusWaitingTime_version1 extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip, int max_waiting_time){
        int size=car_list.car_list.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int cost_on_way=0;
        int waiting_time=0;
        int temp=0;
        int sign=0;
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            a[i]=calculate_distance(car.position,trip.getSrc());
        }
        int[] index=sort_array(a);

        //check if this car is carrying a customer
        while(temp<size&&sign==0){
            if(a[temp]<max_waiting_time&&car_list.car_list.get(index[temp]).getTime_stamp_arrive_des()<=trip.getStartTime()){
                sign=1;
                temp--;
            }
            temp++;
        }
        if(sign==0){
            System.out.println("Can not serve this customer, because there is not a car available in "+ max_waiting_time+"s.");
            return -1;
        }
        trip.calculate_end_time(a[temp]);
        trip.calculate_cost(a[temp]);
        car_list.car_list.get(index[temp]).setTime_stamp_arrive_des(trip.getEnd_time());
        car_list.car_list.get(index[temp]).setPosition(trip.getDest());
        return index[temp];
    }
    static void simulate(Car_list car_list, Customer_demand demand, int n, int max_waiting_time){
        Trip trip_next=new Trip();
        int index=0;//the index of customer
        int total_cost=0;
        int total_revenue=0;
        int Count=0;
        System.out.println("Version 1 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                trip_next=demand.trip_list.get(index);
            }
            if(trip_next.getStartTime()==time){
                index++;
                int car_index=TripSchedule(car_list,trip_next,max_waiting_time);
                if(car_index==-1){
                    //      System.out.println("Lost one customer");
                }else{
                    total_cost += trip_next.getCost_trip();
                    total_revenue += trip_next.getRevenue();
                    Count++;
                }
            }
        }
        System.out.println("Total Cost: "+total_cost);
        System.out.println("Total revenue: "+total_revenue);
        System.out.println("Total customer: "+Count);
        System.out.println("*************************************************************************************************");
    }
}

