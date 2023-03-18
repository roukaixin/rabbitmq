package com.kai.workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
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
        channel.basicQos(1);

        channel.basicConsume("work-queues",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2 消费消息为：" + new String(body));
                // 手动确认消息已经被消费
                // 参数1：消息的唯一 id
                // 参数2：消息是否被消费
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}
