package com.github.dev.muzi.base.concurrent.knowledge.nowcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author lifuyi8
 * @since 2021/2/21 8:56 上午
 */
public class TreeLevelOrder {

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        if (root == null) return new ArrayList<>();

        // 遍历辅助队列
        LinkedList<TreeNode> queue = new LinkedList<>();

        // 收集结果的二维数组
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        // 先加入根节点
        queue.offer(root);

        // 队列不为空就去遍历记录
        while (!queue.isEmpty()) {

            // 获取当前层的节点数量
            int size = queue.size();

            // 保存当前层的节点值
            ArrayList<Integer> list = new ArrayList<>();

            // 处理掉这层的节点
            while (size-- > 0) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);

                // 维护下一层的节点
                if (tmp.left != null)
                    queue.offer(tmp.left);

                // 维护下一层节点
                if (tmp.right != null)
                    queue.offer(tmp.right);
            }
            // 保存当前层节点
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node3.left = node4;
        node3.right = node5;

        System.out.println(JSON.toJSONString(levelOrder(node1)));
    }
}
