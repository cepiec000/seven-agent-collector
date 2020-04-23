package com.seven.agent.collector.service;

import com.seven.agent.collector.bean.JvmInfoDO;
import com.seven.agent.collector.bean.JvmMemoryInfoDO;
import com.seven.agent.collector.enums.MemoryTypeEnum;

import java.util.List;
import java.util.Map;

public interface IJvmService {
    public List<JvmMemoryInfoDO> queryJvmMemoryList(String appName, Long reTime);

    public Map getJvmHeadMemoryList(List<JvmMemoryInfoDO> list);

    public Map getThreadCountList(List<JvmMemoryInfoDO> list);

    public Map getJvmNonHeadMemoryList(List<JvmMemoryInfoDO> list);

    public Map getEdenSpaceList(List<JvmMemoryInfoDO> list);

    public Map getSurvivorSpaceList(List<JvmMemoryInfoDO> list);

    public Map getClassCountList(List<JvmMemoryInfoDO> list);

    public Map getOldGenList(List<JvmMemoryInfoDO> list);

    public Map getPermGenList(List<JvmMemoryInfoDO> list);

    public JvmInfoDO getJvmInfo(String appName);

    public List<String> appNames();
}
