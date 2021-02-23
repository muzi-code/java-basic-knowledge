package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync.wn;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;

public class TestExpress {


    private static Express express = new Express(0,Express.CITY);

    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3 ; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i < 3 ; i++) {
            new CheckKm().start();
        }

        ThreadUtils.seconds(1);
        express.changeKm();
        express.changeSite();
    }
}
