package com.github.dev.muzi.base.concurrent.knowledge.common;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    private ThreadUtils() {
    }

    public static void sleep(TimeUnit unit, Long num) {
        try {
            unit.sleep(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seconds(Long secondes) {
        try {
            TimeUnit.SECONDS.sleep(secondes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seconds(int secondes) {
        try {
            TimeUnit.SECONDS.sleep(secondes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
