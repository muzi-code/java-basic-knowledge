package com.github.dev.muzi.base.design.pattern.core.singleton;


/***
 * 懒汉式线程安全，双重验证，保证只会出现一个实例
 * 推荐使用，获取实例不需要重复获取锁
 *
 * 这里面的关键是 INSTANCE 需要被声明为volatile ，线程对这个变量的写优先于读 ，防止重排序。
 *
 * new 关键字初始化变量不是一个原子性的操作
 * 1. 分配内存
 * 2. 调用构造函数初始化成员变量
 * 3. 将对象指向分配的内存的空间 （和c的指针指向malloc申请的空间是一样的）
 *
 * create by muzi 2019-05-09
 */
public class LazySingletonSafe02 {

    private volatile static LazySingletonSafe02 INSTANCE;

    private LazySingletonSafe02(){}

    public static LazySingletonSafe02 getInstance(){//双重验证保证安全
        if ( null == INSTANCE ){
            synchronized (LazySingletonSafe02.class){
                if ( null == INSTANCE ){
                    // tmp line 01
                    INSTANCE = new LazySingletonSafe02();
                }
            }
        }
        return INSTANCE;
    }
}
