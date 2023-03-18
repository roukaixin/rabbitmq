package com.kai.fanout;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        // 参数1：交换机名称
        // 参数2：交换机类型  fanout（广播模式）
        channel.exchangeDeclare("logs","fanout");

        // 发送消息，发送到交换机
        channel.basicPublish("logs","", null,"广播模型".getBytes());

        // 关闭连接
        RabbitMQUtil.closeConnection(channel,connection);
    }
}
