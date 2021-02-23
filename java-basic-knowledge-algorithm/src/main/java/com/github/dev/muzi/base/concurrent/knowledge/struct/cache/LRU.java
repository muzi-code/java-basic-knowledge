package com.github.dev.muzi.base.concurrent.knowledge.struct.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lifuyi8
 * @since 2021/2/21 1:11 下午
 */

class LRU<K, V> {

    public static void main(String[] args) {

        LRU<Integer, Integer> lru = new LRU<>(5);

        for (int i = 0; i < 5; i++) {
            lru.put(i, i);
        }

        //lru.get(0);

        lru.put(5, 5);

        System.out.println(lru.get(0));
        System.out.println(lru.get(1));
    }

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final float hashLoadFactory = 0.75f;

    private volatile LinkedHashMap<K, V> map;

    private int cacheSize;

    public LRU(int cacheSize) {
        this.cacheSize = cacheSize;

        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;

        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) {

            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {

                return size() > LRU.this.cacheSize;
            }
        };
    }

    public V get(K key) {
        lock.readLock().lock();
        V value = map.get(key);
        lock.readLock().unlock();
        return value;
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        map.put(key, value);
        lock.writeLock().unlock();
    }

    public void clear() {
        lock.writeLock().lock();
        map.clear();
        lock.writeLock();
    }

    public void remove(K key) {
        lock.writeLock().lock();
        map.remove(key);
        lock.writeLock();
    }

    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }
}
