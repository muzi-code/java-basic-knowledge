package com.github.dev.muzi.base.concurrent.knowledge.exercise;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class Exercise08_CollectionJoin {
    /**
     * 动态规划思想，每次迭代使用之前处理好的结果
     */
    private static List<Integer> join(List<List<Integer>> lists){
        if (CollectionUtils.isEmpty(lists)){
            return Lists.newArrayList();
        }
        List<Integer> res = lists.get(0);
        for (int i = 1; i < lists.size() ; i++) {
            List<Integer> temp = Lists.newArrayList();
            List<Integer> now = lists.get(i);
            for (int j = 0; j < res.size() ; j++) {
                Integer idxValue = res.get(j);
                if (now.contains(idxValue)){
                    temp.add(idxValue);
                }
            }
            res = temp;
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> coll01 = Lists.newArrayList(Arrays.asList(3,6,9,12,15));
        List<Integer> coll02 = Lists.newArrayList(Arrays.asList(4,6,8,12,17));
        List<Integer> coll03 = Lists.newArrayList(Arrays.asList(2,6,9,13,18));

        List<List<Integer>> lists = Lists.newArrayList();
        lists.add(coll01);
        lists.add(coll02);
        lists.add(coll03);


        List<Integer> res = join(lists);

        // 预期结果输出 [6]
        System.out.println(res);
    }
}
