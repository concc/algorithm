package cn.edu.hbuas.sort;


import java.util.Arrays;

public class Validator {
    /**
    *  排序对数器
    */
    public static <T> void comparator(Comparable<T>[] arr){
        Arrays.sort(arr);
    }

    /**
    *  随机数组生成
    */
    public  static Integer[] generateRandomArray(int maxSize, int maxValue) {
        Integer[] arr = new Integer[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; ++i){
            arr[i] = (int)((maxValue + 1) * Math.random() - (int)(maxValue * Math.random()));
        }
        return  arr;
    }

    /**
    *  复制数组
    */
    public static <T> Comparable<T>[] copyArray(Comparable<T>[] arr) {
        if (arr == null) {
            return null;
        }
        Comparable<T>[] res = new Comparable[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    /**
    *  判断数组是否相等
    */
    public static <T> boolean isEqual(Comparable<T>[] arr1, Comparable<T>[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
    *  打印数组内容
    */
    public static <T> void printArray(Comparable<T>[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
