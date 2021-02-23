package com.github.dev.muzi.base.design.pattern.core.chain.demo;

import java.util.List;

/**
 * 【抽象类】意图处理器
 * Create by Pinus Li on 2019-12-13
 */
public abstract class IntentProcessorHandler {

    private IntentProcessorHandler next;

    /*
     * 在执行构造链条时需要这个set方法
     */
    public static IntentProcessorHandler make(List<IntentProcessorHandler> handlerList) {
        IntentProcessorHandler first = null;
        if (handlerList != null && handlerList.size() > 0){
            first = handlerList.get(0);
            for (int i = 0; i < handlerList.size() - 1 ; i++) {
                IntentProcessorHandler now = handlerList.get(i);
                IntentProcessorHandler next = handlerList.get(i + 1);
                now.setNext(next);
            }
        }
        return first;
    }

    /*
     * 对节点本身而言它只需要处理好自己的事情，无需知道下一个节点是谁。
     */
    private IntentProcessorHandler getNext() {
        return next;
    }

    private void setNext(IntentProcessorHandler next){
        this.next = next;
    }

    /*
     * 链的迭代条件
     */
    public void process(Object request) {
        handleRequest(request);

        if (getNext() != null) {
            getNext().process(request);
        }
    }

    /*
     * 用户自己实现的请求处理方法
     */
    protected abstract void handleRequest(Object request);

    private void next(Object request) {
        if (getNext() != null) getNext().handleRequest(request);
    }
}
