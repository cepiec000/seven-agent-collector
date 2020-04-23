package com.seven.agent.collector.service;

import com.seven.agent.collector.bean.LoggerDO;
import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.LoggerQuery;

public interface ILoggerService {
    public ResultPage<LoggerDO> queryLoggerByPage(LoggerQuery loggerQuery);
}
