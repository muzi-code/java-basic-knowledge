package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync.wn;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * create by muzi 2019-06-29
 *
 * wait和notifyAll的使用问题：数据库连接池
 */
public class DBPool {

    /**
     * 容器
     */
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    /**
     * 限制池的大小
     */
    public DBPool(int initialSize){
        if (initialSize > 0){
            for (int i = 0; i < initialSize ; i++) {
                pool.add(ConnectionImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放链接,通知其他等待的线程
     */
    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取链接
     */
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if (mills < 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                //超时时刻
                long future = System.currentTimeMillis() + mills;
                //等待时长
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    //重新计算等待时长
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
