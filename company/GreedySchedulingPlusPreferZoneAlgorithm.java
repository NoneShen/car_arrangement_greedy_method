//package com.company;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.Vector;
//
//import static com.company.AdditionalFunctions.randomArray;
//import static com.company.AdditionalFunctions.random_set;
//
//
//public class GreedySchedulingPlusPreferZoneAlgorithm extends TripSchedulingAlgorithm implements TripSchedulingAlgorithm.algorithm{
//      public int k;
//    public static Vector<Set<Integer>> car_pref_zone;
//    GreedySchedulingPlusPreferZoneAlgorithm(int a){
//        k=a;
//        car_pref_zone=generate_prefer_zone(k);
//    }
//    //先把顾客的zone算出来，还是greedy安排，但是如果顾客的目的地不在preferzone里面，不安排这辆车
//     public static int TripSchedule(Car_list car_list, Trip trip){
//
//         int size=car_list.car_list.size();//number of cars
//         int[] a=new int[size];//distance between each car and the customer
//        HashSet<Integer> set=new HashSet<Integer>();
//        int Zone_des=which_zone(trip.getDest());
//        int Zone_customer=which_zone(trip.getSrc());
//        int[] index = get_index(car_list, trip, size, a, set);
//        for(int i=0;i<size;i++){
//            if(car_pref_zone.get(index[i]).contains(Zone_des)&&car_pref_zone.get(index[i]).contains(Zone_customer)){
//                return car_point_set_i(car_list, trip, a, index, set, i);
//            }
//        }
//        System.out.println("car not enough");
//        return 0;
//    }
//    static void simulate( Car_list car_list, Customer_demand demand, int n){
//        int  total_cost=0;
//        int index=0;
//        Trip trip_next=new Trip();
//        System.out.println("Version 3:*************************************************************************************************");
//        for(int time=0;time<3600;time++) {
//            if (index < n) {
//                trip_next=demand.trip_list.get(index);
//            }
//            if (trip_next.getStartTime() == time) {
//                Double D = Math.random();
//                index++;
//                if(D<=0.5){
//                    int car_index=GreedySchedulingPlusAlgorithm.TripSchedule(car_list,trip_next);
//                    total_cost+=car_list.car_list.get(car_index).getTime_stamp_arrive_des()-time;
//                }
//                else{
//                    int car_index=TripSchedule(car_list,trip_next);
//                    total_cost+=car_list.car_list.get(car_index).getTime_stamp_arrive_des()-time;
//                }
//            }
//        }
//        System.out.println("*************************************************************************************************");
//        System.out.println("Total Cost: "+total_cost);
//        System.out.println("*************************************************************************************************");
//    }
//
//    public static Vector<Set<Integer>> generate_prefer_zone(int k){
//        int[] Num_car_pref_zone=new int[k];
//        Vector<Set<Integer>> car_pref_zone=new Vector<>(k);
//
//        for(int i=0;i<k;i++){
//            Num_car_pref_zone[i]=randomArray(1,5,1)[0];
//            car_pref_zone.add(random_set(1,5,Num_car_pref_zone[i]));
//        }
//        return car_pref_zone;
//    }
//
//
//    /**
//     * Determine which zone this point belong to
//     * @param a
//     * @return
//     */
//    public static int which_zone(Point a){
//        int x=a.getX();
//        int y=a.getY();
//        if(x<500&&y<500){
//            return 3;
//        }else if(x<500&&y>500){
//            return 1;
//        }else if(x>500&&y>500){
//            return 2;
//        }else{
//            return 4;
//        }
//    }
//}
