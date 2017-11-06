package com.example.springBootDemo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class HelloSender2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello2 " ;
        send(sendMsg);
    }
    public void send(String sendMsg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender2 : " +" thread: "+Thread.currentThread().getId() +" "+ sendMsg +" "+ System.currentTimeMillis());
        this.rabbitTemplate.convertAndSend(null,"helloQueue", sendMsg,correlationData);
    }
}