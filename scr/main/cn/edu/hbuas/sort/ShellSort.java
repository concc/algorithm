package cn.edu.hbuas.sort;

public class ShellSort {
    /**
     * 希尔排序思想： 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序
     *
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //希尔排序的增量
        int d = arr.length;
        while (d > 1) {
            //使用希尔增量的方式，即每次折半
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int temp = arr[i];
                    int j;
                    for (j = i - d; j >= 0 && arr[j] > temp; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[] arr1 = Validator.generateRandomArray(5000, 5000);
        int[] arr2 = Validator.copyArray(arr1);
        Validator.comparator(arr1);
        shellSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        } else {
            System.out.println("排序算法验证未通过");
        }
    }
}
