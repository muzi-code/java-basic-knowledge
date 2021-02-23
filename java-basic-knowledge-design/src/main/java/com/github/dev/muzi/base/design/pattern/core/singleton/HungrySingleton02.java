package com.github.dev.muzi.base.design.pattern.core.singleton;


/***
 * 饿汉式，静态代码块创建单例。
 *
 * create by muzi  2019-05-09
 */
public class HungrySingleton02 {

    private static HungrySingleton02 INSTANCE ;

    static {
        INSTANCE = new HungrySingleton02();
    }

    private HungrySingleton02(){}

    public static HungrySingleton02 getInstance(){
        return INSTANCE;
    }
}
