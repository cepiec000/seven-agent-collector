package com.seven.agent.collector.service;

import com.seven.agent.collector.bean.MethodDO;
import com.seven.agent.collector.bean.result.MethodResult;

import java.util.List;
import java.util.Map;

public interface IMethodService {
    /**
     * 时间段内 每分钟 方法的访问次数
     * @param appName
     * @param className
     * @param methodName
     * @param reTime
     * @param topNum
     * @return
     */
    public Map queryMinuteCountData(String appName,String className,String methodName,long reTime,int topNum);

    /**
     * 时间段内 方法的 访问次数
     * @param appName
     * @param className
     * @param methodName
     * @param reTime
     * @param topNum
     * @return
     */
    public Map queryCountData(String appName,String className,String methodName,long reTime,int topNum);

    public Map queryTimeRankMethod(String appName,String className,String methodName,int topNum);
    public Map loadMethodListData(List<MethodDO> list);
    public Map loadMethodMinuteCountData(List<MethodResult> list);
    public Map loadMethodCountData(List<MethodResult> list);
}
