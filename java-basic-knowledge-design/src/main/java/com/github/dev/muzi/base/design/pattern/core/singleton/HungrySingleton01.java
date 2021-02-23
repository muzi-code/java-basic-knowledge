package com.github.dev.muzi.base.design.pattern.core.singleton;

/***
 * 饿汉式，初始化默认final单例bean
 *
 * create by muzi  2019-05-09
 */
public class HungrySingleton01 {
    private final static HungrySingleton01 INSTANCE = new HungrySingleton01();

    private HungrySingleton01(){}

    public static HungrySingleton01 getInstance(){
        return INSTANCE;
    }
}


