package com.seven.agent.collector.controller;

import com.seven.agent.collector.bean.*;
import com.seven.agent.collector.service.IJvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IJvmService jvmService;

    @RequestMapping(value = {"/","/index"})
    public String index(@RequestParam(value = "appName", required = false) String appName, HttpServletRequest request) {
        List<String> appNames = jvmService.appNames();
        appNames.add("TEST");
        if (StringUtils.isEmpty(appName) && appNames.size() == 0) {
            return "index";
        }
        if (StringUtils.isEmpty(appName)) {
            appName = appNames.get(0);
        }
        JvmInfoDO jvmInfo = jvmService.getJvmInfo(appName);
        request.setAttribute("jvmInfo", jvmInfo);
        request.setAttribute("appName", appName);
        request.setAttribute("appNames", appNames);
        return "index";
    }

    @RequestMapping("/method")
    public String method(@RequestParam(value = "appName", required = false) String appName, HttpServletRequest request) {
        List<String> appNames = jvmService.appNames();
        if (StringUtils.isEmpty(appName) && appNames.size() == 0) {
            return "method";
        }
        if (StringUtils.isEmpty(appName)) {
            appName = appNames.get(0);
        }
        JvmInfoDO jvmInfo = jvmService.getJvmInfo(appName);
        request.setAttribute("jvmInfo", jvmInfo);
        request.setAttribute("appName", appName);
        request.setAttribute("appNames", appNames);
        return "method";
    }


    @RequestMapping("/logger")
    public String logger(@RequestParam(value = "appName", required = false) String appName, HttpServletRequest request) {
        List<String> appNames = jvmService.appNames();
        if (StringUtils.isEmpty(appName) && appNames.size() == 0) {
            return "logger";
        }
        if (StringUtils.isEmpty(appName)) {
            appName = appNames.get(0);
        }
        JvmInfoDO jvmInfo = jvmService.getJvmInfo(appName);
        request.setAttribute("jvmInfo", jvmInfo);
        request.setAttribute("appName", appName);
        request.setAttribute("appNames", appNames);
        return "logger";
    }

    @RequestMapping("/sql")
    public String sql(@RequestParam(value = "appName", required = false) String appName, HttpServletRequest request) {
        List<String> appNames = jvmService.appNames();
        if (StringUtils.isEmpty(appName) && appNames.size() == 0) {
            return "sql";
        }
        if (StringUtils.isEmpty(appName)) {
            appName = appNames.get(0);
        }
        JvmInfoDO jvmInfo = jvmService.getJvmInfo(appName);
        request.setAttribute("jvmInfo", jvmInfo);
        request.setAttribute("appName", appName);
        request.setAttribute("appNames", appNames);
        return "sql";
    }

    @RequestMapping("/log")
    public String log(@RequestParam(value = "appName", required = false) String appName, HttpServletRequest request){
        List<String> appNames = jvmService.appNames();
        if (StringUtils.isEmpty(appName) && appNames.size() == 0) {
            return "sql";
        }
        if (StringUtils.isEmpty(appName)) {
            appName = appNames.get(0);
        }
        JvmInfoDO jvmInfo = jvmService.getJvmInfo(appName);
        request.setAttribute("jvmInfo", jvmInfo);
        request.setAttribute("appName", appName);
        request.setAttribute("appNames", appNames);
        return "tailLog";
    }

    @RequestMapping("/removeAll")
    @ResponseBody
    public String deleteAll() {
        mongoTemplate.dropCollection(JvmInfoDO.class);
        mongoTemplate.dropCollection(JvmMemoryInfoDO.class);
        mongoTemplate.dropCollection(MethodDO.class);
        mongoTemplate.dropCollection(SqlDO.class);
        mongoTemplate.dropCollection(LoggerDO.class);
        List<JvmInfoDO> list = mongoTemplate.findAll(JvmInfoDO.class);
        System.out.println(list.size());
        return "0k";
    }


}
