package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author lifuyi8
 * @since 2021/5/31 10:10 下午
 */
public class BubbleSortTool {

    public static <T extends Comparable> List<T> bubbleSort(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        } else if (list.size() == 1) {
            return list;
        }
        return bubbleSortCore(list);
    }

    private static <T extends Comparable> List<T> bubbleSortCore(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - (i + 1); j++) {
                T front = list.get(j);
                T after = list.get(j + 1);
                if (front.compareTo(after) > 0) {
                    list.set(j, after);
                    list.set(j + 1, front);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(
                Arrays.asList(12, 53, 78, 6, 4, 29, 13, 26, 78, 33, 19, 14, 25)
        );
        bubbleSort(list);
        for (Integer value : list) {
            System.out.print(value + " ");
        }
    }

}
