package com.company;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static com.company.AdditionalFunctions.sort_array;

public class GreedySchedulingAlgorithmPlusWaitingTime_version2 extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Trip trip,int max_waiting_time){
        int size=car_list.car_list.size();//number of cars
        double[] a=new double[size];//distance between each car and the customer
        double cost_on_way=0;
        double last_trip_remain_time=0;
        int temp=0;
        int sign=0;
        for(int i=0;i<size;i++){
            Car car=car_list.car_list.get(i);
            cost_on_way=calculate_distance(car.position,trip.getSrc())/car.speed;
            a[i]=cost_on_way;
        }
        int[] index=sort_array(a);
        while(temp<size&&sign==0){
            last_trip_remain_time=Math.max(car_list.car_list.get(index[temp]).getTime_stamp_arrive_des()-trip.getStartTime(),0);
            if(last_trip_remain_time+a[temp]<max_waiting_time){
                sign=1;
                temp--;
            }
            temp++;
        }
        if(sign==0){
           // System.out.println("Can not serve this customer, because there is not a car available in "+ max_waiting_time+"s.");
            return -1;
        }
        trip.setTimestamp_picked_up(a[temp],last_trip_remain_time);
        trip.calculate_end_timestamp(car_list,index[temp]);
        trip.calculate_penalty();
        trip.calculate_cost(a[temp],car_list.car_list.get(index[temp]));
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
        int Penalty=0;
        int Count=0;
        System.out.println("Version 2 :*************************************************************************************************");
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
