package com.example.springBootDemo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String sendMsg) {
        System.out.println("Receiver1  : "+" thread: "+Thread.currentThread().getId() + " "+ sendMsg +" " + System.currentTimeMillis());
    }

}
