package com.company;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import static com.company.AdditionalFunctions.*;

public class GreedySchedulingAlgorithmPlusWaitingTime_version1 extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip, int max_waiting_time){
        int size=car_list.car_list.size();//number of cars
        double[] a=new double[size];//distance between each car and the customer
        int temp=0;
        int sign=0;
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            a[i]=calculate_distance(car.position,trip.getSrc())/car.speed;
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
        //   Write_content("output.txt","Can not serve this customer, because there is not a car available in "+ max_waiting_time+"s.");
            trip.setCar_assigned_id(-1);
            return -1;
        }
        trip.setTimestamp_picked_up(a[temp],0);
        trip.calculate_end_timestamp(car_list,index[temp]);
        trip.calculate_penalty();
        trip.calculate_cost(a[temp],car_list.car_list.get(index[temp]));
        car_list.car_list.get(index[temp]).setTime_stamp_arrive_des(trip.getEnd_time());
        car_list.car_list.get(index[temp]).setPosition(trip.getDest());
        return index[temp];
    }
    static void simulate(Car_list car_list, Customer_demand demand, int n, int max_waiting_time,int k_i) throws IOException {
        Trip trip_next=new Trip();
        int k=car_list.car_list.size();
        int index=0;//the index of customer
        double total_cost=0;
        int total_revenue=0;
        double Penalty=0;
        int Count=0;
     //  Write_content("output.txt","Version 1 :*************************************************************************************************");
        Write_content("output.csv",",");
        for(int time=0;time<3600;time++){
            if(index<n) {
                trip_next=demand.trip_list.get(index);
            }
            if(trip_next.getStartTime()==time){
                index++;
                int car_index=TripSchedule(car_list,trip_next,max_waiting_time);
                if(car_index==-1){
                    //     Write_content("output.txt","Lost one customer");
                }else{
                    total_cost += trip_next.getCost_trip();
                    total_revenue += trip_next.getRevenue();
                    Penalty+=trip_next.getCustomer_penalty();
                    Count++;
                }
            }
        }
        double Average_revenue=total_revenue*2.5/Count;
        BigDecimal b   =   new BigDecimal(total_revenue*2.5-total_cost*0.5-Penalty*Average_revenue-(n-Count)*k_i);
        double Objective=b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        Write_content("output.csv",Objective+"");

    }
}

