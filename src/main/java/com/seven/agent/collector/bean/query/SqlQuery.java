package com.seven.agent.collector.bean.query;

public class SqlQuery extends BaseQuery {
    public String sqlFirst;
    private String sql;
    public long thanEqual;
    public long countBe;
    public long countTo;

    public String getSqlFirst() {
        return sqlFirst;
    }

    public void setSqlFirst(String sqlFirst) {
        this.sqlFirst = sqlFirst;
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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
