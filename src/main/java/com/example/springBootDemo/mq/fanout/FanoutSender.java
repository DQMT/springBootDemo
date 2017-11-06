package com.example.springBootDemo.mq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String msgString="fanoutSender :hello　";
        System.out.println(msgString);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend("fanoutExchange","abcd.ee", msgString,correlationData);
    }

}
