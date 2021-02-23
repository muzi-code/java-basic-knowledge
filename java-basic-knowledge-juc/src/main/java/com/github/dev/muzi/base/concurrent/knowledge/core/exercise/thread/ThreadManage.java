package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * create by muzi 2019-06-28
 *
 * 为什么说Java天生就是多线程的？
 */
public class ThreadManage {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo info: threadInfos) {
            System.out.println("["+ info.getThreadId() + "] " + info.getThreadName());
        }
    }
}
