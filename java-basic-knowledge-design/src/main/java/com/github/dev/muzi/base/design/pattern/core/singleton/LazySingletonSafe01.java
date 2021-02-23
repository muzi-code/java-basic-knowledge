package com.github.dev.muzi.base.design.pattern.core.singleton;


/***
 * 懒汉式线程安全，不推荐使用，每次都需要获取锁，耗时。
 *
 * create by muzi  2019-05-09
 */
public class LazySingletonSafe01 {

    private  static LazySingletonSafe01 INSTANCE;

    private LazySingletonSafe01(){}

    public static synchronized LazySingletonSafe01 getInstance(){
        if ( null == INSTANCE ){
            INSTANCE = new LazySingletonSafe01();
        }
        return INSTANCE;
    }
}
