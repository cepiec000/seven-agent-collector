package com.seven.agent.collector.utils;

import com.seven.agent.collector.bean.LoggerDO;
import com.seven.agent.collector.bean.MethodDO;
import com.seven.agent.collector.bean.SqlDO;

import java.util.Objects;

public class DateVerifyUtil {
    public static boolean verifyMethodData(MethodDO methodDO) {
        if (null == methodDO) {
            return false;
        }
        if (Objects.isNull(methodDO.getAppName()) || Objects.isNull(methodDO.getMethodName())  || methodDO.getCreateTime() == 0) {
            return false;
        }
        return true;
    }

    public static boolean verifyLoggerData(LoggerDO loggerDO) {
        if (null == loggerDO) {
            return false;
        }
        if (Objects.isNull(loggerDO.getAppName()) || Objects.isNull(loggerDO.getMessage()) || Objects.isNull(loggerDO.getLevel()) || loggerDO.getCreateTime() == 0) {
            return false;
        }
        return true;
    }

    public static boolean verifySqlData(SqlDO sqlDO) {
        if (null == sqlDO) {
            return false;
        }
        if (Objects.isNull(sqlDO.getAppName()) || Objects.isNull(sqlDO.getSql())  || sqlDO.getCreateTime() == 0) {
            return false;
        }
        return true;
    }
}
