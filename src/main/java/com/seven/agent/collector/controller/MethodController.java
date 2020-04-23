package com.seven.agent.collector.controller;

import com.alibaba.fastjson.JSON;
import com.seven.agent.collector.bean.MethodDO;
import com.seven.agent.collector.bean.result.MethodResult;
import com.seven.agent.collector.service.IMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/method")
public class MethodController {

    @Autowired
    private IMethodService methodService;


    @RequestMapping(value = "/minuteCountMethod", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map minuteCountMethod(@RequestParam(value = "appName") String appName,
                                 @RequestParam(value = "className", required = false) String className,
                                 @RequestParam(value = "methodName", required = false) String methodName,
                                 @RequestParam(value = "reTime") Long reTime) {
        return methodService.queryMinuteCountData(appName, className, methodName, reTime, 50);
    }

    @RequestMapping(value = "/countMethod", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map countMethod(@RequestParam(value = "appName") String appName,
                           @RequestParam(value = "className", required = false) String className,
                           @RequestParam(value = "methodName", required = false) String methodName,
                           @RequestParam(value = "reTime") Long reTime) {
        return methodService.queryCountData(appName, className, methodName, reTime, 100);
    }


    @RequestMapping(value = "/queryTimeRankMethod", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map queryTimeRankMethod(@RequestParam(value = "appName") String appName,
                                   @RequestParam(value = "className", required = false) String className,
                                   @RequestParam(value = "methodName", required = false) String methodName) {
        return methodService.queryTimeRankMethod(appName, className, methodName, 50);
    }


}
