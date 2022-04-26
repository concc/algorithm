package cn.edu.hbuas.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ：luopengfei
 * @date ： 2021/11/22 21:22
 * @description： 打乱数组，洗牌算法
 */
public class LeetCode384 {
    int[] nums;
    int[] original;

    public LeetCode384(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, original.length);
        return nums;
    }

    /**
     *  洗牌算法，每次随机其中一个数字，并将其与最左边的数字换位，然后将换位后的数字排除
     * 在剩余数组中再次进行洗牌，使每个数出现的概率都是一致的
    */
    public int[] shuffle() {
        Random random= new Random();
        for (int i = 0; i < nums.length; i++){
            int j = i + random.nextInt(nums.length - i);
            swap(i, j);
        }
        return nums;
    }

    public void swap(int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] input = {1,2,3,4};
        LeetCode384 leetCode384 = new LeetCode384(input);
        System.out.println(Arrays.toString(leetCode384.shuffle()));
        System.out.println(Arrays.toString(leetCode384.reset()));
        System.out.println(Arrays.toString(leetCode384.shuffle()));
    }

}
