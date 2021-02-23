package com.github.dev.muzi.base.design.pattern.core.stream.model;


public enum FunctionType {
    TEST("1", "测试功能链处理");

    private String code;

    private String remark;

    FunctionType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UploadType{" +
                "code=" + code +
                ", remark='" + remark + '\'' +
                '}';
    }
}
