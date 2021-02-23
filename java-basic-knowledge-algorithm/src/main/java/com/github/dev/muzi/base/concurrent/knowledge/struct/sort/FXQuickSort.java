package com.github.dev.muzi.base.concurrent.knowledge.struct.sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * create by lifuyi7  2020-07-23
 */
public class FXQuickSort {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(
                Arrays.asList(12, 53, 78, 6, 4, 29, 13, 26, 78, 33, 19, 14, 25)
        );
        quickSort(list);
        for (Integer value : list) {
            System.out.print(value + " ");
        }
    }

    public static <T extends Comparable> List<T> quickSort(List<T> list){
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        } else if (list.size() == 1) {
            return list;
        }
        return quickSort(list,0,list.size() - 1);
    }

    private static <T extends Comparable> List<T> quickSort(List<T> list, int pre, int rear) {
        int minNumber;
        if (pre < rear) {
            minNumber = quickSortPartition(list, pre, rear);
            quickSort(list, pre, minNumber - 1);
            quickSort(list, minNumber + 1, rear);
        }
        return list;
    }

    private static <T extends Comparable> int quickSortPartition(List<T> list, int pre, int rear) {
        T mainObject = list.get(rear);
        int index = pre - 1;

        for (int j = pre; j < rear; j++) {

            if (list.get(j).compareTo(mainObject) <= 0) {
                index++;
                exchangeElem(list, index, j);
            }
        }
        exchangeElem(list, ++index, rear);
        return index;
    }

    private static <T extends Comparable> void exchangeElem(List<T> list, int x, int y) {
        T temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}
