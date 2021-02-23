package com.github.dev.muzi.base.design.pattern.core.singleton;

/***
 * 静态内部类实现方式
 *
 * create by muzi  2019-05-09
 */
public class StaticSingleton {

    private StaticSingleton(){}

    private static class SingletonFactory{
        public static final StaticSingleton INSTANCE = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonFactory.INSTANCE;
    }
}
