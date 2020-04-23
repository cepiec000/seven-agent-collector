package com.seven.agent.collector.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
@Configuration
public class ServerMain {

    @Autowired
    private ServerHandler serverHandler;
    //定义监听接口
    private static final int PORT = 16999;

    @PostConstruct
    public void start() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建服务端监控线程
                IoAcceptor acceptor = new NioSocketAcceptor();

                acceptor.getSessionConfig().setReadBufferSize(2048);
                acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

                //设置日志记录
                acceptor.getFilterChain().
                        addLast("logger", new LoggingFilter());
                TextLineCodecFactory lineCodecFactory= new TextLineCodecFactory(Charset.forName("UTF-8"));
                lineCodecFactory.setDecoderMaxLineLength(1024*1024);
                lineCodecFactory.setEncoderMaxLineLength(1024*1024);
                //设置编码过滤器
                acceptor.getFilterChain().
                        addLast("codec", new ProtocolCodecFilter(lineCodecFactory));

                //指定业务逻辑处理器
                acceptor.setHandler(serverHandler);

                //设置端口号
                try {
                    acceptor.bind(new InetSocketAddress(PORT));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //启动监听线程
                //acceptor.bind();
            }
        }).start();

    }
}
