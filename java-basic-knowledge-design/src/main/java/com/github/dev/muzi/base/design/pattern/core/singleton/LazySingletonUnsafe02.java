package com.github.dev.muzi.base.design.pattern.core.singleton;

/***
 * 懒汉式线程不安全 ，未保证判断和创建的整体的原子性
 *
 * create by muzi  2019-05-09
 */
public class LazySingletonUnsafe02 {

    private  static LazySingletonUnsafe02 INSTANCE;

    private LazySingletonUnsafe02(){}

    public static LazySingletonUnsafe02 getInstance(){
        if ( null == INSTANCE ){ //同样只要有两个线程同时执行到判断语句，也会产生多个实例
            synchronized (LazySingletonUnsafe02.class){ //只能保证同一时间只有一个线程创建实例
                INSTANCE = new LazySingletonUnsafe02();
            }
        }
        return INSTANCE;
    }
}
