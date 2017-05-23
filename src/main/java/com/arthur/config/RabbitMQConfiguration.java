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
public class RabbitMQConfiguration implements EnvironmentAware {

	final static String TEST_QUEUE_NAME = "test_queue1";
	final static String TEST_EXCHANGE_NAME = "test_exchange1";
	final static String ROUTING_KEY="test1";
	// RabbitMQ的配置信息

	private String mqRabbitHost;
	private Integer mqRabbitPort;
	private String mqRabbitUsername;
	private String mqRabbitPassword;
	private String mqRabbitVirtualHost;

	private Environment environment;
	private RelaxedPropertyResolver rabbitPropertyResolver;

	//从application.yml中读
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		this.rabbitPropertyResolver = new RelaxedPropertyResolver(environment,
				"spring.rabbitmq.");
		mqRabbitHost = rabbitPropertyResolver.getProperty("host");
		mqRabbitPort = Integer.parseInt(rabbitPropertyResolver.getProperty("port"));
		mqRabbitUsername = rabbitPropertyResolver.getProperty("username");
		mqRabbitPassword = rabbitPropertyResolver.getProperty("password");
		mqRabbitVirtualHost = rabbitPropertyResolver.getProperty("virtual-host");
	}



	// 建立一个连接容器，类型数据库的连接池。
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =
				new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);

		connectionFactory.setUsername(mqRabbitUsername);
		connectionFactory.setPassword(mqRabbitPassword);
		//connectionFactory.setVirtualHost(mqRabbitVirtualHost);

		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	// RabbitMQ的使用入口
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}

	// 要求RabbitMQ建立一个队列。
	@Bean
	public Queue myQueue() {
		return new Queue(TEST_QUEUE_NAME,false);
	}

	// 声明一个监听容器
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(new String[]{TEST_QUEUE_NAME});
		container.setMessageListener(listenerAdapter);

		return container;
	}

	 //在spring容器中添加一个监听类
	 @Bean
	 MessageListenerAdapter listenerAdapter(Receiver receiver) {
		 return new MessageListenerAdapter(receiver, "receiveMessage");
	 }
	// 定义一个直连交换机
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TEST_EXCHANGE_NAME);
	}
	// 要求队列和直连交换机绑定，指定ROUTING_KEY
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(TEST_QUEUE_NAME);
	}

}
