package com.kai.topic;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 生产者
 *
 * @author 不北咪
 * @date 2023/3/17 11:50
 */
public class Produces {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("ems","topic");

        // 发生消息
        channel.basicPublish("ems","user.add",null,"动态路由模式".getBytes());
    }
}
