package com.arthur.config;



import com.arthur.rabbitMQ.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by wangtao on 17/4/23.
 */
@Configuration
@EnableTransactionManagement
public class RabbitMQConfiguration {

	final static String TEST_QUEUE_NAME = "test_queue1";
	final static String TEST_EXCHANGE_NAME = "test_exchange1";
	final static String ROUTING_KEY="test1";


	private String mqRabbitHost;
	private Integer mqRabbitPort;
	private String mqRabbitUsername;
	private String mqRabbitPassword;
	private String mqRabbitVirtualHost;

	private Environment environment;
	private RelaxedPropertyResolver rabbitPropertyResolver;


//	@Override
//	public void setEnvironment(Environment environment) {
//		this.environment = environment;
//		this.rabbitPropertyResolver = new RelaxedPropertyResolver(environment,
//				"spring.rabbitmq.");
//		mqRabbitHost = rabbitPropertyResolver.getProperty("host");
//		mqRabbitPort = Integer.parseInt(rabbitPropertyResolver.getProperty("port"));
//		mqRabbitUsername = rabbitPropertyResolver.getProperty("username");
//		mqRabbitPassword = rabbitPropertyResolver.getProperty("password");
//		mqRabbitVirtualHost = rabbitPropertyResolver.getProperty("virtual-host");
//	}
//
//
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory =
//				new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);
//
//		connectionFactory.setUsername(mqRabbitUsername);
//		connectionFactory.setPassword(mqRabbitPassword);
//		//connectionFactory.setVirtualHost(mqRabbitVirtualHost);
//
//		return connectionFactory;
//	}
//
//	@Bean
//	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//		return new RabbitAdmin(connectionFactory);
//	}
//
//
//	@Bean
//	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		return new RabbitTemplate(connectionFactory);
//	}


	@Bean
	public Queue myQueue() {
		return new Queue(TEST_QUEUE_NAME,false);
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(new String[]{TEST_QUEUE_NAME});
		container.setMessageListener(listenerAdapter);

		return container;
	}


	 @Bean
	 MessageListenerAdapter listenerAdapter(Receiver receiver) {
		 return new MessageListenerAdapter(receiver, "receiveMessage");
	 }

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TEST_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(TEST_QUEUE_NAME);
	}

}
