package com.github.dev.muzi.base.design.pattern.core.stream;

import com.github.dev.muzi.base.design.pattern.core.stream.model.ControlType;
import com.github.dev.muzi.base.design.pattern.core.stream.model.ProcessContext;

public interface ProcessHandler {

    ControlType process(ProcessContext processContext);
}
