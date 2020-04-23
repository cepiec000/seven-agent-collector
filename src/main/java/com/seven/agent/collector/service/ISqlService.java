package com.seven.agent.collector.service;

import com.seven.agent.collector.bean.page.ResultPage;
import com.seven.agent.collector.bean.query.SqlQuery;

public interface ISqlService {
    public ResultPage querySqlPage(SqlQuery sqlQuery);
}
