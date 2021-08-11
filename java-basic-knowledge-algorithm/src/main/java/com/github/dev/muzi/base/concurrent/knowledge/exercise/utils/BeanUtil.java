package com.github.dev.muzi.base.concurrent.knowledge.exercise.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class BeanUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    private BeanUtil() {
    }


    /**
     * 利用org.apache.commons.beanutils.BeanUtils实现
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);
        return obj;
    }

    /**
     * 利用org.apache.commons.beanutils.BeanMap实现
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null)
            return null;
        Map<String, Object> map = null;
        try {
            map = BeanUtils.describe(obj);
        }catch (Exception e){
            LOGGER.info("实体对象转Map发生异常");
        }
        return map;
    }

}
