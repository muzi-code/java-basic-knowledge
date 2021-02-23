package com.github.dev.muzi.base.design.pattern.core.stream;

import com.github.dev.muzi.base.design.pattern.core.stream.model.ControlType;
import com.github.dev.muzi.base.design.pattern.core.stream.model.DpQuery;
import com.github.dev.muzi.base.design.pattern.core.stream.model.ProcessContext;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;


@Service("functionDispatcher")
public class FunctionDispatcher {

    @Autowired
    private FunctionStreamContext functionStreamContext;

    public void dp(DpQuery dpQuery){
        Preconditions.checkNotNull(dpQuery);
        Preconditions.checkNotNull(dpQuery.getType());

        LinkedList<ProcessHandler> funcHandlerList = functionStreamContext.getFunctionList(dpQuery.getType().getCode());
        ProcessContext context = ProcessContext.newInstance("123");
        for (ProcessHandler handler: funcHandlerList) {
            ControlType type = handler.process(context);
            if (type == ControlType.CLOSE){
                break;
            }
        }

    }

}
