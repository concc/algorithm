package cn.edu.hbuas.sort;

public class BubbleSort {

    /**
     *  冒泡排序思想： 定义一个边界，判断边界内开始值以及后一个值的大小
     *  开始值大于后一个值则交换（重的沉下去，小的冒出来）
     *  O(N^2)
     *
     */
    public static <T> void bubbleSort(Comparable<T>[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; --i) {
            for (int j = 0; j < i; ++j) {
                // 开始索引值和后一个值比较
                if (less(arr[j + 1], arr[j])){
                    swap(arr, j, j + 1);
                }
            }

        }
    }

    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
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
        Comparable<Integer>[] arr2 = Validator.copyArray(arr1);
        Validator.comparator(arr1);
        bubbleSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }
}
