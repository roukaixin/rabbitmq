package com.kai.direct;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者 （消费用户添加）
 *
 * @author 不北咪
 * @date 2023/3/17 11:20
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("ems","direct");

        // 获取临时队列
        String queue = channel.queueDeclare().getQueue();

        // 交换机绑定队列
        // 参数1：队列名字
        // 参数2：交换机名
        // 参数3：消费那个key
        channel.queueBind(queue,"ems","user:add",null);

        // 消费
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者 user:add 消费"+new String(body));
            }
        });
    }
}
