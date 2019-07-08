package com.example.demo;

import java.util.*;

public class Test {
    public static void main(String[] args) {


        Integer[] array = {2, 2, 2, 1, 1};


        List<ArrayList<Integer>> set = combinations(array, 8);
        ArrayList<Double> arrayList = price(set);

        arrayList.sort(new Comparator<Double>() {
            @Override
            public int compare(Double aDouble, Double t1) {
                return Double.compare(aDouble, t1);

            }
        });

        HashSet<Integer> j = new HashSet<>();


        System.out.println(arrayList.get(0));
        System.out.println(sum(100));
        System.out.println(arrayList);
        System.out.println(set);
        int[] ar = {1, 1, 2, 2, 2};
        int[] arr = {1, 3, 2, 5, 2};

        System.out.println(price(ar));
        System.out.println(Arrays.toString(bubleSort(arr)));

    }

    public static double price(int[] array) {
        double count = 0;
        double[] arrayPrice = {6.0, 6.4, 7.2, 7.6, 8};
        int minus = 0;

        if (array[0] != 0 && array[2] != 0) {

            array[1] += array[0];
            array[0] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            count += array[i] * arrayPrice[i] * (arrayPrice.length - i);
            minus += array[i];

            if (i < (array.length - 1)) {
                array[i + 1] = array[i + 1] - minus;
            }
        }

        return count;

    }

    public static int[] bubleSort(int[] array) {
        int count = 0;
        boolean swapped  ;
        for (int i = 0; i < array.length-1; i++) {
            swapped = false;
            for (int j = 0; j < array.length-i - 1; j++) {


                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }



                count ++;
            }

           if (!swapped ){break;}
        }
        System.out.println(count);

        return array;

    }

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    public static ArrayList<Double> price(List<ArrayList<Integer>> set) {
        Double[] array1 = {6.0, 6.4, 7.2, 7.6, 8.0};
        Double[] array2 = {0.0, 0.0, 0.0, 0.0, 0.0};//length set.size().


        for (int y = 0; y < set.size(); y++) {

            {
                for (int i = 0; i < 5; i++) {
                    array2[y] += set.get(y).get(i) * array1[i] * (5 - i);
                }
            }
        }
        return new ArrayList<Double>(Arrays.asList(array2));
    }

    public static List<ArrayList<Integer>> combinations(Integer[] arraym, int max) {
        List<ArrayList<Integer>> arrayLists = new ArrayList<>();
        Integer[] array1 = {5, 4, 3, 2, 1};
//gether combinations of books for
        for (int z = 0; z < array1.length; z++) {
            Integer[] array = arraym.clone();
            Integer[] array3 = {0, 0, 0, 0, 0};

            setOfBooks(max, array1[z], array, array3);
            arrayLists.add(new ArrayList<Integer>(Arrays.asList(array3)));
        }
        return arrayLists;
    }

    public static void setOfBooks(int max, Integer num, Integer[] array, Integer[] array3) {
        //run through array max times(max = number of books )
        for (int y = 0; y < max; y++) {
            Integer n = 0;
            //gether one set of n dif books
            for (int i = 0; i < 5; i++) {

                if (array[i] > 0) {
                    n += 1;
                    array[i] = array[i] - 1;
                }
                if (n.equals(num)) {
                    break;
                }
            }
            switchs(n, array3);
        }
    }

    public static Integer[] switchs(Integer n, Integer[] array) {

        switch (n) {
            case 5:
                array[0] += 1;
                break;
            case 4:
                array[1] += 1;
                break;
            case 3:
                array[2] += 1;
                break;
            case 2:
                array[3] += 1;
                break;
            case 1:
                array[4] += 1;
            default:
                break;

        }
        return array;

    }


//    public static void printCombinations(int index, int[] denom, int target, int[] vals) {
//        if (target == 0) {
//            System.out.println(Arrays.toString(vals));
//            return;
//        }
//        if (index == denom.length) return;
//        int currDenom = denom[index];
//        for (int i = 0; i * currDenom <= target; i++) {
//            vals[index] = i;
//            printCombinations(index + 1, denom, target - i * currDenom, vals);
//            vals[index] = 0;
//        }
//    }

//    public static ArrayList printCombinations1(int index, int[] denom,int target, int[] vals)
//    {
//
//        Set<HashSet> set = new HashSet<>();
//        Set<Integer> set1 = new HashSet<>();

//
//        while (target>0){
//            set1.add()
//
//        }

//        for (int i =0; i<=100;)
//
//
//        if(target==0)
//        {
//            System.out.println(Arrays.toString(vals));
//            return;
//        }
//        if(index == denom.length) return;
//        int currDenom = denom[index];
//        for(int i = 0; i*currDenom <= target;i++)
//        {
//            vals[index] = i;
//            printCombinations(index+1, denom, target - i*currDenom, vals);
//            vals[index] = 0;
//        }
//    }


    public static Integer getSmallest(ArrayList<Integer> list, int nth) {

        list.sort(Comparator.naturalOrder());

        return list.get(nth - 1);
    }

    public static int[] min(int[] list, int nth) {
        int min = list[0];
        int min2 = list[1];
        int ii = 0;
        int[] sorted = new int[list.length];


        for (int i = 0; i < list.length; i++) {
            if (list[i] < min) {
                min2 = min;
                min = list[i];
                ii = i;
//            } else if (list[i]<min2){
//                min2=list[i];

            }
        }

        if (list[0] == min) {
            for (int i = 1; i < list.length; i++) {
                if (list[i] < min2) {
                    min2 = list[i];
                }
            }

        }


        return sorted;
    }

    public static int nth(int[] list, int nth) {
        int min = list[0];
        int ii = list.length;
        int iii = list.length;


        for (int i = 0; i < ii; i++) {
            if (list[i] < min) {
                min = list[i];
                iii = i;
            }
        }


        return ii;
    }


    public static String[] answer() {
        String[] array = new String[100];

        for (int i = 1; i < 100; i++) {

            if (i % 3 != 0 && i % 5 != 0) {
                array[i - 1] = Integer.toString(i);
            } else if (i % 5 == 0 && i % 3 == 0) {
                array[i - 1] = "FuzzBuzz";
            } else if (i % 3 == 0) {
                array[i - 1] = "Fuzz";
            } else {
                array[i - 1] = "Buzz";
            }

        }

        return array;
    }

    public static Map<String, Integer> ntjjh(Map<String, Integer> books) {

        return books;
    }

    public static String[] answer12() {

        String[] array = new String[100];

        for (int i = 1; i <= 100; i++) {

            if ((Math.sqrt(i) - Math.floor(Math.sqrt(i))) == 0) {
                array[i - 1] = "open";
            } else {
                array[i - 1] = "closed";
            }

        }

        return array;

    }


    public static int sum(int n) {


        int count = 0;

        for (int i = 0; i <= n / 25; i++) {
            for (int j = 0; j <= n / 10; j++) {
                for (int k = 0; k <= n / 5; k++) {
                    for (int l = 0; l <= n; l++) {
                        int v = i * 25 + j * 10 + k * 5 + l;
                        if (v == n) {
                            count++;
                        } else if (v > n) {
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }


}
