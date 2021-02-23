package com.github.dev.muzi.base.design.pattern.core.singleton;

/***
 * 懒汉式线程不安全，多线程情况下会有多个实例的情况
 *
 * create by muzi  2019-05-09
 */
public class LazySingletonUnsafe01 {

    private static LazySingletonUnsafe01 INSTANCE;

    private LazySingletonUnsafe01(){}

    public static LazySingletonUnsafe01 getInstance(){
        if ( null == INSTANCE ){                   //非原子操作 ，线程不安全
            // tmp line 01
            INSTANCE = new LazySingletonUnsafe01();
        }
        return INSTANCE;
    }
}
