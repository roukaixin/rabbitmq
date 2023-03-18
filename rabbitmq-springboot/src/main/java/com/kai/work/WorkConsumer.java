package com.kai.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work模式
 *
 * @author 不北咪
 * @date 2023/3/19 3:53
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = {
            @Queue(value = "work")
    })
    public void consumer1(String message){
        System.out.println("consumer1 ==> work模型消费：" + message);
    }

    @RabbitListener(queuesToDeclare = {
            @Queue(value = "work")
    })
    public void consumer2(String message){
        System.out.println("consumer2 ==> work模型消费：" + message);
    }

}
