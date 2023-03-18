package com.kai.topic;

import com.kai.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 *
 * @author 不北咪
 * @date 2023/3/17 23:06
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("ems","topic");

        // 获取临时队列
        String queue = channel.queueDeclare().getQueue();

        // 交换机绑定
        // 参数1：队列
        // 参数2：交换机名
        // 参数3：key 匹配规则 *：0或多个 、#：一个
        channel.queueBind(queue,"ems","user.*",null);

        // 消费
        // 参数1：队列
        // 参数2：是否开启确认消费
        // 参数3：消费对象
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("动态路由消费者："+ new String(body));
            }
        });

    }
}
