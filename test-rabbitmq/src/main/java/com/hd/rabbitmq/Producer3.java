package com.hd.rabbitmq;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Producer3 {

	private final static String QUEUE_NAME = "rabbitMQ.test.queue";

	public static void main(String[] args) throws IOException, TimeoutException {

		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置RabbitMQ相关信息
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setPort(5672);
		// 创建一个新的连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();

		// 声明一个队列
		// queueDeclare（队列名称，是否持久化（true表示是，队列将在服务器重启时生存），是否是独占队列（创建者可以使用的私有队列，断开后自动删除），
		// 当所有消费者客户端连接断开时是否自动删除队列，队列的其他参数）
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 开启发送方确认模式
		channel.confirmSelect();

		// 存储未确认的消息标识tag
		final SortedSet<Long> confirmSet = Collections.synchronizedNavigableSet(new TreeSet<Long>());

		// 异步监听确认和未确认的消息
		channel.addConfirmListener(new ConfirmListener() {

			/**
			 * 处理返回确认成功
			 * 
			 * @param deliveryTag 如果是多条，这个就是最后一条消息的tag
			 * @param multiple    是否多条
			 * @throws IOException
			 */
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("消息发送成功,	deliveryTag:" + deliveryTag + "		multiple:" + multiple + "");
				if (multiple) {
					// 移除发送成功的多条消息标识tag
					confirmSet.headSet(deliveryTag + 1).clear();
				} else {
					// 移除发送成功的一条消息标识tag
					confirmSet.remove(deliveryTag);
				}
			}

			/**
			 * 处理返回确认失败
			 * 
			 * @param deliveryTag 如果是多条，这个就是最后一条消息的tag
			 * @param multiple    是否多条
			 * @throws IOException
			 */
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("失败,deliveryTag:" + deliveryTag + "multiple:" + multiple + "");
				if (multiple) {
					confirmSet.headSet(deliveryTag + 1).clear();
				} else {
					confirmSet.remove(deliveryTag);
				}
			}

		});

		String message = "Hello RabbitMQ ～ ";

		// 发送消息到队列中
		// basicPublish（交换机名称，队列映射的路由key，消息的其他属性，发送信息的主体）
		for (int i = 1; i <= 10; i++) {
			String msg = message + i;
			long tag = channel.getNextPublishSeqNo();
			confirmSet.add(tag);
			System.out.println("tag:" + tag);
			
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
			System.out.println("Producer Send +'" + msg + "'");
			
		}
		
		System.out.println("============================");
		// 关闭通道和连接
		// channel.close();
		// connection.close();

	}
}
