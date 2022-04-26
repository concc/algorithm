package cn.edu.hbuas.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ：luopengfei
 * @date ： 2021/11/21 20:46
 * @description： dfs  and bfs
 * 计算树的最大深度
 */
public class LeetCode559 {
    public static int dfs(Node root) {
        int res = 0;
        if(root == null) {
            return 0;
        }
        for (Node node : root.children){
            res = Math.max(res, dfs(node));
        }
        return res + 1;
    }

    public static int bfs(Node root) {
        if (root == null) return 0;
        int ans = 0;
        Deque<Node> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                Node t = d.pollFirst();
                for (Node node : t.children) {
                    d.addLast(node);
                }
            }
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) {
        Node node = new Node(1);
        ArrayList<Node> integers = new ArrayList<>();
        Node node1 = new Node(5);
        node1.children = new ArrayList<>();
        Node node2 = new Node(6);
        node2.children = new ArrayList<>();
        integers.add(node1);
        integers.add(node2);
        node.children = integers;
        int i = bfs(node);
        System.out.println(i);
    }

}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

