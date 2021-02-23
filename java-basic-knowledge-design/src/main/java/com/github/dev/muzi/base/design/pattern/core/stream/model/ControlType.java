package com.github.dev.muzi.base.design.pattern.core.stream.model;

public enum ControlType {
    PASS(1, "继续处理请求"),
    CLOSE(2, "丢弃当前请求");

    private int code;

    private String remark;

    ControlType(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
