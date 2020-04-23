package com.seven.agent.collector;

import com.seven.agent.collector.utils.SpringApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SevenAgentCollectorApplication {

	public static void main(String[] args) {
		ApplicationContext run=SpringApplication.run(SevenAgentCollectorApplication.class, args);
		SpringApplicationContextUtil.setApplicationContext(run);
	}

}
