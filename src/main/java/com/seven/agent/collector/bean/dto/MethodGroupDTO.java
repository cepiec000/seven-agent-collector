package com.seven.agent.collector.bean.dto;

public class MethodGroupDTO {
    private String methodName;
    private long count;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
