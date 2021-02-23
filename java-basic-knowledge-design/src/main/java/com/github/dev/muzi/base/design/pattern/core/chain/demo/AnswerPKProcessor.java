package com.github.dev.muzi.base.design.pattern.core.chain.demo;

/*
 * 意图PK处理器
 * Create by Pinus Li on 2019-12-13
 */
public class AnswerPKProcessor extends IntentProcessorHandler  {

    @Override
    public void handleRequest(Object request) {
        System.out.println("答案PK");
    }
}
