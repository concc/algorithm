package cn.edu.hbuas.sort;


import cn.edu.hbuas.heap.PriorityQueue;

public class HeapSort {


    /**
     *  插入排序思想： 构建大顶堆，将顶端最大值与最后的值进行交换，并缩短排序数组长度
     *  再将交换后的数字进行下沉操作，重新选出一个最大的值
     *  O(NlogN)
     *
     */
    private static <T> void sort(Comparable<T>[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for(int i = N/2; i >= 0; i--) {
            sink(arr, i, N);
        }
        for(int i = N-1; i >= 0; i--) {
            swap(arr, 0, i);
            sink(arr, 0, i);
        }
    }


    /**
    *  堆下沉操作
    */
    private static <T> void sink(Comparable<T>[] arr, int k, int  N) {
        while(k * 2 + 1 < N) {
            int j = k * 2 + 1;
            if(j < N - 1 && less(arr[j], arr[j + 1]))  j++;
            if(!less(arr[k], arr[j])) break;
            swap(arr, k, j);
            k = j;
        }
    }


    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    /**
    *  直接使用堆结构进行排序
    */
    public static  void sort1(Integer[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> keys = new PriorityQueue<>(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = keys.delMax();
        }
    }

    /**
     *  交换数组中的两个值
     */
    private static <T> void swap(Comparable<T>[] a, int i, int j){
        Comparable<T> t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        Integer[] arr1 = Validator.generateRandomArray(5000, 5000);
        Integer[] arr2 = new Integer[arr1.length];
        for (int i = 0; i< arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        Validator.comparator(arr1);
//        sort(arr2);
        sort1(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }
}
