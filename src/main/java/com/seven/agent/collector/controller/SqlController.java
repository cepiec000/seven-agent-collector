package com.seven.agent.collector.controller;

import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.SqlQuery;
import com.seven.agent.collector.service.ISqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sql")
public class SqlController {

    @Autowired
    private ISqlService sqlService;

    @RequestMapping("querySqlPage")
    public ResultPage querySqlPage(SqlQuery sqlQuery) {
        return sqlService.querySqlPage(sqlQuery);
    }

}
