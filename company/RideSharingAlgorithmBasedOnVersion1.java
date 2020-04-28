package com.company;

import static com.company.AdditionalFunctions.sort_array;

public class RideSharingAlgorithmBasedOnVersion1 extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {

    public static int TripSchedule(Car_list car_list, Customer_demand demand,int trip_index, int max_waiting_time){
        Trip trip=demand.trip_list.get(trip_index);
        Trip trip_temp=new Trip();
        double Over_head1=0;
        double Over_head2=0;
        double Over_head1_target=1.1;
        double Over_head2_target=1.2;
        int Trip_to_be_shared=-1;
        int distance_from_A_to_C_passing_B=0;
        int distance_from_B_to_D_passing_C=0;
        int distance_from_A_to_C=0;
        int distance_from_B_to_D=0;
        for(int i=0;i<trip_index;i++){
           trip_temp=demand.trip_list.get(i);
            if(trip_temp.getCar_assigned_id()!=-1&&trip_temp.getTimestamp_picked_up()>trip.getStartTime()&&trip_temp.shared_with==-1){
                distance_from_A_to_C_passing_B=calculate_distance(trip_temp.getSrc(),trip.getSrc(),trip_temp.getDest());
                distance_from_B_to_D_passing_C=calculate_distance(trip.getSrc(),trip_temp.getDest(),trip.getDest());
                distance_from_A_to_C=calculate_distance(trip_temp.getSrc(),trip_temp.getDest());
                distance_from_B_to_D=calculate_distance(trip.getSrc(),trip.getDest());
                Over_head1=(double) distance_from_A_to_C_passing_B/distance_from_A_to_C;
                Over_head2=(double) distance_from_B_to_D_passing_C/distance_from_B_to_D;
                if(Over_head1<=1.1&&Over_head2<=1.2&&Over_head1+Over_head2<Over_head1_target+Over_head2_target){
                    Over_head1_target=Over_head1;
                    Over_head2_target=Over_head2;
                    Trip_to_be_shared=i;
                }
            }
        }
        if(Trip_to_be_shared!=-1){
            trip_temp=demand.trip_list.get(Trip_to_be_shared);
            trip.setTimestamp_picked_up(trip_temp.getTimestamp_picked_up()-trip.getStartTime()+calculate_distance(trip_temp.getSrc(),trip.getSrc()),0);
            trip.setCar_assigned_id(trip_temp.getCar_assigned_id());
            Car car =car_list.car_list.get(trip.getCar_assigned_id());
            trip.shared_with=trip_temp.trip_id;
            trip_temp.shared_with=trip.trip_id;
            distance_from_A_to_C_passing_B=calculate_distance(trip_temp.getSrc(),trip.getSrc(),trip_temp.getDest());
           double distance_pick_up_A=trip_temp.getPick_up_waiting_time()*car.speed;
           double distance_from_C_to_D=calculate_distance(trip_temp.getDest(),trip.getDest());
           double Cost_divide_by_two=(distance_pick_up_A+distance_from_C_to_D+distance_from_A_to_C_passing_B)/2;
           trip.setCost_trip(Cost_divide_by_two);
           trip_temp.setCost_trip(Cost_divide_by_two);
           trip_temp.setEnd_time((distance_from_A_to_C_passing_B+distance_pick_up_A)/car.speed+trip_temp.getStartTime());
           trip.setEnd_time(trip_temp.getEnd_time()+distance_from_C_to_D/car.speed);
           car.setTime_stamp_arrive_des(trip.getEnd_time());
           car.setPosition(trip.getDest());
            return trip.getCar_assigned_id();
        }else {
          return GreedySchedulingAlgorithmPlusWaitingTime_version1.TripSchedule(car_list,trip,max_waiting_time);
        }
    }
    static void simulate(Car_list car_list, Customer_demand demand, int n, int max_waiting_time){
        Trip trip_next=new Trip();
        int index=0;//the index of customer
        double total_cost=0;
        int total_revenue=0;
        int Penalty=0;
        int Count=0;
        System.out.println("Version 1 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                trip_next=demand.trip_list.get(index);
            }
            if(trip_next.getStartTime()==time){
                int car_index=TripSchedule(car_list,demand,index,max_waiting_time);
                index++;
                if(car_index==-1){
                    //      System.out.println("Lost one customer");
                }
            }
        }
        for(int i=0;i<n;i++){
            trip_next=demand.trip_list.get(i);
            if(trip_next.getCar_assigned_id()!=-1){
                total_cost+=trip_next.getCost_trip();
                total_revenue+=trip_next.getRevenue();
                Penalty+=trip_next.getCustomer_penalty();
                Count++;
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

