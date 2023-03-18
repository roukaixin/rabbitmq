package com.kai.topic;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 *
 * @author 不北咪
 * @date 2023/3/17 22:55
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("ems","topic");

        // 获取临时队列
        String queue = channel.queueDeclare().getQueue();

        // 队列绑定交换机
        channel.queueBind(queue,"ems","#.add",null);

        // 消费
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("动态模式消费者 ： " + new String(body));
            }
        });
    }
}
