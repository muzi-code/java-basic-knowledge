package com.github.dev.muzi.base.concurrent.knowledge.struct.tree.binary;

public class AVLTree<E extends Comparable> {
    public AVLNode<E> treeRoot = null;

    private static final int LH = 1;    //左子树 - 右子树 = 1     左高
    private static final int EH = 0;    //左子树 - 右子树 = 0     左右子树同高
    private static final int RH = -1;   //左子树 - 右子树 = -1    右高

    /**
     * 插入值
     */
    public boolean insertAVL(E data){
        try {
            if(data == null) return false;
            System.out.println("提示：插入的数据为:" + data + "  2秒后开始插入！");
            Thread.sleep(10);
            return insertAVL(new AVLNode<E>(data));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入的方法
     * 如根节点为空插入到根节点，根结点不为空，比值找位置
     * 找到位置后插入，以插入的父结点开始向上回溯
     * 插入的数据小 父bf + 1 否则 bf --
     * 若节点的bf为0，不再向上调整BF值，
     * 若bf绝对值为2 则直接上检测台
     */
    private synchronized Boolean insertAVL(AVLNode<E> node){
        AVLNode<E> index = this.treeRoot;   //用作遍历
        AVLNode<E> parent = this.treeRoot;   //用作定位父结点
        int cmp = 0;                        //用于找寻插入位置
        if(this.treeRoot == null){      //如果树根是空的那就让node为根节点。结束当前方法。
            this.treeRoot = node;
            System.out.println("提示：树为空，已经被插入到树的根节点！");
            return true;
        }
        //找插入点
        while (index != null) {         //已排除树根是空的情况 如果index遍历后为空那么index就是插入位置
            parent = index;
            cmp = node.data.compareTo(index.data);
            if(cmp < 0){                //小于0就要去左子树中找
                index = index.lChild;
            }else if(cmp > 0){
                index = index.rChild;  //大于0就要去左子树中找
            }else{
                System.out.println("提示：插入的结点值与树中的重复，不必再次插入！");
                return false;
            }
        }

        node.parent = parent;           //parent为index的父亲，index的位置就是node需要进行插入的位置所以我们要将node插入
        if(cmp < 0){
            parent.lChild = node;
            System.out.println("提示：已经被插入到值为：" + parent.data + " 结点的左孩子");
        }else{
            parent.rChild = node;
            System.out.println("提示：已经被插入到值为：" + parent.data + " 结点的右孩子");
        }


        //自下向上回溯，查找最近不平衡节点
        while(parent!=null){
            cmp = node.data.compareTo(parent.data);
            if(cmp < 0){    //插入节点在parent的左子树中
                parent.bf++;
            }else{           //插入节点在parent的右子树中
                parent.bf--;
            }
            if(parent.bf == 0){    //此节点的balance为0，不再向上调整BF值，且不需要旋转
                break;
            }
            if(Math.abs(parent.bf) == 2){  //找到最小不平衡子树根节点
                System.out.println("提示：发生了不平衡，值为：" + parent.data + " 结点的平衡因子值为：" + parent.bf);
                insertFixedUP(parent);
                break;                  //不用继续向上回溯
            }
            parent = parent.parent;
        }
        return true;
    }

    /**
     * 调整的方法：
     * 1.unbalance为2时，即左子树高于右子树，leftLoseBalance(unbalance)：
     *
     * 2.unbalance为-2时，即右子树高于左子树，rightLoseBalance(unbalance);：
     */
    private synchronized Boolean insertFixedUP(AVLNode<E> unbalance){
        return (unbalance.bf == 2) ? leftLoseBalance(unbalance): rightLoseBalance(unbalance);
    }


    /**
     * 左边失衡的情况
     */
    private synchronized boolean leftLoseBalance(AVLNode<E> unbalance){
        System.out.println("提示：unbalance结点的左子树高于右子树。");
        AVLNode unbLChild = unbalance.lChild;
        if ( 1 == unbLChild.bf){
            System.out.println("提示：unbalance结点的左孩子平衡因子为1，调整平衡因子，以unbalance结点为基准点右旋转");
            unbalance.bf = unbLChild.bf = EH;   //unbLChild平衡因子为1  直接修改 unbLChild 和unbalance值为0
            rightRotateChange(unbalance);
        }else if (-1 == unbLChild.bf){
            System.out.println("提示：unbalance结点的左孩子unbLChild的平衡因子为-1，需要做多重考虑。");
            AVLNode<E> rd = unbLChild.rChild;
            switch (rd.bf) {   //调整各个节点的BF
                case LH:    //情况1
                    //rd值为1的话就是说  rd左子树高 左旋右旋转后  rd左子树会转到 unbLChild的右子树上 unbalance的右子树会比左子树高1
                    System.out.println("提示：情况一，unbLChild右孩子的bf值为1");
                    unbalance.bf = RH;  //这种情况下我们修改失衡节点的bf值为-1
                    unbLChild.bf = EH; //修改失衡节点的左孩子的值为0
                    break;
                case EH:    //情况2
                    System.out.println("提示：情况二，unbLChild右孩子的bf值为0");
                    unbalance.bf = unbLChild.bf = EH;  //rd值为0的话就是说 失衡节点没有右孩子 我们直接赋值unbalance和unbLChild为0
                    break;
                case RH:    //情况3
                    //rd值为-1的话就是说  rd右子树高 左旋右旋转后  rd右子树会转到 unbalance的左子树上 unbLChild左子树会比右子树高1
                    System.out.println("提示：情况三，unbLChild右孩子的bf值为-1");
                    unbalance.bf = EH;  //设0
                    unbLChild.bf = LH;  //设1
                    break;
            }
            rd.bf = EH;
            leftRotateChange(unbLChild);
            rightRotateChange(unbalance);
        }else if ( 0 == unbLChild.bf){
            unbLChild.bf = RH;
            unbalance.bf = LH;
            rightRotateChange(unbalance);
            return false;
        }
        return true;
    }

    /**
     * 右边失衡的情况
     */
    private synchronized boolean rightLoseBalance(AVLNode<E> unbalance){
        System.out.println("提示：unbalance结点的右子树高于左子树。");
        AVLNode unbRChild = unbalance.rChild;
        if( -1 == unbRChild.bf){
            System.out.println("提示：unbalance结点的右孩子平衡因子为-1，调整平衡因子，以unbalance结点为基准点左旋转");
            unbalance.bf = unbRChild.bf = EH;
            leftRotateChange(unbalance);
        }else if(  1 == unbRChild.bf){
            System.out.println("提示：unbalance结点的右孩子unbLChild的平衡因子为1，需要做多重考虑。");
            AVLNode<E> ld = unbRChild.lChild;
            switch (ld.bf) {   //调整各个节点的BF
                case LH:    //情况1
                    System.out.println("提示：情况一，unbRChild左孩子的bf值为1");
                    unbalance.bf = EH;
                    unbRChild.bf = RH;
                    break;
                case EH:    //情况2
                    System.out.println("提示：情况一，unbRChild左孩子的bf值为0");
                    unbalance.bf = unbRChild.bf = EH;
                    break;
                case RH:    //情况3
                    System.out.println("提示：情况一，unbRChild左孩子的bf值为-1");
                    unbalance.bf = LH;
                    unbRChild.bf = EH;
                    break;
            }
            ld.bf = EH;
            rightRotateChange(unbRChild);
            leftRotateChange(unbalance);
        }else if(  0 == unbRChild.bf){      //我再删除元素-在进行回溯时 找到失衡节点左子树<右子树失衡
            unbRChild.bf = LH;              //我们需要对失衡节点和他的的右孩子进行调整，由于左边失衡所以需要左旋转
            unbalance.bf = RH;              //右孩子如果本身为0，旋转过时必须先初始化为1 因为转过去左子树会嫁接到父结点的右子树上
            leftRotateChange(unbalance);    //因为原先为0，旋转后所以左比右高1，故如此设计。 而由于左子树本身就少1父结点左旋后右子树
            return false;           //变长了所以旋转前设置父结点为-1
        }
        return true;
    }

    /**
     * 左旋转 以node为基点
     */
    private synchronized void leftRotateChange(AVLNode<E> node){
        AVLNode<E> temp = node.rChild;
        node.rChild = temp.lChild;
        if(temp.lChild != null) temp.lChild = node;

        temp.parent = node.parent;
        if(node.parent == null) this.treeRoot = temp;
        else if(node == node.parent.lChild) node.parent.lChild = temp;
        else node.parent.rChild = temp;

        temp.lChild = node;
        node.parent = temp;
    }


    /**
     * 右旋转 node为基准点
     */
    private synchronized void rightRotateChange(AVLNode<E> node){
        AVLNode<E> temp = node.lChild;
        node.lChild = temp.rChild;
        if(temp.rChild != null) temp.rChild = node;

        temp.parent = node.parent;
        if(node.parent == null) this.treeRoot = temp;
        else if(node == node.parent.lChild) node.parent.lChild = temp;
        else node.parent.rChild = temp;

        temp.rChild = node;
        node.parent = temp;
    }

    /**
     * 删除avl树中 == data 的元素
     */
    public synchronized boolean deleteAVLNode(E data){
        if(data != null){
            deleteAVLNode(new AVLNode<E>(data));
            return true;
        }
        return false;
    }

    /**
     * 删除一个 == node 的元素
     */
    private void deleteAVLNode(AVLNode<E> node){
        AVLNode<E> indexNode = this.treeRoot;
        Integer comparaNum = null;
        AVLNode<E> unkonwnChild = null;
        while (indexNode != null){
            comparaNum = node.data.compareTo(indexNode.data);
            if(comparaNum == 0){
                break;
            }else if (comparaNum < 0){
                indexNode = indexNode.lChild;
            }else if(comparaNum > 0){
                indexNode = indexNode.rChild;
            }
        }
        if (indexNode == null){
            System.out.println("提示：您要找的数据在树里没有！");
            return;
        }

        //如果indexNode左右子树都不为空，找到其直接后继，替换indexNode，之后indexNode指向顶位结点replaceNode，删除indexNode其实是删除replaceNode
        if (indexNode.lChild != null && indexNode.rChild != null) {
            AVLNode<E> replaceNode = treeMinNode(indexNode.rChild);         //我需要找到一个顶位结点  直接后继或直接前驱
            if (replaceNode == null) replaceNode = treeMaxNode(indexNode.lChild);

            indexNode.data = replaceNode.data;                      //我把待删除结点值替换成顶2位结点的值
            indexNode = replaceNode;                                //此时indexNode指向了顶位结点，也就是把要删除的对象变成了顶位结点
        }                                                           //顶位结点也就是后继结点
        //此时顶位结点也可能发生以下几种情况
        //我们用一个变量来保存 index
        unkonwnChild = (indexNode.lChild != null ? indexNode.lChild : indexNode.rChild);
        if(indexNode.lChild == null && indexNode.rChild == null){  //左右子树都为空的情况下
            deleteFixedUP(indexNode);    //从indexNode开始回溯
            if (indexNode.parent != null) {
                if (indexNode == indexNode.parent.lChild)
                    indexNode.parent.lChild = null;
                else if (indexNode == indexNode.parent.rChild)
                    indexNode.parent.rChild = null;
                indexNode.parent = null;
            }
        }else if (indexNode.lChild != null || indexNode.rChild != null) {  //如果其左右子树有其一不为空
            unkonwnChild.parent = indexNode.parent;
            if (indexNode.parent == null)                   //如果indexNode为root节点
                this.treeRoot = unkonwnChild;
            else if (indexNode == indexNode.parent.lChild)    //如果indexNode是左孩子
                indexNode.parent.lChild  = unkonwnChild;
            else                                                //如果indexNode是右孩子
                indexNode.parent.rChild = unkonwnChild;

            indexNode.lChild = indexNode.rChild = indexNode.parent = null;     //p的指针清空

            //这里更改了unkonwnChild的父节点，所以可以直接从它开始向上回溯
            deleteFixedUP(unkonwnChild);

        } else{ // 如果全树只有一个节点
            System.out.println("提示：树就剩一个结点也给删了！");
            this.treeRoot = null;
        }
    }

    /**
     * 删除某节点p后的调整方法：
     * 1.从p开始向上回溯，修改祖先的BF值，这里只要调整从p的父节点到根节点的BF值，
     * 调整原则为，当p位于某祖先节点(简称A)的左子树中时，A的BF减1，当p位于A的
     * 右子树中时A的BF加1。当某个祖先节点BF变为1或-1时停止回溯，这里与插入是相反的，
     * 因为原本这个节点是平衡的，删除它的子树的某个节点并不会改变它的高度
     *
     * 2.检查每个节点的BF值，如果为2或-2需要进行旋转调整，调整方法如下文，
     * 如果调整之后这个最小子树的高度降低了，那么必须继续从这个最小子树的根节点(假设为B)继续
     * 向上回溯，这里和插入不一样，因为B的父节点的平衡性因为其子树B的高度的改变而发生了改变，
     * 那么就可能需要调整，所以删除可能进行多次的调整。
     */
    private synchronized void deleteFixedUP(AVLNode<E> node){
        boolean heightLower = true;     //看最小子树调整后，它的高度是否发生变化，如果减小，继续回溯
        AVLNode<E> nodeParent = node.parent;
        int cmp;
        //自下向上回溯，查找不平衡的节点进行调整
        while(nodeParent!=null && heightLower){
            cmp = node.data.compareTo(nodeParent.data); //需要回溯 的结点和其父结点比较
            /**
             * 删除的节点是右子树，等于的话，必然是删除的某个节点的左右子树不为空的情况
             * 例如：     10
             *          /    \
             *         5     15
             *       /   \
             *      3    6
             * 这里删除5，是把6的值赋给5，然后删除6，这里6是p，p的父节点的值也是6。
             * 而这也是右子树的一种
             */
            if(cmp >= 0 ){  //如果大于或等于他的父结点那么我们如果删除了它 父结点的右子树会少一个元素
                nodeParent.bf++; //所以给父元素的bf值+1
            }else{              //如果小于父结点
                nodeParent.bf--;//左子树少了那就给它的父元素 -1
            }
            if(Math.abs(nodeParent.bf) == 1){   //父节点经过调整平衡因子后，如果为1或-1，说明调整之前是0，停止回溯。
                break;  //也就是原来是0调整后最多就是±1   不需要继续回溯了
            }
            AVLNode<E> unbalance = nodeParent;//如果父元素的平衡因子一旦等于2 或 -2那么我们就必须要去调整元素的位置使得树重新回到平衡状态
            if(nodeParent.bf == 2){     //父元素bf == 2  左子树远大于右子树失衡  我们需要对失衡点进行调整
                heightLower = leftLoseBalance(unbalance);
            }else if(nodeParent.bf == -2){     //父元素bf == -2  右子树远大于左子树失衡  我们需要对失衡点进行调整
                heightLower = rightLoseBalance(unbalance);
            }
            nodeParent = nodeParent.parent;
        }
    }

    /**
     * 沿着一个节点向左找最小
     */
    private AVLNode<E> treeMinNode(AVLNode<E> x){
        while (x.lChild != null){
            x = x.lChild;
        }
        return x;
    }

    /**
     * 沿着一个节点向右找最大
     */
    private AVLNode<E> treeMaxNode(AVLNode<E> x){
        while (x.rChild != null){
            x = x.rChild;
        }
        return x;
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrderErgodic(AVLNode<E> node){
        if(node == null){
            return;
        }
        System.out.println("数据：" + node.data + " bf值：" + node.bf );
        preOrderErgodic(node.lChild);
        preOrderErgodic(node.rChild);
    }

    /**
     * 静态内部类AVLNode avl树所使用的结点类
     */
    private static class AVLNode<E extends Comparable>{
        public AVLNode<E> lChild = null;
        public AVLNode<E> rChild = null;
        public AVLNode<E> parent = null;
        public E data = null;
        public Integer bf = 0;

        public AVLNode(){}

        public AVLNode(E data){
            this.data = data;
        }
        public AVLNode(E data,Integer bf){
            this.data = data;
            this.bf = bf;
        }
        public AVLNode(E data, AVLNode<E> parent){
            this.data = data;
            this.parent = parent;
        }
    }


}