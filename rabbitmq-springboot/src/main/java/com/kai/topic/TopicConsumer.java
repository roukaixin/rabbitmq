package com.kai.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 动态路由模式
 *
 * @author 不北咪
 * @date 2023/3/19 3:29
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "topic",type = "topic"),
                    key = {"user.add"}
            )
    })
    public void consumer1(String message){
        System.out.println("消费者1 ==》" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "topic",type = "topic"),
                    key = {"user.*"}
            )
    })
    public void consumer2(String message){
        System.out.println("消费者2 ==》" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "topic",type = "topic"),
                    key = {"user.#"}
            )
    })
    public void consumer3(String message){
        System.out.println("消费者3 ==》" + message);
    }
}
