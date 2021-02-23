package com.github.dev.muzi.base.design.pattern.core.chain.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Pinus Li on 2019-12-13
 */
public class Client {

    public static IntentProcessorHandler handler;

    static {
        List<IntentProcessorHandler> handlers = new ArrayList<>();
        IntentPreprocessor preProcessor = new IntentPreprocessor();
        QueryAnswerProcessor queryProcessor = new QueryAnswerProcessor();
        AnswerPKProcessor pkProcessor = new AnswerPKProcessor();
        AnswerPushProcessor pushProcessor = new AnswerPushProcessor();
        handlers.add(preProcessor);
        handlers.add(queryProcessor);
        handlers.add(pkProcessor);
        handlers.add(pushProcessor);
        handler = IntentProcessorHandler.make(handlers);
    }

    public static void main(String[] args) {

        Client.handler.process("123");
    }
}
