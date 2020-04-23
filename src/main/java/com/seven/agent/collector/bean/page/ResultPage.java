package com.seven.agent.collector.bean.page;


import java.util.List;

public class ResultPage<T> {
    private int limit;
    private List<T> rows;
    private long total;

    public ResultPage(List<T> rows) {
        this.rows = rows;
    }

    public ResultPage(int limit, List<T> rows, long total) {
        this.limit = limit;
        this.rows = rows;
        this.total = total;
    }

    public ResultPage(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
