package com.hd.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer3 {

	private final static String QUEUE_NAME = "rabbitMQ.test.queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		Customer3 customer3 = new Customer3();
		customer3.createCustomer("customer1");
		customer3.createCustomer("customer2");
	}

	public void createCustomer(final String customerName) throws IOException, TimeoutException{
		
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置RabbitMQ地址
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setPort(5672);
		// 创建一个新的连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		final Channel channel = connection.createChannel();
		// 声明要关注的队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);// 保证一次只分发一个
		System.out.println(customerName + " Waiting Received messages");

		// DefaultConsumer类实现了Consumer接口，通过传入一个频道，
		// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(customerName + " Received '" + message + "'");
				
				// doWork处理任务
				doWork(customerName);
				
				// 返回消费确认状态
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};

		// 消费手动确认 -- RabbitMQ中的消息确认机制
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
	
	
	private void doWork(String customer) {
        try {
            Thread.sleep(2000); // 暂停2秒钟
            System.out.println(customer + ": completion of the job!");
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
