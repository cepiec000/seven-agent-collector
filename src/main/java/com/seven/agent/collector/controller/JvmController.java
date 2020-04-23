package com.seven.agent.collector.controller;

import com.seven.agent.collector.bean.JvmMemoryInfoDO;
import com.seven.agent.collector.service.IJvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jvm")
public class JvmController {

    @Autowired
    private IJvmService jvmService;

    @GetMapping(value = "/getJvmThreadCountInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getJvmThreadCountInfo(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getThreadCountList(list);
    }


    @GetMapping(value = "/getJvmHeadMemoryData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getJvmHeadMemoryData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getJvmHeadMemoryList(list);
    }

    @GetMapping(value = "/getJvmNonHeadMemoryData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getJvmNonHeadMemoryData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getJvmNonHeadMemoryList(list);
    }

    @GetMapping(value = "/getEdenSpaceData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getEdenSpaceData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getEdenSpaceList(list);
    }
    @GetMapping(value = "/getSurvivorSpaceData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getSurvivorSpaceData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getSurvivorSpaceList(list);
    }

    @GetMapping(value = "/getOldGenData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getOldGenData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getOldGenList(list);
    }

    @GetMapping(value = "/getPermGenData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getPermGenData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getPermGenList(list);
    }


    @GetMapping(value = "/getClassCountData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map getClassCountData(@RequestParam("appName") String appName, @RequestParam("reTime") Long reTime) {
        List<JvmMemoryInfoDO> list = jvmService.queryJvmMemoryList(appName, reTime);
        return jvmService.getClassCountList(list);
    }

}
