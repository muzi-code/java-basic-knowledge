package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lifuyi8
 * @since 2021/2/21 1:11 下午
 */

public class LRUCache<K, V> {
    private int cacheSize;
    private LinkedHashMap<K, V> map;

    public LRUCache(int cacheSize) {
        float hashLoadFactory = 0.75f;
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LRUCache.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }
}
