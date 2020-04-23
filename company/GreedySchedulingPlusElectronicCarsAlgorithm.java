//package com.company;
//import java.util.HashSet;
//import java.util.Vector;
//
//
//
//public class GreedySchedulingPlusElectronicCarsAlgorithm extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm {
//
//    public static int TripSchedule(Car_list car_list, Trip trip){
//        int size=car_list.car_list.size();//number of cars
//        int[] a=new int[size];//distance between each car and the customer
//        HashSet<Integer> set=new HashSet<Integer>();
//        int distance=trip.getCost_trip();
//        int[] index = get_index(car_list,trip, size, a, set);
//        if(distance>500){
//            int i=0;
//            while(index[i]<30){
//                i++;
//            }
//            return car_point_set_i(car_list,trip, a, index, set, i);
//        }else {
//            return car_point_set_first(car_list,trip, a, index, set);
//        }
//    }
//
//    static void simulate( Car_list car_list, Customer_demand demand, int n){
//        int  total_cost=0;
//        int index=0;
//        Trip trip_next=new Trip();
//        System.out.println("Version 4 :*************************************************************************************************");
//        for(int time=0;time<3600;time++){
//            if(index<n) {
//                trip_next=demand.trip_list.get(index);
//            }
//
//            if(trip_next.getStartTime()==time){
//                index++;
//                int car_index=TripSchedule(car_list,trip_next);
//                total_cost+=car_list.car_list.get(car_index).getTime_stamp_arrive_des()-time;
//            }
//        }
//        System.out.println("Total Cost: "+total_cost);
//        System.out.println("*************************************************************************************************");
//    }
//
//}
//
