package com.example.springBootDemo.mq;

import com.example.springBootDemo.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        User user=new User();
        user.setUserName("testUser");
        user.setPassWord("111111");
        user.setEmail("testUser@h3c.com");
        user.setNickName("test");
        user.setRegTime(new Date().toString());
        System.out.println("user send : " + user.getUserName()+"/"+user.getPassWord());
        this.rabbitTemplate.convertAndSend("userQueue", user,correlationData);
    }

}
