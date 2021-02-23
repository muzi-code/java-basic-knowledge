package com.github.dev.muzi.base.concurrent.knowledge.struct.cache;

import com.alibaba.fastjson.JSON;
import com.github.dev.muzi.base.algorithm.knowledge.utils.BeanUtil;

import java.util.Map;

public class ExecTest {

    public static void main(String[] args) {
        RBTree<Integer> rb = new RBTree<>();

        for (int i = 0; i < 100; i++) {
            rb.add("" + i, i);
        }

        Map map = BeanUtil.objectToMap(rb);
        System.out.println(JSON.toJSONString(map));
    }
}
