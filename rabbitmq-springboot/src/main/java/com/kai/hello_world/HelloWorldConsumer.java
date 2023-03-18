package com.kai.hello_world;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 直连模式
 *
 * @author 不北咪
 * @date 2023/3/19 3:52
 */
@Component
public class HelloWorldConsumer {

    @RabbitListener(queuesToDeclare = {
            @Queue(value = "hallo_world")
    })
    public void consumer(String message){
        System.out.println("消费者消费信息："+ message);
    }

}
