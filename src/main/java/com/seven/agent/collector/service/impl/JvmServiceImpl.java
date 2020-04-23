package com.seven.agent.collector.service.impl;

import com.seven.agent.collector.bean.JvmInfoDO;
import com.seven.agent.collector.bean.JvmMemoryInfoDO;
import com.seven.agent.collector.service.IJvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JvmServiceImpl implements IJvmService {
    private static final long MB = 1048576L;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<JvmMemoryInfoDO> queryJvmMemoryList(String appName, Long reTime) {
        Query query = new Query();
        if (!StringUtils.isEmpty(appName)) {
            query.addCriteria(Criteria.where("appName").is(appName));
        }
        if (reTime != null && reTime > 0) {
            query.addCriteria(Criteria.where("createTime").gte(System.currentTimeMillis() - (reTime * 60 * 1000L)));
        }
        query.with(Sort.by(Sort.Order.desc("createTime")));
        List<JvmMemoryInfoDO> list = this.mongoTemplate.find(query, JvmMemoryInfoDO.class);
        Collections.sort(list, new Comparator<JvmMemoryInfoDO>() {
            @Override
            public int compare(JvmMemoryInfoDO o1, JvmMemoryInfoDO o2) {
                return (int) (o1.getCreateTime() - o2.getCreateTime());
            }
        });
        return list;
    }

    @Override
    public Map getPermGenList(List<JvmMemoryInfoDO> list) {
        Map permGenMap = new HashMap();
        if (list==null || list.size()==0){
            return permGenMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getPermGenUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getPermGenMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getPermGenCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        permGenMap.put("used", usedArr);
        permGenMap.put("max", maxArr);
        permGenMap.put("committed", committedArr);
        permGenMap.put("axis", axis);
        return permGenMap;
    }

    @Override
    public Map getOldGenList(List<JvmMemoryInfoDO> list) {
        Map oldGenMap = new HashMap();
        if (list==null || list.size()==0){
            return oldGenMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getOldGenUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getOldGenMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getOldGenCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        oldGenMap.put("used", usedArr);
        oldGenMap.put("max", maxArr);
        oldGenMap.put("committed", committedArr);
        oldGenMap.put("axis", axis);
        return oldGenMap;
    }


    @Override
    public Map getClassCountList(List<JvmMemoryInfoDO> list) {
        Map classCountMap = new HashMap();
        if (list==null || list.size()==0){
            return classCountMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getLoadedClassCount() / 1024);
            maxArr.add(memoryInfoDO.getTotalLoadedClassCount() / 1024);
            committedArr.add(memoryInfoDO.getUnLoadClassCount() / 1024);
            axis.add(memoryInfoDO.getCreateDate());
        }
        classCountMap.put("loaded", usedArr);
        classCountMap.put("totalLoaded", maxArr);
        classCountMap.put("unload", committedArr);
        classCountMap.put("axis", axis);
        return classCountMap;
    }

    @Override
    public Map getSurvivorSpaceList(List<JvmMemoryInfoDO> list) {
        Map survivorSpaceMap = new HashMap();
        if (list==null || list.size()==0){
            return survivorSpaceMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getSurvivorSpaceUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getSurvivorSpaceMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getSurvivorSpaceCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        survivorSpaceMap.put("used", usedArr);
        survivorSpaceMap.put("max", maxArr);
        survivorSpaceMap.put("committed", committedArr);
        survivorSpaceMap.put("axis", axis);
        return survivorSpaceMap;
    }


    @Override
    public Map getEdenSpaceList(List<JvmMemoryInfoDO> list) {
        Map edenSpaceMap = new HashMap();
        if (list==null || list.size()==0){
            return edenSpaceMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getEdenUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getEdenMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getEdenCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        edenSpaceMap.put("used", usedArr);
        edenSpaceMap.put("max", maxArr);
        edenSpaceMap.put("committed", committedArr);
        edenSpaceMap.put("axis", axis);
        return edenSpaceMap;
    }

    @Override
    public Map getJvmNonHeadMemoryList(List<JvmMemoryInfoDO> list) {
        Map nonHeadMemoryMap = new HashMap();
        if (list==null || list.size()==0){
            return nonHeadMemoryMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getNonUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getNonMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getNonCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        nonHeadMemoryMap.put("used", usedArr);
        nonHeadMemoryMap.put("max", maxArr);
        nonHeadMemoryMap.put("committed", committedArr);
        nonHeadMemoryMap.put("axis", axis);
        return nonHeadMemoryMap;
    }

    @Override
    public Map getJvmHeadMemoryList(List<JvmMemoryInfoDO> list) {
        Map headMemoryMap = new HashMap();
        if (list==null || list.size()==0){
            return headMemoryMap;
        }
        List<Long> usedArr = new ArrayList<>();
        List<Long> maxArr = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        List<Long> committedArr = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            usedArr.add(memoryInfoDO.getUsedMemory() / MB);
            maxArr.add(memoryInfoDO.getMaxMemory() / MB);
            committedArr.add(memoryInfoDO.getCommittedMemory() / MB);
            axis.add(memoryInfoDO.getCreateDate());
        }
        headMemoryMap.put("used", usedArr);
        headMemoryMap.put("max", maxArr);
        headMemoryMap.put("committed", committedArr);
        headMemoryMap.put("axis", axis);
        return headMemoryMap;
    }


    @Override
    public Map getThreadCountList(List<JvmMemoryInfoDO> list) {
        Map threadCountMap = new HashMap();
        if (list==null || list.size()==0){
            return threadCountMap;
        }
        long count = 0;
        List<String> axis = new ArrayList<>();
        List<Long> seriesData = new ArrayList<>();
        for (JvmMemoryInfoDO memoryInfoDO : list) {
            count += memoryInfoDO.getThreadCount();
            axis.add(memoryInfoDO.getCreateDate());
            seriesData.add(memoryInfoDO.getThreadCount());
        }
        long average = count / list.size();
        threadCountMap.put("average", average);
        threadCountMap.put("axis", axis);
        threadCountMap.put("data", seriesData);
        return threadCountMap;
    }

    @Override
    public JvmInfoDO getJvmInfo(String appName) {
        Query query = new Query();
        if (!StringUtils.isEmpty(appName)) {
            query.addCriteria(Criteria.where("appName").is(appName));
        }

        return this.mongoTemplate.findOne(query, JvmInfoDO.class);
    }

    @Override
    public List<String> appNames() {
        List<JvmInfoDO> list = this.mongoTemplate.findAll(JvmInfoDO.class);
        if (list != null) {
            return list.stream().map(JvmInfoDO::getAppName).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
