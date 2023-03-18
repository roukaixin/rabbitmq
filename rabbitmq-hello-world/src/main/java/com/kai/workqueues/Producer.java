package com.kai.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

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

        for (int i = 0; i < 200; i++) {
            channel.basicPublish("","work-queues", MessageProperties.PERSISTENT_TEXT_PLAIN,("工作模型（work queues）:"+i).getBytes());
        }

        channel.close();
        connection.close();
    }
}
