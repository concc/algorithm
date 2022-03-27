package cn.edu.hbuas.base.tree;


/**
 *  线段树
 * 树形数组实现
*/
public class SegmentTree {

    private int[] seg;

    private final int n;

    public SegmentTree(int[] nums) {
        n = nums.length;

        if (n > 0) {
            seg = new int[n * 4];
            build(nums, 0, n - 1, 0);
        }
    }

    private void build(int[] nums, int start, int end, int node) {
        if (start == end) {
            seg[node] = nums[start];
            return;
        }

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        build(nums, start, mid, leftNode);
        build(nums, mid + 1, end, rightNode);
        seg[node] = seg[leftNode] + seg[rightNode];
    }

    private void modify(int index, int val) {
        modify(0, n - 1, index, val, 0);
    }

    private void modify(int start, int end, int index, int val, int node) {
        if (start == end) {
            seg[node] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        if (index <= mid ){
            modify(start, mid, index, val, leftNode);
        }else {
            modify(mid + 1, end, index, val, rightNode);
        }
        seg[node] = seg[leftNode] + seg[rightNode];
    }

    private int query(int i, int j) {
        return query(0, n - 1, i, j, 0);
    }

    private int query(int start, int end, int i, int j, int node) {
        // 不在正常范围内
        if (start > j || end < i) {
            return 0;
        }
        // 只要在区间内，即为最终结果
        if (i <= start && j >= end) {
            return seg[node];
        }

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        return query(start, mid, i, j, leftNode) + query(mid + 1, end, i, j, rightNode);
    }


    public static void main(String[] args) {
        int[] resource = new int[]{1, 2, 7, 8, 5};
        SegmentTree segmentTree = new SegmentTree(resource);
        for (int item : segmentTree.seg) {
            System.out.println(item);
        }

        System.out.println(segmentTree.query(0, 2));

        segmentTree.modify(0, 4);

        System.out.println("_________________________________________________");
        for (int item : segmentTree.seg) {
            System.out.println(item);
        }

        System.out.println(segmentTree.query(0, 1));
    }
}
