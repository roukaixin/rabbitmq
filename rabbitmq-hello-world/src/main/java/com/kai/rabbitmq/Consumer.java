package com.kai.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/hello-world");
        connectionFactory.setUsername("hello-world");
        connectionFactory.setPassword("!Aa13536431379");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("hello-world",false,false,false,null);

        /*
         * 参数1：队列名称
         * 参数2：开启消息的自动确认机制
         * 参数3：消息消费回调
         */
        channel.basicConsume("hello-world",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费消息--------》" + new String(body));
            }
        });
    }
}
