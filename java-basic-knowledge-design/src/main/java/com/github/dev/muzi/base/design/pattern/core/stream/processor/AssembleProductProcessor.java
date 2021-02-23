package com.github.dev.muzi.base.design.pattern.core.stream.processor;

import com.github.dev.muzi.base.design.pattern.core.stream.AbstractProcessor;
import com.github.dev.muzi.base.design.pattern.core.stream.model.ControlType;
import com.github.dev.muzi.base.design.pattern.core.stream.model.ProcessContext;
import com.github.dev.muzi.base.design.pattern.core.stream.ProcessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("assembleProductProcessor")
public class AssembleProductProcessor extends AbstractProcessor implements ProcessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssembleProductProcessor.class);


    @Override
    public ControlType process(ProcessContext processContext) {
        LOGGER.info("节点【{}】开始执行。", this.getName());

        LOGGER.info("节点【{}】执行结束。", this.getName());
        return ControlType.PASS;
    }
}
