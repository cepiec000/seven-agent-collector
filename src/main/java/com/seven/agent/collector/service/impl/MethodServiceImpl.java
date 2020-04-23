package com.seven.agent.collector.service.impl;

import com.alibaba.fastjson.JSON;
import com.seven.agent.collector.bean.MethodDO;
import com.seven.agent.collector.bean.result.MethodResult;
import com.seven.agent.collector.exception.AgentCollectorException;
import com.seven.agent.collector.service.IMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MethodServiceImpl implements IMethodService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static String METHOD_TABLE = "method";

    public Map queryMinuteCountData(String appName, String className, String methodName, long reTime, int topNum) {
        if (StringUtils.isEmpty(appName)) {
            throw new AgentCollectorException("appName is required ");
        }
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(appName)) {
            criteria.and("appName").is(appName);
        }
        if (!StringUtils.isEmpty(className)) {
            criteria.and("className").is(className);
        }
        if (!StringUtils.isEmpty(methodName)) {
            criteria.and("methodName").is(methodName);
        }
        criteria.and("createTime").gte(System.currentTimeMillis() - milliSeconds(reTime));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project("appName", "className", "methodName").and(DateOperators.DateToString.dateOf("date").toString("%Y-%m-%d %H:%M").withTimezone(DateOperators.Timezone.valueOf("Asia/Shanghai"))).as("createDate"),
                Aggregation.project("appName", "className", "methodName", "createDate"),
                Aggregation.group("appName", "className", "methodName", "createDate").count().as("count"),
                Aggregation.sort(Sort.Direction.ASC, "createDate"),
                Aggregation.match(Criteria.where("count").gt(0L)),
                Aggregation.skip(0L),
                Aggregation.limit(topNum)
        );
        AggregationResults<MethodResult> results = mongoTemplate.aggregate(aggregation, METHOD_TABLE, MethodResult.class);
        List<MethodResult> list = results.getMappedResults();
        return loadMethodMinuteCountData(list);
    }

    @Override
    public Map queryCountData(String appName, String className, String methodName, long reTime, int topNum) {
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(appName)) {
            criteria.and("appName").is(appName);
        }
        if (!StringUtils.isEmpty(className)) {
            criteria.and("className").is(className);
        }
        if (!StringUtils.isEmpty(methodName)) {
            criteria.and("methodName").is(methodName);
        }
        criteria.and("createTime").gte(System.currentTimeMillis() - milliSeconds(reTime));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project("appName", "className", "methodName", "countTime"),
                Aggregation.group("appName", "className", "methodName").count().as("count"),
                Aggregation.sort(Sort.Direction.DESC, "count"),
                Aggregation.match(Criteria.where("count").gt(0L)),
                Aggregation.skip(0L),
                Aggregation.limit(topNum)
        );
        AggregationResults<MethodResult> results = mongoTemplate.aggregate(aggregation, METHOD_TABLE, MethodResult.class);
        List<MethodResult> list = results.getMappedResults();
        return loadMethodCountData(list);
    }

    @Override
    public Map queryTimeRankMethod(String appName, String className, String methodName, int topNum) {
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(appName)) {
            criteria.and("appName").is(appName);
        }
        if (!StringUtils.isEmpty(className)) {
            criteria.and("className").is(className);
        }
        if (!StringUtils.isEmpty(methodName)) {
            criteria.and("methodName").is(methodName);
        }
        criteria.and("countTime").gt(0L);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project("appName", "className", "methodName", "countTime"),
                Aggregation.group("appName", "className", "methodName").max("countTime").as("countTime"),
                Aggregation.sort(Sort.Direction.DESC, "countTime"),
                Aggregation.skip(1L),
                Aggregation.limit(topNum)
        );

        AggregationResults<MethodDO> results = mongoTemplate.aggregate(aggregation, METHOD_TABLE, MethodDO.class);
        List<MethodDO> list = results.getMappedResults();
        return loadMethodListData(list);
    }

    @Override
    public Map loadMethodListData(List<MethodDO> list) {
        Map map = new HashMap();
        if (list == null || list.size() == 0) {
            return map;
        }
        List<String> axis = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        List<String> full = new ArrayList<>();
        for (MethodDO method : list) {
            axis.add(method.getMethodName());
            data.add(method.getCountTime());
            full.add(method.getAppName() + "<br/> " + method.getClassName() + "." + method.getMethodName() + "<br/>" + method.getCountTime() + "毫秒");
        }
        map.put("axis", axis);
        map.put("data", data);
        map.put("full", full);
        return map;
    }

    @Override
    public Map loadMethodMinuteCountData(List<MethodResult> list) {
        Map map = new HashMap();

        List<String> axis = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        List<String> full = new ArrayList<>();
        for (MethodResult method : list) {
            axis.add(method.getCreateDate());
            data.add(method.getCount());
            full.add(method.getAppName() + "<br/>" + method.getClassName() + "." + method.getMethodName() + "<br/>" + method.getCreateDate() + "<br/>" + method.getCount() + "次");
        }
        map.put("axis", axis);
        map.put("data", data);
        map.put("full", full);
        return map;
    }

    @Override
    public Map loadMethodCountData(List<MethodResult> list) {
        Map map = new HashMap();

        List<String> axis = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        List<String> full = new ArrayList<>();
        for (MethodResult method : list) {
            axis.add(method.getMethodName());
            data.add(method.getCount());
            full.add(method.getAppName() + "<br/>" + method.getClassName() + "." + method.getMethodName() + "<br/>" + method.getCount() + "次");
        }
        map.put("axis", axis);
        map.put("data", data);
        map.put("full", full);
        return map;
    }

    public static long milliSeconds(long reTime) {
        return reTime * 60 * 1000L;
    }
}
