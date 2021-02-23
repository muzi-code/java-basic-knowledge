package com.github.dev.muzi.base.concurrent.knowledge.common;

import java.text.DecimalFormat;

/**
 * Create by muzi on 2019-07-18
 */
public class JVMUtils {


    public static String displayAvailableMemory() {
        StringBuilder stringBuilder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00") ;
        //显示JVM总内存
        long totalMem = Runtime.getRuntime().totalMemory();
        long maxMem = Runtime.getRuntime().maxMemory();
        long freeMem = Runtime.getRuntime().freeMemory();

        stringBuilder.append("当前JVM内存：");
        stringBuilder.append(df.format(totalMem/1024/1024));
        stringBuilder.append("MB  ,");

        stringBuilder.append("当前JVM空闲内存：");
        stringBuilder.append(df.format(freeMem/1024/1024));
        stringBuilder.append("MB  ,");

        stringBuilder.append("JVM可拿到最大内存：");
        stringBuilder.append(df.format(maxMem/1024/1024));
        stringBuilder.append("MB  。");
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        System.out.println(displayAvailableMemory());;
    }
}
