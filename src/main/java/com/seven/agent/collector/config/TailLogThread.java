package com.seven.agent.collector.config;


import com.seven.agent.collector.bean.LoggerDO;
import com.seven.agent.collector.utils.SpringApplicationContextUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TailLogThread extends Thread {
    private MongoTemplate mongoTemplate;
    private Session session;
    private long lastId = System.currentTimeMillis();
    private String appName;
    public TailLogThread(Session session,String appName) {
        loadMongoTemplate();
        this.session = session;
        this.appName=appName;

    }

    private void loadMongoTemplate() {
        if (mongoTemplate == null) {
            mongoTemplate = SpringApplicationContextUtil.getBean(MongoTemplate.class);
        }
    }

    private List<String> queryLogger() {
        List<String> result =null;
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.and("appName").is(appName);
            criteria.and("createTime").gt(lastId);
            query.addCriteria(criteria);
            query.with(Sort.by(Sort.Direction.ASC, "createTime"));
            List<LoggerDO> list = mongoTemplate.find(query, LoggerDO.class);
             result = new ArrayList<>();
            if (list != null && list.size() > 0) {
                for (LoggerDO logger : list) {
                    result.add(logger.toString());
                    lastId = logger.getCreateTime();
                }
            }
        }catch (Exception e){
            System.out.println("webSocket close");
        }
        return result;
    }
    @Override
    public void run() {
        try {

            while (true){
                List<String> result = queryLogger();
                if (result.size()==0){
                    Thread.sleep(300);
                }

                for (String line : result) {
                    // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                    session.getBasicRemote().sendText(line + "<br>");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("tail thread stop");
        }
    }
}
