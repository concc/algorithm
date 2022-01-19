package cn.edu.hbuas.sort;


public class InsertSort {


    /**
     *  插入排序思想： 从后往左遍历，当遇到开始索引 or 比自己小或相等的值则停下来
     *  保证前面有序，如新增数字不符合有序，则挨个替换，直到有序停止
     *  O(N^2)
     *
    */
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i <= arr.length - 1; ++i) {
            // 直到遇到比自己小的数为止
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; --j) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
    *  交换数组中的两个值
    */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    public static void main(String[] args) {
        int[] arr1 = Validator.generateRandomArray(5000, 5000);
        int[] arr2 = Validator.copyArray(arr1);
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
