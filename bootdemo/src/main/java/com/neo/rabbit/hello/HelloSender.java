package com.neo.rabbit.hello;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender : " + context);
		//this.rabbitTemplate.convertAndSend("hello", context);

		Object obj = this.rabbitTemplate.convertSendAndReceive("hello", context);
		System.out.println("zhangwei : " + obj.toString());
	}

}