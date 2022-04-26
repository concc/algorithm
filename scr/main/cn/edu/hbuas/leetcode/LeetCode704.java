package cn.edu.hbuas.leetcode;

/**
 * @author ：luopengfei
 * @date ： 2021/11/22 21:51
 * @description： 二分
 */
public class LeetCode704 {
    public int search(int[] nums, int target) {
        return binarrySearch(nums, target);
    }

    public int binarrySearch(int[] nums, int target){
        int right = nums.length - 1;
        int left = 0;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (target == nums[mid]){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
