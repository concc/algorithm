package cn.edu.hbuas.sort;


public class QuickSort {

    /**
     *  快速排序思想： 随机选择一个数，使大于该数的放在该数右边，小于该数的放在左边
     *  得到选择的数在数组中的绝对位置，然后递归二分继续找到小规模数组中选中的数在子数组中的绝对位置，
     *  直到整个数组有序
     *  不稳定排序，平均时间复杂度 O(NlogN)
     *
     */
    public static <T> void quickSort(Comparable<T>[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
//        sort(arr, 0, arr.length - 1);
        sort(arr, 0, arr.length - 1);
    }


    public static <T> void sort(Comparable<T>[] arr, int l, int r){
        if (l >= r) {
            return;
        }
        int j = partition(arr, l, r);
        sort(arr, l, j - 1);
        sort(arr, j + 1, r);
    }

    /**
     *  三向切分，维护一个小于选中值的l - lt的指针
     *  再维护一个大于选中值的gt - r的指针
    */
    public static <T> void sort3(Comparable<T>[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int lt = l;
        int i = l + 1;
        int gt = r;
        Comparable<T> v = arr[l];
        while (i <= gt){
            int cmp = arr[i].compareTo((T) v);
            // 当当前值小于选中值，左指针前移，并且将当前值与选中值交换
            if (cmp < 0) swap(arr, lt++, i++);
            // 当大于选中值，右指针后移，并将当前值与右指针指向值交换
            if (cmp > 0) swap(arr, gt--, i);
            else i++;
        }
        sort(arr, l, lt - 1);
        sort(arr, gt + 1, r);
    }

    public static <T> int partition(Comparable<T>[] arr, int l, int r) {
        int i = l;
        // 避免溢出
        int j = r + 1;
        Comparable<T> v = arr[l];
        while (true){
            // 从左往右找，结束退出
            while (less(arr[++i], v)) if (i == r) break;
            // 从右往左找，结束退出
            while (less(v, arr[--j])) if (j == l) break;
            //左右指针相遇
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, l, j);
        return j;
    }


    /**
     *  交换数组中的两个值
     */
    private static <T> void swap(Comparable<T>[] a, int i, int j){
        Comparable<T> t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }


    public static void main(String[] args) {
        Integer[] arr1 = Validator.generateRandomArray(5000, 5000);
        Comparable<Integer>[] arr2 = Validator.copyArray(arr1);
        Validator.comparator(arr1);
        quickSort(arr2);
        Validator.printArray(arr1);
        Validator.printArray(arr2);
        if (Validator.isEqual(arr1, arr2)) {
            System.out.println("排序算法验证通过");
        }else {
            System.out.println("排序算法验证未通过");
        }
    }
}
