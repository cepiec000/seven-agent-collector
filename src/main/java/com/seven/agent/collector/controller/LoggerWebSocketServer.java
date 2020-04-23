package com.seven.agent.collector.controller;

import com.seven.agent.collector.config.TailLogThread;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/webSocket/{param}")
public class LoggerWebSocketServer {
    TailLogThread thread = null;

    @OnOpen
    public void onOpen(Session session, @PathParam("param")String appName) {
        try {
            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
            thread = new TailLogThread(session,appName);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
