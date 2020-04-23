package com.seven.agent.collector.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sql")
public class SqlDO extends BaseDO{
    private String sql;
    private long countTime;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }
}
