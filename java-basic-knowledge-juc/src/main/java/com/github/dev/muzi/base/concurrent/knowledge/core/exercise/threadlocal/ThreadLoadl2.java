package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;
import lombok.Data;

public class ThreadLoadl2 {
    private static class MyThreadLocal extends ThreadLocal<Person> {
        @Override
        protected Person initialValue() {
            return new Person();
        }
    }
    static ThreadLocal<Person> tl  = new MyThreadLocal();


    public static void main(String[] args) {


        new Thread(()->{
            ThreadUtils.seconds(3);
            System.out.println(tl.get().getName());
        }).start();

        new Thread(()->{
            ThreadUtils.seconds(1);
            tl.get().setName("lisi");
            System.out.println(tl.get().getName());

        }).start();
    }


}
