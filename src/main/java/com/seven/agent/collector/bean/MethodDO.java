package com.seven.agent.collector.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "method")
public class MethodDO extends BaseDO{
    private String classFullName;
    private String className;
    private String methodName;
    private long countTime;

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
