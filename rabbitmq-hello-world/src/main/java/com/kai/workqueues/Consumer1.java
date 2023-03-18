package com.kai.workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/work-queues");
        connectionFactory.setUsername("work-queues");
        connectionFactory.setPassword("!Aa13536431379");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work-queues",true,false,false,null);

        // 设置请求数（QOS）
        // 如果要关闭消息自动确认机制，就需要设置 QPS 为 1。
        // 比如：消费完一条之后在去拿下一条消息
        channel.basicQos(1);

        // 参数1：队列名字
        // 参数2：是否开启自动确认机制 （true：消费完之后自动在删除消息，false：需要手动确认是否已经消费）
        channel.basicConsume("work-queues",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1 消费消息为：" + new String(body));
                // 手动确认消息已经被消费
                // 参数1：消息的唯一 id
                // 参数2：消息是否被消费
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}
