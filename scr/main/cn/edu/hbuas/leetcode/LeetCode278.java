package cn.edu.hbuas.leetcode;

/**
 * @author ：luopengfei
 * @date ： 2021/11/22 22:18
 * @description： 二分查找版本
 */
public class LeetCode278 extends VersionControl{
    public static final int target = 4;
    public static int count = 0;

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        // 循环直至区间左右端点相同
        while (left < right) {
            int mid = left + (right -left)/2;
            // 答案在区间 [left, mid] 中
            if (isBadVersion(mid)) {
                right = mid;
            }else {
                // 答案在区间 [mid+1, right] 中
                left = mid + 1;
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }


    public static void main(String[] args) {
        LeetCode278 leetCode278 = new LeetCode278();
        System.out.println(leetCode278.firstBadVersion(40));
        System.out.println(count);
    }
}

class VersionControl {
    public boolean isBadVersion(int version){
        LeetCode278.count++;
        return version == LeetCode278.target;
    }
}