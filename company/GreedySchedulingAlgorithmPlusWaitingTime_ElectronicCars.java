package com.company;
import java.util.HashSet;
import java.util.Vector;

import static com.company.AdditionalFunctions.sort_array;


public class GreedySchedulingAlgorithmPlusWaitingTime_ElectronicCars extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip, int max_waiting_time){
        int size=car_list.car_list.size();//number of cars
        double[] a=new double[size];//distance between each car and the customer
        double cost_on_way=0.0;
        double waiting_time=0;
        int temp=0;
        int sign=0;
        int distance=trip.getRevenue();
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            cost_on_way=calculate_distance(car.position,trip.getSrc())/car.speed;
            if(distance/car.speed+cost_on_way>500&&i<0.3*size){
                a[i]=0;
                continue;
            }
            a[i]=cost_on_way;
        }
        int[] index=sort_array(a);
        while(temp<size&&sign==0){
            waiting_time=Math.max(car_list.car_list.get(index[temp]).getTime_stamp_arrive_des()-trip.getStartTime(),0);
            if(a[temp]!=0&&waiting_time+a[temp]<max_waiting_time){
                sign=1;
                temp--;
            }
            temp++;
        }
        if(sign==0){
    //        System.out.println("Can not serve this customer, because there is not a car available in "+ max_waiting_time+"s.");
            return -1;
        }
        trip.calculate_end_time(waiting_time+a[temp],car_list.car_list.get(index[temp]));
        trip.calculate_cost(a[temp],car_list.car_list.get(index[temp]));
        car_list.car_list.get(index[temp]).setTime_stamp_arrive_des(trip.getEnd_time());
        car_list.car_list.get(index[temp]).setPosition(trip.getDest());
        //  System.out.println(index[temp]);
        return index[temp];
    }

    static void simulate( Car_list car_list, Customer_demand demand, int n, int max_waiting_time){
        double total_cost=0;
        int index=0;//the index of customer
        int total_revenue=0;
        int Count=0;
        int Penalty=0;
        Trip trip_next=new Trip();
        System.out.println("Version 4 :*************************************************************************************************");
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
                    Penalty+=trip_next.getCustomer_penalty();
                    Count++;


                }
            }
        }
        System.out.println("Total Cost: "+total_cost*0.5);
        System.out.println("Total revenue: "+total_revenue*2.5);
        System.out.println("Total customer: "+Count);
        System.out.println("Acceptance Rate "+ (double)Count/(double)n*100+ "%");
        System.out.println("Main Objective "+total_revenue*2.5+"-"+Penalty+"-"+(n-Count)+"*k");
        System.out.println("*************************************************************************************************");
    }

}
