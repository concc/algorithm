package cn.edu.hbuas.base.sort;

public class ShellSort {
    /**
     * 希尔排序思想： 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序
     *
     */
    public static <T> void shellSort(Comparable<T>[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        int N = a.length;
        int h = 1;
        while(h < N/3){
            //1,4,13,40,121,...h为每次第二组的第一个元素的坐标，这样先两i=h后i++也就是第二组的后面的一直在和第一组的进行比较
            h = 3 * h + 1;
        }
        //将数组变为h有序
        while(h >= 1){
            //将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...中
            for(int i = h; i < N; i++){
                //j>=h目的是a[j-h]有意义，j -= h,是为了让a[i]和a[i -h],a[i -2*h],...比较
                for(int j = i; j >= h&& less(a[j], a[j-h]); j -= h){
                    swap(a,j,j-h);
                }
            }
            //逐步减小h，直至h=1时结束
            h = h / 3;
        }
    }


    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    private static <T> void swap(Comparable<T>[] a, int i, int j){
        Comparable<T> t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] arr1 = Validator.generateRandomArray(5000, 5000);
        Comparable<Integer>[] arr2 = Validator.copyArray(arr1);
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
