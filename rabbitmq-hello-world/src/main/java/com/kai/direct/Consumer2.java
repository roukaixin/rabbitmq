package com.kai.direct;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者 （user:update）
 *
 * @author 不北咪
 * @date 2023/3/17 11:29
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("ems","direct");

        // 获取临时队列
        String queue = channel.queueDeclare().getQueue();

        // 交换机绑定队列
        channel.queueBind(queue,"ems","user:update",null);

        // 消费 （只能从队列中消费）
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者 user:update 消费："+ new String(body));
            }
        });
    }
}
