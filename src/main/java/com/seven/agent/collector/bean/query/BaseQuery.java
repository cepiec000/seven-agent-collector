package com.seven.agent.collector.bean.query;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class BaseQuery extends BaseQueryPage {
    private String appName;
    private Long startTime;
    private Long endTime;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Query getBaseQuery() {
        Query query = new Query();
        if (Objects.nonNull(this.appName)) {
            query.addCriteria(Criteria.where("appName").is(this.appName));
        }
        if (Objects.nonNull(this.startTime)) {
            query.addCriteria(Criteria.where("createTime").gte(this.startTime));
        }
        if (Objects.nonNull(this.endTime)) {
            query.addCriteria(Criteria.where("createTime").lte(this.endTime));
        }
        return query;
    }
}
