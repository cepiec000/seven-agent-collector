package com.seven.agent.collector.service.impl;

import com.seven.agent.collector.bean.LoggerDO;
import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.LoggerQuery;
import com.seven.agent.collector.service.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class LoggerServiceImpl implements ILoggerService {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResultPage<LoggerDO> queryLoggerByPage(LoggerQuery loggerQuery) {
        Query query = new Query();
        if (!StringUtils.isEmpty(loggerQuery.getAppName())) {
            query.addCriteria(Criteria.where("appName").is(loggerQuery.getAppName()));
        }
        if (!StringUtils.isEmpty(loggerQuery.getLevel())) {
            query.addCriteria(Criteria.where("level").is(loggerQuery.getLevel()));
        }
        if (!StringUtils.isEmpty(loggerQuery.getMessage())) {
            Pattern pattern = Pattern.compile("^.*" + loggerQuery.getMessage() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("message").regex(pattern));
        }
        loggerQuery.setOrderBy(query);
        loggerQuery.setLimit(query);
        long count = mongoTemplate.count(query, LoggerDO.class);
        List<LoggerDO> list = mongoTemplate.find(query, LoggerDO.class);
        return new ResultPage<LoggerDO>(loggerQuery.getRows(), list, count);
    }
}
