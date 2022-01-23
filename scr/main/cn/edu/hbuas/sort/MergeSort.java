package cn.edu.hbuas.sort;

public class MergeSort {

    private static Comparable[] temp;

    /**
     *  归并排序思想： 将待排序数组递归分为左右两个有序的子数组
     *  然后将两个分别有序的子数组归并到一个大的数组中，直到整个数组有序
     *  O(NlogN)
     *
     */
    public static <T> void mergeSort(Comparable<T>[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        // 初始化需要使用临时数组
        temp = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    /**
    *  递归拆成不可分割的子数组，然后归并
    */
    private static <T> void sort(Comparable<T>[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r -l) >> 1);
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
    *  归并：将两个子数组合并为一个有序的数组
    */
    private static <T> void merge(Comparable<T>[] arr, int l, int mid, int r){
        int li = l;
        int ri = mid + 1;
        // 保存原有的数组元素
        for (int i = l; i <= r; ++i) {
            temp[i] = arr[i];
        }
        for (int k = l; k <= r; ++k) {
            if (li > mid){
                arr[k] = temp[ri++];
            }
            else if (ri > r){
                arr[k] = temp[li++];
            }else if(less(temp[ri], temp[li])) {
                arr[k] = temp[ri++];
            }else {
                arr[k] = temp[li++];
            }
        }
    }


    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }


    public static void main(String[] args) {
        Integer[] arr1 = Validator.generateRandomArray(5000, 5000);
        Comparable<Integer>[] arr2 = Validator.copyArray(arr1);
        Validator.comparator(arr1);
        mergeSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }
}
