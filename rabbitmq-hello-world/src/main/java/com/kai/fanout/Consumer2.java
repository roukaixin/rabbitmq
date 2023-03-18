package com.kai.fanout;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("logs","fanout");

        // 获取临时队列（因为广播模式，一个消费者对应一个队列，所以不用创建持久化队列）
        String queue = channel.queueDeclare().getQueue();

        // 绑定交换机
        channel.queueBind(queue,"logs","",null);

        // 消费
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
