package com.company;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static com.company.AdditionalFunctions.sort_array;

public class GreedySchedulingPlusWaitingTimeAlgorithm extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip,int max_waiting_time){
        int size=car_list.car_list.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int time_from_car_to_customer=0;
        int time_remain=0;
        int sign=0;
        int temp=0;
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            a[i]=calculate_distance(car.position,trip.getSrc())+Math.max(car.getTime_stamp_arrive_des()-trip.getStartTime()-max_waiting_time,0);
        }
        int[] index=sort_array(a);

        while(sign==0&&temp<size){
            if(a[index[temp]]<max_waiting_time){
                sign=1;
                temp--;
            }
            temp++;
        }
        if(sign==0){
            System.out.println("Can not serve this customer, because there is not a car available in "+ max_waiting_time+"s.");
            return -1;
        }
        time_from_car_to_customer=calculate_distance(car_list.car_list.get(index[temp]).position,trip.getSrc());
        time_remain= Math.max(car_list.car_list.get(index[temp]).getTime_stamp_arrive_des()-trip.getStartTime(),0);
        trip.calculate_end_time(time_from_car_to_customer+time_remain);
        car_list.car_list.get(index[temp]).setTime_stamp_arrive_des(trip.getEnd_time());
        car_list.car_list.get(index[temp]).setPosition(trip.getDest());
      //  System.out.println(index[temp]);
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
                    System.out.println("Lost one customer");
                }else{
                    total_cost+=car_list.car_list.get(car_index).getTime_stamp_arrive_des()-time;
                    total_revenue+= trip_next.getRevenue();
                    Count++;
                }
            }
        }
        System.out.println("Total Cost: "+total_cost);
        System.out.println("Total revenue: "+total_revenue);
        System.out.println("Total customer: "+Count);
        System.out.println("Average Cost:" + (total_cost-total_revenue)/Count);
        System.out.println("*************************************************************************************************");
    }
}
