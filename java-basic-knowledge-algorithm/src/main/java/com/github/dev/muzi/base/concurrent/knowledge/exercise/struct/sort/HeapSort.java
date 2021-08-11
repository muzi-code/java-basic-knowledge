package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.sort;

import com.alibaba.fastjson.JSON;

public class HeapSort {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(defaultHeapSort(new int[]{5, 9, 44, 55, 23, 7, 12, 8})));
    }

    /**
     * 堆排序入口，需要传入一个长度大于等于2的数组
     * 如果小于2就别排序了，没啥用也
     */
    public static int[] defaultHeapSort(int[] array) {
        if (array.length < 2) {
            System.out.println("提示：数组长度小于2，由于我们使用1 - n的下标，不使用0 。");
            System.out.println("提示：所以一个元素进来一个元素出去我们就别脱裤子放屁了。");
            System.out.println("提示：将返回空值，恶心你一下！");
            return null;
        }
        heapSort(array);
        return array;
    }

    /**
     * 堆排序
     * 构建初始堆
     * 替换第一和最后的元素，堆长-1
     * 维护堆顶元素和堆的性质
     */
    private static int[] heapSort(int[] array) {
        int heapSize = array.length - 1;
        int temp = -1;
        createMaxHeap(array, heapSize);
        for (int i = array.length - 1; i > 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapSize = heapSize - 1;
            maintainHeapSort(array, 0, heapSize);
        }
        return array;
    }

    /**
     * 创建初始堆即从最后一个根结点向前遍历
     */
    private static void createMaxHeap(int[] array, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maintainHeapSort(array, i, heapSize);
        }
    }

    /**
     * 维护堆的性质，当前为大顶堆，也就是说左右孩子必须小于根。
     * 如果左右孩子不小于根，替换根和左右孩子中大的结点，并对该结点向下继续维护堆性质
     * 如果左右孩子都小于根那么就不用继续向下维护
     */
    private static void maintainHeapSort(int[] array, int index, int heapSize) {
        int lChild = leftChild(index);
        int rChild = rightChild(index);
        int largest = -1;
        int temp = -1;
        if (lChild <= heapSize && array[lChild] > array[index]) {
            largest = lChild;
        } else {
            largest = index;
        }
        if (rChild <= heapSize && array[rChild] > array[largest]) {
            largest = rChild;
        }
        if (largest != index) {
            temp = array[largest];
            array[largest] = array[index];
            array[index] = temp;
            maintainHeapSort(array, largest, heapSize);
        }
    }

    /**
     * 返回标号为index的结点的左孩子的下标
     */
    private static int leftChild(int index) {
        return ((index * 2) + 1);
    }

    /**
     * 返回标号为index的结点的右孩子的下标
     */
    private static int rightChild(int index) {
        return ((index * 2) + 2);
    }

    /**
     * 返回标号为index的结点的父结点的下标
     */
    private static int parent(int index) {
        return (index / 2) - 1;
    }
}
