package com.example.springBootDemo.mq.callback;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplatenew;

    public void send() {

        rabbitTemplatenew.setConfirmCallback(this);
        String msg = "callbackSender : i am callback sender";
        System.out.println(msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData);
    }

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}
