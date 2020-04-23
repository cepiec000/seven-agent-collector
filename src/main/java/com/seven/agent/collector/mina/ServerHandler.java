package com.seven.agent.collector.mina;

import com.alibaba.fastjson.JSON;
import com.seven.agent.collector.bean.*;
import com.seven.agent.collector.utils.DateVerifyUtil;
import com.seven.agent.collector.utils.MongoUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ServerHandler extends IoHandlerAdapter {
    private static final String METHOD_PREFIX = "METHOD";
    private static final String LOGGER_PREFIX = "LOGGER";
    private static final String SQL_PREFIX = "SQL";
    private static final String JVM_PREFIX = "JVM";
    private static final String MEMORY_PREFIX = "MEMORY";

    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 当一个新客户端连接后触发此方法
     */
    @Override
    public void sessionCreated(IoSession session){
        //显示客户端的ip和端口
        System.out.println(session.getRemoteAddress());
    }

    /**
     * 当接口中其他方法抛出异常未被捕获时触发此方法
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

    /**
     * 当接收到客户端的请求信息后触发此方法
     * 消息接收事件
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String strMsg = message.toString();
        if(strMsg.trim().equalsIgnoreCase("quit")){
            session.closeNow();
            return;
        }
        if (strMsg == null || strMsg.length() == 0) {
            return;
        }
        String[] messageArr = strMsg.split("<->");
        switch (messageArr[0]) {
            case JVM_PREFIX:
                jvmHandle(messageArr[1]);
                break;
            case MEMORY_PREFIX:
                memoryHandle(messageArr[1]);
                break;
            case LOGGER_PREFIX:
                loggerHandle(messageArr[1]);
                break;
            case SQL_PREFIX:
                sqlHandle(messageArr[1]);
                break;
            case METHOD_PREFIX:
                methodHandle(messageArr[1]);
                break;
        }

        //返回消息字符串
        session.write("ok");
    }

    /**
     * 当连接空闲时触发此方法
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE" + session.getIdleCount(status));
    }

    private String jvmHandle(String json) {
        JvmInfoDO jvmInfoDO = JSON.parseObject(json, JvmInfoDO.class);
        if (jvmInfoDO == null || StringUtils.isEmpty(jvmInfoDO.getAppName())) {
            return "params error";
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("appName").is(jvmInfoDO.getAppName()));
        JvmInfoDO infoDO = this.mongoTemplate.findOne(query, JvmInfoDO.class);
        if (infoDO == null) {
            this.mongoTemplate.insert(jvmInfoDO);
        } else {
            this.mongoTemplate.findAndModify(query, MongoUtils.buildUpdate(jvmInfoDO), JvmInfoDO.class);
        }
        return "ok";
    }

    private String memoryHandle(String json) {
        JvmMemoryInfoDO jvmMemoryInfoDO = JSON.parseObject(json, JvmMemoryInfoDO.class);
        this.mongoTemplate.insert(jvmMemoryInfoDO);
        return "ok";
    }

    private String methodHandle(String json) {
        MethodDO method = JSON.parseObject(json, MethodDO.class);
        if (DateVerifyUtil.verifyMethodData(method)) {
            this.mongoTemplate.insert(method);
        } else {
            return "method data Illegal parameters";
        }
        return "ok";
    }

    private String loggerHandle(String json) {
        LoggerDO loggerDO = JSON.parseObject(json, LoggerDO.class);
        if (DateVerifyUtil.verifyLoggerData(loggerDO)) {
            this.mongoTemplate.insert(loggerDO);
        } else {
            return "logger data Illegal parameters";
        }
        return "ok";
    }

    private String sqlHandle(String json) {
        SqlDO sqlDO = JSON.parseObject(json, SqlDO.class);

        if (DateVerifyUtil.verifySqlData(sqlDO)) {
            this.mongoTemplate.insert(sqlDO);
        } else {
            return "sql data Illegal parameters";
        }
        return "ok";
    }
}
