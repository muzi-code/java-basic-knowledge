package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.sort;

public class NSquareSort {

    /**
     * 起泡排序/沉底排序/冒泡排序
     *   i  i+1
     *   7  16   9  11  16  18    7 - 16 不换
     *       i  i+1
     *   7  16   9  11  16  18    16 - 9  换
     *           i  i+1
     *   7  9   16  11  16  18     16 - 11 换
     *               i  i+1
     *   7  9   11  16  16  18      16 - 16 不换
     *   ------------------------------ 准备从7执行下一个起泡的循环
     *   7  9   11  16  16  18
     */
    public static int[] bubbleOrder(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }


    /**
     * 插入排序，返回一个排序好的数组
     * 提出key = array[i] = 13
     *           j   i       j与key比较 j大把j给j+1然后j--
     *   7  16  18  13  11  8  2  16  1
     *       j       i       j再与key比较  j大把j给j+1然后j--
     *   7  16  18  18  11  8  2  16  1
     *   j           i       j再与key比较  key大跳出循环  把key给j+1 然后插入成功
     *   7  16  16  18  11  8  2  16  1
     *   ----------------i------------- 准备执行下一个i 的插入
     *   7  13  16  18  11  8  2  16  1
     */
    public static int[] insertOrder(int[] array){
        int key = 0;
        for(int i = 1 ; i<array.length ; i++){
            key = array[i];
            int j;
            for(j = i-1 ; (j >= 0 && array[j] > key ) ; j--){
                array[j+1] = array[j];
            }
            array[j+1] = key;
        }
        return array;
    }


    /**
     * 选择排序，找到最小的，记录下标
     * index = i          //index记录最小的下标
     *     j              如果array[j]小于array[index] 那么就用index记录最小的下标  index = 1
     * (3) 2 7 6 8 9
     *         j          //7大不记录
     * 3 (2） 7 6 8 9
     *                    知道j走完了  有小的就用index记录下标
     * 3 (2) 7 6 8 9
     *                    最后把array[index]和array{i}换位置
     * （2） 3 7 6 8 9
     */
    public static int[] selectOrder(int[] array){
        int index,temp;
        int time = array.length - 1;
        for(int i = 0 ; i < time ; i++){
            index = i;
            for(int j = i+1;j<array.length;j++){
                if(array[index] > array[j]){
                    index = j;
                }
            }
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
            ++index;
        }
        return array;
    }


    /**
     * 希尔排序  传入数组和增量序列倍数
     */
    public static int[] shellOrder(int[] array,int addNum){
        int temp;
        int gap = 1;
        while(gap < array.length / addNum) {              //计算最大的gap值
            gap = gap * addNum+1;
        }
        while (gap > 0){                                // 荡循环，维护gap
            for (int i = gap; i < array.length; i++) {  // 因gap位之前的元素都是每个组的第一位
                temp = array[i];                        // 从gap位开始 那么我们就从gap位开始对个元素做插入排序
                int j;                                  // 可能会问 每个元素所在的组不一样，怎么就插入了
                for (j = i - gap; j >= 0 && array[j]> temp; j -= gap) { //正是因为每个元素所在的组不一样  那么我们在对每个元素做插入排序的时候
                    array[j + gap] = array[j];                        //遍历的都是相隔gap位的元素，所以相隔gap位的元素都是一组的
                }                                                   //做插入排序
                array[j + gap] = temp;
            }
            gap = (int)Math.floor(gap / addNum);
        }
        return array;
    }


}
