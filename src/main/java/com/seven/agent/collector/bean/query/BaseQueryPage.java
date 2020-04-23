package com.seven.agent.collector.bean.query;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

public class BaseQueryPage {
    private Integer rows;
    private Integer page;
    private String sort;
    private String sortOrder;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setOrderBy(Query query){
        if (!StringUtils.isEmpty(this.getSort())) {
            if ("asc".equals(this.getSortOrder())) {
                query.with(Sort.by(Sort.Direction.ASC,this.getSort()));
            }
            if ("desc".equals(this.getSortOrder())) {
                query.with(Sort.by(Sort.Direction.DESC,this.getSort()));
            }
        }else {
            query.with(Sort.by(Sort.Direction.DESC,"createTime"));
        }
    }

    public void setLimit(Query query){
        query.skip(this.page.longValue()-1);
        query.limit(this.getRows());
    }
}
