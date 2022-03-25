package cn.edu.hbuas.leetcode;

import java.util.Objects;

public class Leetcode5 {


    public static void main(String[] args) {
        String result1 = longestPalindrome1("aacabdkacaa");
        String result2 = longestPalindrome2("aacabdkacaa");
        String result3 = longestPalindrome3("aacabdkacaa");
        if (result1.equals(result2)  && result2.equals(result3)) {
            System.out.println("success");
        }else {
            System.out.println("failed");
        }
    }


    /**
    *  暴力穷举法
    */
    public static String longestPalindrome1(String s) {

        // 如果为null或者长度小于1直接返回原字符串
        if (Objects.isNull(s) && s.length() <= 1) {
            return s;
        }

        // 初始化结果字符串,结果字符串长度
        String result = "";
        int max = 0;

        for (int i = 0; i< s.length(); i++) {
            // 小于max不需要继续判断，即i+max
            for (int j = i + max; j <= s.length(); j++) {
                // 截取待判断字符串
                String temp = s.substring(i, j);
                // 判断是否为回文
                boolean check = checkPalindrome(temp);
                // 是否更新结果值
                if (check) {
                    if ( max < temp.length()) {
                        max = temp.length();
                        result = temp;
                    }
                }
            }
        }
        return result;
    }

    public static boolean checkPalindrome(String str){
        // 如果字符串为空
        if(null == str) {
            return false;
        }
        int length = str.length();
        int mid = length/ 2;
        int i = 0, j = length -1;
        while (i < mid && j >= mid) {
            if (str.charAt(i++) != str.charAt(j--)){
                return false;
            }
        }
        return true;
    }


    /**
     *  动态规划
     */
    public static String longestPalindrome2(String s) {
        if (Objects.isNull(s) || s.length() <= 1) {
            return s;
        }
        // 翻转字符串
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int maxEnd = 0, maxLength = 0;
        // 用来记录前一步判断的长度
        int[][] record = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j< length; j++) {
                // 如果相等则记录长度
                if (reverse.charAt(j) == s.charAt(i)) {
                    if (i == 0 || j == 0) {
                        // 第一行或第一列如果相等长度为1
                        record[i][j] = 1;
                    } else {
                        // 不为第一列或第一行，取前一次记录的长度并加上1
                        record[i][j] = record[i-1][j-1] + 1;
                    }
                    if (record[i][j] > maxLength) {
                        // 这里可能出现这种情况，aacabdkacaa，翻转后为aacakdbacaa,如果按照上述求源字符串和翻转后字符串的最长
                        // 的公共子串时，结果为aaca，但aaca并不是回文串，不符合要求，我们还需要判断该字符串倒置前的下标和当前的字符串下标是不是匹配
                        // 首先，i和j都指向子串的末尾位置，先求出j翻转前的下标，对应的就是字符串首位的下标
                        // 当加上长度后就是末尾字符的下标，此时如果他和i相等，即是我们要找的字符串

                        // 正例：caba, 回文子串为aba，即i 指向3， j 指向2， j倒置前的下标为 length - 1 -j = 4 - 1 - 2 = 1
                        // 我们再加上字符串的长度才为末尾字符串的下标，也就是 before + record[i[j] - 1 = 1 + 3 - 1 = 3 刚好和i相等
                        // 此回文子串成立
                        /*
                         	 0 1 2 3
                        	 c a b a
                        0 a  0 1 0 1
                        1 b  0 0 2 0
                        2 a  0 1 0 3
                        3 c  1 0 0
                        */

                        // 反例：aacabdkacaa, 回文子串为acaa，即i 指向10， j 指向10， j倒置前的下标为 length - 1 -j = 11 - 1 - 10 = 0
                        // 我们再加上字符串的长度才为末尾字符串的下标，也就是 before + record[i[j] - 1 = 0 + 4 - 1 = 3 不相等
                        // 此回文子串不成立
                        /*
                         	  0 1 2 3 4 5 6 7 8 9 10
                        	  a a c a b d k a c a a
                        0  a  1 1 0 1 0 0 0 1 0 1 1
                        1  a  1 2 0 1 0 0 0 1 0 1 2
                        2  c  0 0 1 0 0 0 0 0 2 0 0
                        3  a  1 1 0 2 0 0 0 1 0 3 1
                        4  k  0 0 0 0 0 0 1 0 0 0 0
                        5  d  0 0 0 0 0 1 0 0 0 0 0
                        6  b  0 0 0 0 1 0 0 0 0 0 0
                        7  a  1 1 0 1 0 0 0 1 0 1 1
                        8  c  0 0 2 0 0 0 0 0 2 0 0
                        9  a  1 1 0 1 0 0 0 1 0 3 1
                        10 a  1 2 0 1 0 0 0 1 0 1 4
                        */
                        int before = length - 1 - j;
                        if (before + record[i][j] - 1 == i) {
                            maxLength = record[i][j];
                            maxEnd = i;
                        }
                    }
                }
            }

        }
        return s.substring(maxEnd - maxLength + 1, maxEnd + 1);
    }


    /**
     *  中心扩展法
     */
    public static String longestPalindrome3(String s) {
        if (Objects.isNull(s) || s.length() <= 1) {
            return s;
        }
        // 记录回文串起始和结束位置
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数扩展
            int len1 = centerSpread(i, i, s);
            // 偶数扩展
            int len2 = centerSpread(i, i+1, s);
            // 两次扩展取最优
            int len = Math.max(len1, len2);
            // 当扩展出现比原有长度更长的情况。更新起始位置
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    public static int centerSpread(int left, int right, String str) {
        // 计算中心扩展为回文串的最大长度
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
