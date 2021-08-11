package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.cache;


import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class RBTree<T> {

    /**
     * 红色
     */
    private static final boolean RED = true;

    /**
     * 黑色
     */
    private static final boolean BLACK = false;

    /**
     * 叶子节点/哨兵节点
     */
    private static final RBNode NIL = new RBNode<>(null, null, null, null, null, BLACK);

    /**
     * 根节点
     */
    private RBNode root;


    public RBTree() {
        root = NIL;
    }

    public boolean add(String key, T value) {
        if (StringUtils.isBlank(key) || value == null) {
            return false;
        }
        RBNode<T> rbNode = new RBNode<T>(NIL, NIL, NIL, key, value, RED);
        insert(rbNode);
        return true;
    }

    private synchronized void insert(RBNode<T> node) {
        RBNode pre = NIL;
        RBNode index = root;

        // 通过index找到node可以插入的位置，pre记录index经过的前一个节点。
        while (index != NIL) {
            pre = index;
            String itemKey = index.key;
            String nowKey = node.key;
            if (nowKey.compareTo(itemKey) < 0) {
                index = index.lChild;
            } else if (nowKey.compareTo(itemKey) > 0) {
                index = index.rChild;
            } else {
                // 如果结点key相同，覆盖原值
                index.value = node.value;
                return;
            }
        }

        // 根据node值的大小，将node插入到pre对应的左右子节点上。
        node.parent = pre;
        if (pre == NIL) {
            this.root = node;
        } else if (node.key.compareTo(pre.key) < 0) {
            pre.lChild = node;
        } else {
            pre.rChild = node;
        }

        // 根据当前node位置，向上回溯检测红黑树的性质。
        insertFixUp(node);
    }


    private void insertFixUp(RBNode node) {
        // 父为红色才检测
        while (RED == node.parent.color) {
            // 父节点是爷节点的左孩子
            if (node.parent == node.parent.parent.lChild) {
                RBNode uncle = node.parent.parent.rChild;
                // 叔节点为红色调整节点的颜色,继续向上回溯即可
                if (RED == uncle.color) {
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
                RBNode uncle = node.parent.parent.lChild;
                // 叔叔节点为红色，调整颜色，继续向上回溯
                if (RED == uncle.color) {
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
     * 基点：待旋转的轴心点
     * 左旋转，即基点继承右孩子的左子树，右孩子继承基点的父关系，基点变成右孩子的左子树。
     */
    private synchronized void leftRotate(RBNode base) {
        // 使right引用指向base基点的右孩子。
        RBNode right = base.rChild;
        // 是base基点继承右孩子的左子树。
        base.rChild = right.lChild;
        // 如果right的左孩子不是哨兵。
        if (right.lChild != NIL) {
            // 使得right的左孩子的父指针域指向基点。
            right.lChild.parent = base;
        }
        // right继承base基点的父关系
        right.parent = base.parent;
        if (base.parent == NIL) {
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
    private synchronized void rightRotate(RBNode base) {
        // 基点继承左孩子的右子树，当作自己的左子树。
        RBNode left = base.lChild;
        base.lChild = left.rChild;
        if (left.rChild != NIL) {
            left.rChild.parent = base;
        }
        // 左孩子继承基点的父关系
        left.parent = base.parent;
        if (base.parent == NIL) {
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


    private static class RBNode<T> {
        private RBNode lChild;
        private RBNode rChild;
        private RBNode parent;
        private String key;
        private T value;
        private boolean color;

        public RBNode() {
        }

        public RBNode(RBNode lChild, RBNode rChild, RBNode parent, String key, T value, boolean color) {
            this.lChild = lChild;
            this.rChild = rChild;
            this.parent = parent;
            this.key = key;
            this.value = value;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RBNode<?> rbNode = (RBNode<?>) o;
            return Objects.equals(key, rbNode.key);
        }


    }

    /**
     * 删除
     */
    public void delByKey(String key) {
        if (key != null) {
            deleteNode(search(this.root, key));
        }
    }

    private RBNode search(RBNode x, String key) {
        while (x != NIL) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.lChild;
            else if (cmp > 0)
                x = x.rChild;
            else
                return x;
        }
        return x;
    }

    /**
     * 删除红黑树节点
     */
    public void deleteNode(RBNode z) {
        RBNode x;
        RBNode y = z;
        boolean yOriginalColor = y.color;
        if (z.lChild == this.NIL) {//左孩子为哨兵右孩子子树填
            x = z.rChild;//x为待删结点右子树根节点
            deleteTransplant(z, z.rChild);
        } else if (z.rChild == this.NIL) {//右孩子为哨兵左孩子子树填
            x = z.lChild;//x为待删结点左子树根节点
            deleteTransplant(z, z.lChild);
        } else {//左右都有孩子 找后继结点
            y = treeMinimum(z.rChild);//X指向后继节点的右孩子因为后继节点右孩子会继承后继节点的位置后继节点顶位待删除结点
            yOriginalColor = y.color;
            x = y.rChild;
            if (y.parent == z) x.parent = y;
            else {
                deleteTransplant(y, y.rChild);
                y.rChild = z.rChild;
                y.rChild.parent = y;
            }
            deleteTransplant(z, y);
            y.lChild = z.lChild;
            y.lChild.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK) {//查看删除的或顶位的结点颜色是否为黑  为黑检测
            delFixedUP(x);
        }
    }

    /**
     * 删除调整红黑树
     *
     * @param x
     */
    public void delFixedUP(RBNode x) {
        RBNode w;
        while (x != this.root && x.color == BLACK) {//x必须为双黑结点 不能为树根
            if (x == x.parent.lChild) {//x为父左的情况
                w = x.parent.rChild;
                if (w.color == RED) {//兄弟节点要是红色
                    w.color = BLACK;//把兄弟结点变黑
                    x.parent.color = RED;//父结点变红
                    leftRotate(x.parent);//左转 兄弟结点为X的爷爷结点
                    w = x.parent.rChild;//这时x的兄弟节点会变成原兄弟结点的左结点也就是当前父节点的右结点
                }
                if (w.lChild.color == BLACK && w.rChild.color == BLACK) { //兄弟节点为黑色并且的俩儿子都是黑色
                    w.color = RED;//这种情况必须削减兄弟节点所在树的黑色结点所以把兄弟节点变成红色
                    x = x.parent;//此时x结点为黑色右侧兄弟结点红色虽然右侧有两黑色子侄背儿
                    continue;//但是左右是平衡的所以x上升一辈跳出循环继续检测
                } else if (w.rChild.color == BLACK) { //如果兄弟结点左孩子红，右孩子黑   由于哨兵的缘故这里条件并没有写左孩子红，因为到这里
                    w.lChild.color = BLACK;//左孩子必须红  。  这种情况需要把w左孩子变黑 w变红右旋转
                    w.color = RED;
                    rightRotate(w);//更新w位置依然是x的兄弟结点
                    w = x.parent.rChild;
                }
                w.color = x.parent.color;//使以x.parent为中心左边加一黑颜色结点右边结点保持不变。
                x.parent.color = BLACK;//使左右平衡，使树满足红黑树性质。
                w.rChild.color = BLACK;
                leftRotate(x.parent);
                x = this.root;
            } else {
                w = x.parent.lChild;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.lChild;
                }

                if (w.rChild.color == BLACK && w.lChild.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                    continue;
                } else if (w.lChild.color == BLACK) {
                    w.rChild.color = BLACK;
                    w.color = RED;
                    leftRotate(w);
                    w = x.parent.lChild;
                }
                w.color = x.parent.color;
                x.parent.color = BLACK;
                w.lChild.color = BLACK;
                rightRotate(x.parent);
                x = this.root;
            }
        }
        x.color = BLACK;
    }

    /**
     * v为树根子树替换u为树根子树
     */
    public void deleteTransplant(RBNode u, RBNode v) {
        if (u.parent == this.NIL) this.root = v;
        else if (u == u.parent.lChild) u.parent.lChild = v;
        else u.parent.rChild = v;
        v.parent = u.parent;
    }

    /**
     * 沿着一个节点向左找最小的节点
     */
    public RBNode treeMinimum(RBNode x) {
        while (x.lChild != this.NIL) {
            x = x.lChild;
        }
        return x;
    }

}
