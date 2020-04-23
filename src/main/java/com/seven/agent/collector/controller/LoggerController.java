package com.seven.agent.collector.controller;

import com.alibaba.fastjson.JSON;
import com.seven.agent.collector.bean.LoggerDO;
import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.LoggerQuery;
import com.seven.agent.collector.service.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private ILoggerService loggerService;

    @RequestMapping(value = "/queryPage",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultPage queryLoggerByPage(LoggerQuery loggerQuery){
        ResultPage page=loggerService.queryLoggerByPage(loggerQuery);
        return page;
    }
}
