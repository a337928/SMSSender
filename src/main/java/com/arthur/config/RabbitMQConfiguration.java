package com.arthur.config;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;



/**
 * Created by wangtao on 17/4/23.
 */
public class RabbitMQConfiguration implements EnvironmentAware {

	final static String TEST_QUEUE_NAME = "test_queue";
	final static String TEST_EXCHANGE_NAME = "test_exchange";
	final static String ROUTING_KEY="test";
	// RabbitMQ��������Ϣ

	private String mqRabbitHost;
	private Integer mqRabbitPort;
	private String mqRabbitUsername;
	private String mqRabbitPassword;
	private String mqRabbitVirtualHost;

	private Environment environment;
	private RelaxedPropertyResolver rabbitPropertyResolver;

	//��application.yml�ж�
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		this.rabbitPropertyResolver = new RelaxedPropertyResolver(environment,
				"spring.rabbitmq.");
		mqRabbitHost = rabbitPropertyResolver.getProperty("host");
		mqRabbitPort = Integer.parseInt(rabbitPropertyResolver.getProperty("port"));
		mqRabbitUsername = rabbitPropertyResolver.getProperty("spring");
	}



	// ����һ�������������������ݿ�����ӳء�
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =
				new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);

		connectionFactory.setUsername(mqRabbitUsername);
		connectionFactory.setPassword(mqRabbitPassword);
		connectionFactory.setVirtualHost(mqRabbitVirtualHost);

		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	// RabbitMQ��ʹ�����
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}

	// Ҫ��RabbitMQ����һ�����С�
	@Bean
	public Queue myQueue() {
		return new Queue(TEST_QUEUE_NAME);
	}

	// ����һ����������
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Receiver listenerAdapter) {
//
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(new String[]{TEST_QUEUE_NAME});
//		container.setMessageListener(listenerAdapter);
//
//		return container;
//	}

	// ��spring���������һ��������
//	@Bean
//	Receiver receiver() {
//		return new Receiver();
//	}
	// ����һ��ֱ��������
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(TEST_EXCHANGE_NAME);
	}
	// Ҫ����к�ֱ���������󶨣�ָ��ROUTING_KEY
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

}
