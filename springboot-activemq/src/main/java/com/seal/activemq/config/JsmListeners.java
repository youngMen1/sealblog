package com.seal.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 14:17
 * @description
 **/
@Configuration
@EnableJms
public class JsmListeners {

	/**
	 * 点对点
	 * 
	 * @return
	 */
	@Bean
	public ActiveMQQueue queue() {
		return new ActiveMQQueue("jms-queue");
	}

	/**
	 * 发布/订阅
	 * 
	 * @return
	 */
	@Bean
	public ActiveMQTopic topic() {
		return new ActiveMQTopic("jms-topic");
	}
}
