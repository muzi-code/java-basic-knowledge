package com.github.dev.muzi.base.concurrent.knowledge.labuladong;

/**
 * 二叉树的结点
 *
 * @author lifuyi8
 * @since 2021/8/11 6:36 下午
 */
public class TreeNode {

    private int value;

    private TreeNode leftChild;

    private TreeNode rightChild;

    public TreeNode() {
    }

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode leftChild, TreeNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
