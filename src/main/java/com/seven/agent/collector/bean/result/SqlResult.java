package com.seven.agent.collector.bean.result;

public class SqlResult {
    private String appName;
    private String sql;
    private Long count;
    private Long avgTime;
    private Long fastTime;
    private Long slowestTime;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Long avgTime) {
        this.avgTime = avgTime;
    }

    public Long getFastTime() {
        return fastTime;
    }

    public void setFastTime(Long fastTime) {
        this.fastTime = fastTime;
    }

    public Long getSlowestTime() {
        return slowestTime;
    }

    public void setSlowestTime(Long slowestTime) {
        this.slowestTime = slowestTime;
    }
}
