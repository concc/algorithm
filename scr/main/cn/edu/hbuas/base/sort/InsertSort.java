package cn.edu.hbuas.base.sort;


public class InsertSort {


    /**
     *  插入排序思想： 从后往左遍历，当遇到开始索引 or 比自己小或相等的值则停下来
     *  保证前面有序，如新增数字不符合有序，则挨个替换，直到有序停止
     *  O(N^2)
     *
    */
    public static <T> void insertSort(Comparable<T>[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i <= arr.length - 1; ++i) {
            // 直到遇到比自己小的数为止
            for (int j = i - 1; j >= 0 && less(arr[j + 1], arr[j]); --j) {
                swap(arr, j, j + 1);
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
        insertSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }
}
