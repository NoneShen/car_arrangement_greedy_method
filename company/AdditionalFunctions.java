package com.company;

import java.util.*;

public class AdditionalFunctions {

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
     * return an  n size set that contains numbers between [min,max)
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
     * return an  n size sorted List that contains numbers between [min,max),
     * @return
     */
    public static List<Integer> randomList(int min, int max, int n) {
        List<Integer> time_list = new ArrayList<Integer>();
        int[] array =  randomArray( min,  max,  n);
        for (Integer value : array) {
            time_list.add(value);
        }
        Collections.sort(time_list);
        return time_list;
    }


}
