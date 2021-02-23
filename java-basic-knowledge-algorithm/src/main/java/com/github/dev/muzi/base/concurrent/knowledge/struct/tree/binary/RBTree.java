package com.github.dev.muzi.base.concurrent.knowledge.struct.tree.binary;

import java.util.LinkedList;

/**
 * create by muzi 2019-07-11
 *
 * 白话红黑树的性质：
 * 1.树的每一个节点不是黑色就是红色。
 * 2.树的根节点必须是黑色。
 * 3.父节点和子节点的颜色不能都为红色。（两个相连的节点不能都为红色）
 * 4.在树的任意一个节点到其所有可能到达的叶子节点的路径上，黑色节点的数量必须相同。
 * 5.哨兵的颜色是黑色。（不需要哨兵来实现红黑树算法的可以忽略）
 * 区分 "叶节点" 和 "叶子节点" 的概念。
 * 叶节点相当于哨兵，当前域指向叶节点其实是指向了空（null），这里的叶节点相当于哨兵。
 * 叶子节点指的是左右指针域都为 null ，也就是没有左右孩子的节点。
 */
public class RBTree {

    private static final Node NIL_NODE = new Node();        //叶节点，哨兵
    private static final Byte RED = 1;                      //代表节点颜色为红色
    private static final Byte BLACK = 0;                    //代表节点颜色为黑色

    private Node root = NIL_NODE;                           //根节点，初始化指向叶节点


    public void add(int data) {
        insertNode(new Node(data, RED));
    }

    private synchronized void insertNode(Node node) {
        Node pre = NIL_NODE;
        Node index = root;

        // 通过index找到node可以插入的位置，pre记录index经过的前一个节点。
        while (index != NIL_NODE) {
            pre = index;
            if (node.data < index.data) {
                index = index.lChild;
            } else {
                index = index.rChild;
            }
        }

        // 根据node值的大小，将node插入到pre对应的左右子节点上。
        node.parent = pre;
        if (pre == NIL_NODE) {
            this.root = node;
        } else if (node.data < pre.data) {
            pre.lChild = node;
        } else {
            pre.rChild = node;
        }

        // 再次初始化node其他域的值，节点颜色刚插入统一为红色。
        node.lChild = NIL_NODE;
        node.rChild = NIL_NODE;
        node.color = RED;

        // 根据当前node位置，向上回溯检测红黑树的性质。
        treeStructFix(node);
    }

    private synchronized void treeStructFix(Node node) {
        // 父为红色才检测
        while (RED.equals(node.parent.color)) {
            // 父节点是爷节点的左孩子
            if (node.parent == node.parent.parent.lChild) {
                Node uncle = node.parent.parent.rChild;
                // 叔节点为红色调整节点的颜色,继续向上回溯即可
                if (RED.equals(uncle.color)) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.rChild) {
                    // 叔节点为黑色，node节点为父节点的右孩子，先以父节点为基点左旋，再以旋转后node爷节点为基点右旋。
                    node = node.parent;
                    leftRotate(node);
                }
                // 叔节点为黑色，node节点为父亲的左孩子，改变父节点为黑色，爷节点为红色，并以node的爷节点为基点右旋。
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rightRotate(node.parent.parent);
            } else {
                // 父节点是爷节点的右孩子
                Node uncle = node.parent.parent.lChild;
                // 叔叔节点为红色，调整颜色，继续向上回溯
                if (RED.equals(uncle.color)) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.lChild) {
                    // 叔节点为黑色，node节点若是父亲节点的左孩子，先以父节点为基点右旋，再以旋转后的node爷节点左旋。
                    node = node.parent;
                    rightRotate(node);
                }
                // 叔节点为黑色，node节点为父亲的右孩子，改变父节点为黑色，爷节点为红色，并以node的爷节点为基点左旋。
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                leftRotate(node.parent.parent);
            }
        }

        // 根节点必须为黑色
        this.root.color = BLACK;
    }

    /**
     * 节点实体定义，初始化所有Node指针域指向NIL_NODE.
     * 简化比较操作，将值域定义为整型变量.
     * 默认节点的颜色为黑色.
     */
    private static class Node {
        public Node lChild = NIL_NODE;
        public Node rChild = NIL_NODE;
        public Node parent = NIL_NODE;
        public Integer data = 0;
        public Byte color = BLACK;

        public Node() {
        }

        public Node(Integer data) {
            this.data = data;
        }

        public Node(Integer data, Byte color) {
            this.data = data;
            this.color = color;
        }
    }

    /**
     * 基点：待旋转的轴心点
     * 左旋转，即基点继承右孩子的左子树，右孩子继承基点的父关系，基点变成右孩子的左子树。
     */
    private synchronized void leftRotate(Node base) {
        // 使right引用指向base基点的右孩子。
        Node right = base.rChild;
        // 是base基点继承右孩子的左子树。
        base.rChild = right.lChild;
        // 如果right的左孩子不是哨兵。
        if (right.lChild != NIL_NODE) {
            // 使得right的左孩子的父指针域指向基点。
            right.lChild.parent = base;
        }
        // right继承base基点的父关系
        right.parent = base.parent;
        if (base.parent == NIL_NODE) {
            // base是根节点，继承基点父关系的right，此时应该是树根。
            this.root = right;
        } else if (base == base.parent.lChild) {
            // 如果base是base父节点的左孩子，此时right应继承base父节点的左孩子关系
            base.parent.lChild = right;
        } else {
            // 如果base是base父节点的右孩子，此时right应继承base父节点的右孩子关系
            base.parent.rChild = right;
        }
        // 使right左孩子指针域指向base基点
        right.lChild = base;
        // 使base的父指针域指向right
        base.parent = right;
    }

    /**
     * 右旋转，即基点继承左孩子的右子树，左孩子继承基点的父关系，基点变成左孩子的右子树。
     */
    private synchronized void rightRotate(Node base) {
        // 基点继承左孩子的右子树，当作自己的左子树。
        Node left = base.lChild;
        base.lChild = left.rChild;
        if (left.rChild != NIL_NODE) {
            left.rChild.parent = base;
        }
        // 左孩子继承基点的父关系
        left.parent = base.parent;
        if (base.parent == NIL_NODE) {
            this.root = left;
        } else if (base == base.parent.rChild) {
            base.parent.rChild = left;
        } else {
            base.parent.lChild = left;
        }
        // 基点变成左孩子的右子树
        left.rChild = base;
        base.parent = left;
    }

    /**
     * 层序遍历红黑树
     */
    public void show() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node item = queue.poll();
            System.out.print(" " + item.data);
            if (item.lChild != NIL_NODE) {
                queue.offer(item.lChild);
            }
            if (item.rChild != NIL_NODE) {
                queue.offer(item.rChild);
            }
        }
    }

//    public static void main(String[] args) {
//        RBTree rbTree = new RBTree();
//        int[] arr = {15, 10, 20, 5, 12, 25,1,2};
//        for (int item : arr) {
//            rbTree.add(item);
//        }
//        rbTree.show();
//    }
}
