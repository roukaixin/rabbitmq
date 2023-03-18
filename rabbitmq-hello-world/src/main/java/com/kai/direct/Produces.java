package com.kai.direct;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 生产着
 *
 * @author 不北咪
 * @date 2023/3/17 11:13
 */
public class Produces {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明
        channel.exchangeDeclare("mes","direct");

        // 发布消息到交换机
        // 参数1：交换机名字
        // 参数2：路由key
        // channel.basicPublish("ems","user:add",null,"用户添加信息".getBytes());
        channel.basicPublish("ems","user:update",null,"用户修改信息".getBytes());
    }
}
