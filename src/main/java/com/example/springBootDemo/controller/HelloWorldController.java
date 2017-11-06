package com.example.springBootDemo.controller;

import com.example.springBootDemo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //自定义Property
    @Value("${test.msg}")
    private String msg;

    //返回String
    @RequestMapping("/hello")
    public String index() {
        return msg;
    }

    //返回对象，以json解析
    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUserName("小明");
        user.setPassWord("xxxx");
        return user;
    }
}