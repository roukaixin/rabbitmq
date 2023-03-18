package com.kai.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由模式
 *
 * @author 不北咪
 * @date 2023/3/19 3:23
 */
@Component
public class DirectConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "direct",type = "direct"),
                    key = {"user.add"}
            )
    })
    public void consumer1(String message){
        System.out.println("消费者1 ==》" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "direct",type = "direct"),
                    key = {"user.update"}
            )
    })
    public void consumer2(String message){
        System.out.println("消费者2 ==》" + message);
    }


}
