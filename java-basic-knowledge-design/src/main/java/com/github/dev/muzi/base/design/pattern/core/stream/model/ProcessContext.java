package com.github.dev.muzi.base.design.pattern.core.stream.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 流水线处理上下文定义
 */
public class ProcessContext {

    private ProcessContext() {
    }

    public ProcessContext(Date beginTime, Date endTime, String requestId, Map<String, Object> content) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.requestId = requestId;
        this.content = content;
    }

    /**
     * 业务请求时间
     */
    private Date beginTime;

    /**
     * 业务结束时间
     */
    private Date endTime;

    /**
     * 任务ID
     */
    private String requestId;

    /**
     * 功能链类型
     */
    private FunctionType type;


    /**
     * 过程上下文信息容器
     */
    private Map<String, Object> content = new HashMap<>();


    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return content.get(key);
    }

    public void put(String key, Object obj) {
        if (StringUtils.isBlank(key) || obj == null) {
            return;
        }
        content.put(key, obj);
    }

    public static ProcessContext newInstance(String requestId) {
        if (StringUtils.isBlank(requestId)) {
            return null;
        }
        return new ProcessContext(new Date(), null, requestId, new HashMap<>());
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public FunctionType getType() {
        return type;
    }

    public void setType(FunctionType type) {
        this.type = type;
    }
}
