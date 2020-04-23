package com.seven.agent.collector.bean.query;

public class MethodQuery extends BaseQuery{
    private String className;
    private String methodName;
    private long thanEqual;
    private long countBe;
    private long countTo;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getThanEqual() {
        return thanEqual;
    }

    public void setThanEqual(long thanEqual) {
        this.thanEqual = thanEqual;
    }

    public long getCountBe() {
        return countBe;
    }

    public void setCountBe(long countBe) {
        this.countBe = countBe;
    }

    public long getCountTo() {
        return countTo;
    }

    public void setCountTo(long countTo) {
        this.countTo = countTo;
    }
}
