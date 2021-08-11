package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

import com.alibaba.fastjson.JSON;

/**
 * @author lifuyi8
 * @since 2021/2/20 8:14 下午
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BTree3Iterator {

    // 前中后序遍历3个迭代位
    static int a = 0, b = 0, c = 0;

    // 三种遍历方式输出树的内容
    public static int[][] threeOrders(TreeNode root) {

        // 获取数的长度设置二维数组的列数
        int[][] nums = new int[3][getRootSize(root)];

        // 遍历并设置二维数组的各个序列的值
        getOrder(root, nums);
        return nums;
    }

    // 递归遍历树记录各个位的值
    public static void getOrder(TreeNode root, int[][] nums) {
        if (root == null) {
            return;
        }

        // 前序设置值
        nums[0][a++] = root.val;
        getOrder(root.left, nums);

        // 中序设置值
        nums[1][b++] = root.val;
        getOrder(root.right, nums);

        // 后序设置值
        nums[2][c++] = root.val;
    }

    // 获取树的长度
    public static int getRootSize(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + getRootSize(root.left) + getRootSize(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        System.out.println(JSON.toJSONString(threeOrders(node1)));
    }
}
