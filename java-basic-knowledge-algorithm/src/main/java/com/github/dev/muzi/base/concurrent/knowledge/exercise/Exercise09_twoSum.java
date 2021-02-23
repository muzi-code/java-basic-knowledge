package com.github.dev.muzi.base.concurrent.knowledge.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class Exercise09_twoSum {


    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> container = new HashMap<>(nums.length * 2);

        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (!container.containsKey(find)) {
                container.put(nums[i], i);
                continue;
            }
            return new int[]{container.get(find), i};
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
