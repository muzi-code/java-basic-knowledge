package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadlocal;


import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;
import lombok.Data;

public class ThreadLocal1 {
    volatile static Person person = new Person();

    public static void main(String[] args) {


        new Thread(()->{
            ThreadUtils.seconds(3);
            System.out.println(person.getName());
        }).start();

        new Thread(()->{
            ThreadUtils.seconds(1);
            person.setName("lisi");
        }).start();
    }


}

@Data
class Person{
    private String name = "zhangsan";
}