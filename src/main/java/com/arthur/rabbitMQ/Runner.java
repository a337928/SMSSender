package com.arthur.rabbitMQ;

/**
 * Created by wangtao on 17/5/23.
 */
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;
	private final ConfigurableApplicationContext context;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
				  ConfigurableApplicationContext context) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
	}

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args){
			rabbitTemplate.convertAndSend("test_queue1", arg.getBytes());
		}

		//receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		//context.close();
	}

}
