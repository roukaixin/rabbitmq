package com.kai.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    @Test
    public void producerSendMessage() throws IOException, TimeoutException {

        // 创建一个 mq 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置主机ip
        connectionFactory.setHost("127.0.0.1");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置虚拟主机
        connectionFactory.setVirtualHost("/hello-world");
        // 设置虚拟主机的账户
        connectionFactory.setUsername("hello-world");
        // 设置虚拟主机的密码
        connectionFactory.setPassword("!Aa13536431379");
        // 工厂 new 一个连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 通道绑定消息队列
        // 参数1：队列名字
        // 参数2：是否持久化（是true（只是持久化队列），否false）
        // 参数3：是否被独占这个队列（是true，否false）
        // 参数4：是否消息被消费后自动删除（是true，否false）
        // 参数5：额外参数
        channel.queueDeclare("hello-world",false,false,false,null);

        // 发送消息到队列
        // 参数1：交换机
        // 参数2：队列名称
        // 参数3：消息额外参数 （MessageProperties.PERSISTENT_TEXT_PLAIN : 持久化消息）
        // 参数4：消息本体信息（字节类型）
        channel.basicPublish("","hello-world", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello-world".getBytes());

        // 关闭通道
        channel.close();
        // 关闭连接
        connection.close();

    }
}
