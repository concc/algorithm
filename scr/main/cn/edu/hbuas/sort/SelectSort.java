package cn.edu.hbuas.sort;

public class SelectSort {


    /**
     *  选择排序思想： 第一层循环查询最小的数，将该数与第一个数交换
     *  交换后进入第二个数起始位置继续循环
     *  O(N^2)
     *
     */
    public static <T> void selectSort(Comparable<T>[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; ++i) {
            //初始索引
            int minIndex = i;
            for (int j = i + 1; j < arr.length; ++j) {
                // 找到除查找外数中，最小数的索引
                minIndex = less(arr[j], arr[minIndex]) ? j : minIndex;
            }
            swap(arr, i, minIndex);
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
        selectSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }

}
