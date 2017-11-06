package com.example.springBootDemo.controller;

import com.example.springBootDemo.mq.HelloSender1;
import com.example.springBootDemo.mq.HelloSender2;
import com.example.springBootDemo.mq.TopicSender;
import com.example.springBootDemo.mq.UserSender;
import com.example.springBootDemo.mq.callback.CallBackSender;
import com.example.springBootDemo.mq.fanout.FanoutSender;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/spring")
public class RabbitController {
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private UserSender userSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private CallBackSender callBackSender;

    /**
     * 单生产者
     */
    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
    /**
     * direct exchange
     * 多生产者-多消费者
     */
    @PostMapping("/manyToMany")
    public void manyToMany() {
        for(int i=0;i<10;i++){
            //System.out.println("thread manyToMany "+Thread.currentThread().getId());
            helloSender1.send(" helloMsg["+i+"] ");
            helloSender2.send(" helloMsg["+i+"] ");
        }
    }

    /**
     * 实体类传输测试
     */
    @PostMapping("/userTest")
    public void userTest() {
        userSender.send();
    }

    /**
     * topic exchange类型rabbitmq测试
     */
    @PostMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @PostMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

    /**
     * callback 测试
     * RabbitMQ的自动配置下AmqpTemplate的实现类没有设置rabbitTemplatenew的ConfirmCallback; 因此sender默认不会回调
     * 在调用callBackSender后由于执行了rabbitTemplatenew.setConfirmCallback(this);
     * 之后所有的sender都会执行回调函数ConfirmCallback.confirm
     */
    @PostMapping("/callback")
    public void callback() {
        callBackSender.send();
    }

}
