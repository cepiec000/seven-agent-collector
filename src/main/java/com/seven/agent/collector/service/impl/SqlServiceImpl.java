package com.seven.agent.collector.service.impl;

import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.SqlQuery;
import com.seven.agent.collector.bean.result.SqlResult;
import com.seven.agent.collector.service.ISqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SqlServiceImpl implements ISqlService {
    private static String SQL_TABLE="sql";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public ResultPage querySqlPage(SqlQuery sqlQuery) {
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(sqlQuery.getAppName())) {
            criteria.and("appName").is(sqlQuery.getAppName());
        }
        if (!StringUtils.isEmpty(sqlQuery.getSql())) {
            criteria.and("sql").regex(".*"+sqlQuery.getSql()+".*");

        }
        SortOperation sortOperation= Aggregation.sort(Sort.Direction.DESC, "avgTime");
        if (!StringUtils.isEmpty( sqlQuery.getSort())){
            if ("asc".equals(sqlQuery.getSortOrder())){
                sortOperation=Aggregation.sort(Sort.Direction.ASC,sqlQuery.getSort());
            }
            if ("desc".equals(sqlQuery.getSortOrder())){
                sortOperation=Aggregation.sort(Sort.Direction.DESC,sqlQuery.getSort());
            }
        }
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project("appName", "sql", "countTime"),
                Aggregation.group("appName", "sql").
                        max("countTime").as("slowestTime").
                        min("countTime").as("fastTime").
                        avg("countTime").as("avgTime").
                        count().as("count"),
                sortOperation,
                Aggregation.skip(sqlQuery.getPage().longValue()-1),
                Aggregation.limit(sqlQuery.getRows())
        );
        List<SqlResult> list=null;
        AggregationResults<SqlResult> countResult=mongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(criteria),Aggregation.group("appName","sql")),"sql",SqlResult.class);
        if (countResult.getMappedResults()!=null && countResult.getMappedResults().size()>0) {
            AggregationResults<SqlResult> results = mongoTemplate.aggregate(aggregation, SQL_TABLE, SqlResult.class);
            list = results.getMappedResults();
        }
        return new ResultPage<SqlResult>(sqlQuery.getRows(),list,countResult.getMappedResults().size());
    }
}
