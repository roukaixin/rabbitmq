package com.kai;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
class RabbitmqSpringbootApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    void hello_world(){
        rabbitTemplate.convertAndSend("hallo_world","hallo world");
    }

    @Test
    void work(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work" + i);
        }
    }

    @Test
    void fanout(){
        rabbitTemplate.convertAndSend("fanout","","fanout 模式");
    }

    @Test

    void direct(){
        rabbitTemplate.convertAndSend("direct","user.add","direct 模式");
    }

    @Test
    // # 表示0个或多个
    // * 表示一个
    void topic(){
        rabbitTemplate.convertAndSend("topic","user.add","topic 模式");
        rabbitTemplate.convertAndSend("topic","user.add.or.update","topic 模式");
    }

}
