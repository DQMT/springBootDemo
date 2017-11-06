package com.example.springBootDemo.dao;

import com.example.springBootDemo.domain.MongodbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSaveUser() throws Exception {
        MongodbUser user=new MongodbUser();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        userDAO.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        MongodbUser user= userDAO.findUserByUserName("小明");
        System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        MongodbUser user=new MongodbUser();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDAO.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userDAO.deleteUserById(1l);
    }

}
