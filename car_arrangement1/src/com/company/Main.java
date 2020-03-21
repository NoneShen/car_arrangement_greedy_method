package com.company;
import java.util.*;
public class Main {
    /**
     * sort the array and return the original index of sorted array
     * @param arr
     * @return
     */
    public static int[] sort_array(int[] arr) {
        int temp;
        int index;
        int k = arr.length;
        int[] Index = new int[k];
        for (int i = 0; i < k; i++) {
            Index[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;

                        index = Index[j];
                        Index[j] = Index[j + 1];
                        Index[j + 1] = index;
                    }
                }
        }
        return Index;
    }
    /**
     * return a random number in [0,999]
     * @return
     */
    public static int random_num(){
        int max=999;
        int min=0;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
    /**
     * return a distance between two points
     * @return
     */
    public static int calculate_distance(Point point1, Point point2){
       return Math.abs(point1.getX()-point2.getX())+Math.abs(point1.getY()-point2.getY());
    }

    /**
     * return a set that contains n random numbers from min to max
     * @return
     */
    public static Set<Integer> random_set(int min, int max, int n) {
        Set<Integer> set = new HashSet<Integer>();
        int[] array = new int[n];
        for (; true;) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            // 将不同的数存入HashSet中
            set.add(num);
            // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
            if (set.size() >= n) {
                break;
            }
        }
        return set;

    }
    /**
     * sort an  n size array that contains numbers between [min,max)
     * @return
     */
    public static int[] randomArray(int min, int max, int n) {
        int[] array = new int[n];
        Set<Integer> set = random_set(min,max,n);
        int i = 0;
        for (int a : set) {
            array[i] = a;
            i++;
        }
        return array;
    }


    /**
     * Determine which zone this point belong to
     * @param a
     * @return
     */
    public static int which_zone(Point a){
        int x=a.getX();
        int y=a.getY();
        if(x<500&&y<500){
            return 3;
        }else if(x<500&&y>500){
            return 1;
        }else if(x>500&&y>500){
            return 2;
        }else{
            return 4;
        }
    }
    /**
     * get the index of the car that has the minimal distance between customer
     * @param car_points
     * @param customer
     * @param time
     * @param size
     * @param a
     * @param set
     * @return
     */

    private static int[] get_index(Vector<Car> car_points, Point customer, int time, int size, int[] a, HashSet<Integer> set) {
        int time_remain;
        int[] index;
        for(int i = 0; i<size; i++){
            Point car=car_points.get(i);
            time_remain=car.get_time()-time;
            if(time_remain>0) {
                a[i] = calculate_distance(car, customer)+time_remain;
                set.add(i);
            }else{
                a[i]=  calculate_distance(car,customer);
            }
        }
        index=sort_array(a);
        return index;
    }

    /**
     * let the first car be the output
     * @param car_points
     * @param Des
     * @param time
     * @param a
     * @param index
     * @param set
     * @return
     */

    private static int car_point_set_first(Vector<Car> car_points, Point Des, int time, int[] a, int[] index, HashSet<Integer> set) {
        car_points.get(index[0]).setTime_second(Des.get_time()+a[0]+time);
        car_points.get(index[0]).setX(Des.getX());
        car_points.get(index[0]).setY(Des.getY());
        if(set.contains(index[0])){
            System.out.println("car "+ index[0]);
        }
        return index[0];
    }

    /**
     * let the ith car be the output
     * @param car_points
     * @param Des
     * @param time
     * @param a
     * @param index
     * @param set
     * @param i
     * @return
     */
    private static int car_point_set_i(Vector<Car> car_points, Point Des, int time, int[] a, int[] index, HashSet<Integer> set, int i) {
        car_points.get(index[i]).setTime_second(Des.get_time()+a[i]+time);
        car_points.get(index[i]).setX(Des.getX());
        car_points.get(index[i]).setY(Des.getY());
        if(set.contains(index[i])){
            System.out.println("car "+ index[i]);
        }
        return index[i];
    }

    /**
     * greedy version 1
     * @param car_points
     * @param customer
     * @param Des
     * @param time
     * @return
     */

    public static int greedy_arrangement(Vector<Car> car_points, Point customer, Point Des,int time){
        int size=car_points.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int[] index=new int[size];
        for(int i=0;i<size;i++){
            Point car=car_points.get(i);
            a[i]=calculate_distance(car,customer);
        }
        index=sort_array(a);
        int temp=0;
        while(car_points.get(index[temp]).get_time()>customer.get_time()){
            temp++;
        }
        int time_from_car_to_customer=calculate_distance( car_points.get(index[temp]),customer);
        //  System.out.println(Des.get_time()+" "+time_from_car_to_customer+"  "+time);
        car_points.get(index[temp]).setTime_second(Des.get_time()+time_from_car_to_customer+time);
        car_points.get(index[temp]).setX(Des.getX());
        car_points.get(index[temp]).setY(Des.getY());
        return index[temp];
    }

    public static int greedy_arrangement_version2(Vector<Car> car_points, Point customer, Point Des, int time){
        int size=car_points.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int time_remain=0;
        HashSet<Integer> set=new HashSet<Integer>();
        int[] index = get_index(car_points, customer, time, size, a, set);
        return car_point_set_first(car_points, Des, time, a, index, set);
    }
    //先把顾客的zone算出来，还是greedy安排，但是如果顾客的目的地不在preferzone里面，不安排这辆车
    public static int greedy_arrangement_version3(Vector<Car> car_points,Vector<Set<Integer>> car_pref_zone ,Point customer, Point Des, int time){
        int size=car_points.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int[] index=new int[size];//
        int time_remain=0;
        HashSet<Integer> set=new HashSet<Integer>();
        int Zone_des=which_zone(Des);
        int Zone_customer=which_zone(customer);
      //  System.out.println("Customer From Zone "+ zone_customer+ " to Zone "+ Zone_des);
        Vector<Car> car_in_zone =new Vector<Car>();
        index = get_index(car_points, customer, time, size, a, set);
        for(int i=0;i<size;i++){
            if(car_pref_zone.get(index[i]).contains(Zone_des)&&car_pref_zone.get(index[i]).contains(Zone_customer)){
                return car_point_set_i(car_points, Des, time, a, index, set, i);
            }
        }
        System.out.println("car not enough");
        return 0;
    }
    public static int greedy_arrangement_version4(Vector<Car> car_points, Point customer, Point Des, int time){
        int size=car_points.size();//number of cars
        int[] a=new int[size];//distance between each car and the customer
        int time_remain=0;
        int distance=calculate_distance(customer,Des);
        HashSet<Integer> set=new HashSet<Integer>();
        int[] index = get_index(car_points, customer, time, size, a, set);
        if(distance>650){
           int i=0;
           while(index[i]<30){
               i++;
           }
            return car_point_set_i(car_points, Des, time, a, index, set, i);
        }else {
            return car_point_set_first(car_points, Des, time, a, index, set);
        }
    }


    public static void main(String[] args) {
        int k=100    ;//car
        //0-29 electronic car 30-99 normal car
        int n=150;//customer
        Vector<Car> car_points = new Vector<Car>(k);
        Vector<Car> car_points_copy = new Vector<Car>(k);
        Vector<Car> car_points_copy2 = new Vector<Car>(k);
        Vector<Car> car_points_copy3 = new Vector<Car>(k);
        Vector<Point> customer_points = new Vector<Point>(n);
        Vector<Point> customer_destination = new Vector<Point>(n);
        List<Integer> time_list = new ArrayList<Integer>();

        int[] Num_car_pref_zone=new int[k];
        Vector<Set<Integer>> car_pref_zone=new Vector<>(k);

        for(int i=0;i<k;i++){
            Num_car_pref_zone[i]=randomArray(1,5,1)[0];
            car_pref_zone.add(random_set(1,5,Num_car_pref_zone[i]));
        }

      int[] time_set=  randomArray(1,3600,n);


        for(Integer value : time_set){
            time_list.add(value);

        }
        Collections.sort(time_list);
        //Create k random car point and add them to car_point
        int a=0,b=0;
        for(int i=0;i<k;i++){
            a=random_num();
            b=random_num();
            Car p=new Car(a,b,0);
            Car p1=new Car(a,b,0);
            Car p2=new Car(a,b,0);
            Car p3=new Car(a,b,0);
            car_points.add(p);
            car_points_copy.add(p1);
            car_points_copy2.add(p2);
            car_points_copy3.add(p3);

        }

        //car speed  1/s
        //Create n random customers and their destination
        for(int j=0;j<n;j++){
            Point p=new Point(random_num(),random_num(),time_list.get(j));
            Point p1=new Point(random_num(),random_num(),0);
            p1.setTime_second(calculate_distance(p,p1)+p.get_time());
            customer_points.add(p);
            customer_destination.add(p1);
        }
        //
        int index=0;//the index of customer
        Point customer_next =new Point(0,0,0);
        Point Des_next =new Point(0,0,0);
        int total_cost=0;
        System.out.println("Version 1 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                 customer_next = customer_points.get(index);
                 Des_next = customer_destination.get(index);
            }
            if(customer_next.get_time()==time){
                index++;
             //   System.out.println("At Time:"+time+"   Customer No." + index +" at " +customer_next.toString()+" is looking for a car");
                int car_index=greedy_arrangement(car_points,customer_next,Des_next,time);
          //     System.out.println("Car No. "+ car_index+ " will take the customer to the  destination "+Des_next.toString()+ " at time :"+car_points.get(car_index).get_time());
               total_cost+=car_points.get(car_index).get_time()-time;
            }
        }
        System.out.println("*************************************************************************************************");
        System.out.println("Total Cost: "+total_cost);
        System.out.println("*************************************************************************************************");
        total_cost=0;
        index=0;
        customer_next =new Point(0,0,0);
        Des_next =new Point(0,0,0);
        System.out.println("Version 2 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                customer_next = customer_points.get(index);
                Des_next = customer_destination.get(index);
            }
            if(customer_next.get_time()==time){
                index++;
            //   System.out.println("At Time:"+time+"   Customer No." + index +" at " +customer_next.toString()+" is looking for a car");
                int car_index=greedy_arrangement_version2(car_points_copy,customer_next,Des_next,time);
             //   System.out.println("Car No. "+ car_index+ " will take the customer to the  destination "+Des_next.toString()+ " at time :"+car_points_copy.get(car_index).get_time());
                total_cost+=car_points_copy.get(car_index).get_time()-time;
            }
        }
        System.out.println("*************************************************************************************************");
        System.out.println("Total Cost: "+total_cost);
        System.out.println("*************************************************************************************************");
        total_cost=0;
        index=0;
        customer_next =new Point(0,0,0);
        Des_next =new Point(0,0,0);
        System.out.println("Version 3:*************************************************************************************************");
        for(int time=0;time<3600;time++) {

            if (index < n) {
                customer_next = customer_points.get(index);
                Des_next = customer_destination.get(index);
            }
            if (customer_next.get_time() == time) {
                Double D = Math.random();
                index++;
                if(D<=0.5){
                    //   System.out.println("At Time:"+time+"   Customer No." + index +" at " +customer_next.toString()+" is looking for a car");
                    int car_index=greedy_arrangement_version2(car_points_copy2,customer_next,Des_next,time);
                    //   System.out.println("Car No. "+ car_index+ " will take the customer to the  destination "+Des_next.toString()+ " at time :"+car_points_copy.get(car_index).get_time());
                    total_cost+=car_points_copy2.get(car_index).get_time()-time;
                }
                else{
                    int car_index=greedy_arrangement_version3(car_points_copy2,car_pref_zone,customer_next,Des_next,time);
                    total_cost+=car_points_copy2.get(car_index).get_time()-time;
                }
            }
            }
        System.out.println("*************************************************************************************************");
        System.out.println("Total Cost: "+total_cost);
        System.out.println("*************************************************************************************************");
        total_cost=0;
        index=0;
        customer_next =new Point(0,0,0);
        Des_next =new Point(0,0,0);
        System.out.println("Version 4 :*************************************************************************************************");
        for(int time=0;time<3600;time++){
            if(index<n) {
                customer_next = customer_points.get(index);
                Des_next = customer_destination.get(index);
            }

            if(customer_next.get_time()==time){
                index++;
                //   System.out.println("At Time:"+time+"   Customer No." + index +" at " +customer_next.toString()+" is looking for a car");
                int car_index=greedy_arrangement_version4(car_points_copy3,customer_next,Des_next,time);
                //   System.out.println("Car No. "+ car_index+ " will take the customer to the  destination "+Des_next.toString()+ " at time :"+car_points_copy.get(car_index).get_time());
                total_cost+=car_points_copy3.get(car_index).get_time()-time;
            }
        }
        System.out.println("*************************************************************************************************");
        System.out.println("Total Cost: "+total_cost);
        System.out.println("*************************************************************************************************");
    }
}
