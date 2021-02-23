package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.threadpool;

import java.util.concurrent.Executor;

/**
 * Executor 执行器  只有execute方法
 */
public class T01_MyExecutor implements Executor {

    /*
     * 可以直接run 也可以创建线程去执行
     */
    @Override
    public void execute(Runnable command) {
        command.run();
       // new Thread(command).start();
    }


    public static void main(String[] args) {
        new T01_MyExecutor().execute(()->{
            System.out.println("content print");
        });
    }
}
