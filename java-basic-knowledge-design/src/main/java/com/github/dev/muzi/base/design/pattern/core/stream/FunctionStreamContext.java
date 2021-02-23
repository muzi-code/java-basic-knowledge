package com.github.dev.muzi.base.design.pattern.core.stream;

import com.github.dev.muzi.base.design.pattern.core.stream.model.FunctionType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Map;

@Service("functionStreamContext")
public class FunctionStreamContext {

    @Autowired
    private ApplicationContext context;

    private Map<String, LinkedList<ProcessHandler>> handlerMap;

    @PostConstruct
    private void postConstruct() {
        handlerMap = Maps.newHashMap();

        // 初始化受限上传的功能链条
        initTestFunctionLinked();
    }

    private void initTestFunctionLinked() {
        AbstractProcessor pre = (AbstractProcessor) context.getBean("preDataCollectProcessor");
        pre.setName("数据收集处理器");
        AbstractProcessor assemble = (AbstractProcessor) context.getBean("assembleProductProcessor");
        assemble.setName("组装商品处理器");
        AbstractProcessor post = (AbstractProcessor) context.getBean("postPushProcessor");
        post.setName("推送业务处理器");

        LinkedList<ProcessHandler> list = Lists.newLinkedList();
        list.addLast(pre);
        list.addLast(assemble);
        list.addLast(post);
        handlerMap.put(FunctionType.TEST.getCode(), list);
    }

    public LinkedList<ProcessHandler> getFunctionList(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return handlerMap.get(key);
    }

}
