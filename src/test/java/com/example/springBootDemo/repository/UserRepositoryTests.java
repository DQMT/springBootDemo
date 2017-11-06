package com.example.springBootDemo.repository;

import com.example.springBootDemo.domain.User;
import javafx.application.Application;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate);

        userRepository.save(new User("aa","aa1", "aa@126.com",  "aa123456", formattedDate));
        userRepository.save(new User("bb","bb2", "bb@126.com",  "bb123456", formattedDate));
        userRepository.save(new User("cc","cc3", "cc@126.com",  "cc123456", formattedDate));
        userRepository.save(new User("dd","aa1", "dd@126.com",  "dd123456", formattedDate));
        userRepository.save(new User("ee","aa1", "ee@126.com",  "ee123456", formattedDate));
        userRepository.save(new User("ff","aa1", "ff@126.com",  "ff123456", formattedDate));

        int page=1,size=3;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> userPage = userRepository.findByPassWord("aa1", pageable);
        Assert.assertEquals(3, userPage.getSize());

        userRepository.modifyByIdAndUserId("ccc",3l);
        Assert.assertEquals(6, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByNickName("bb123456").getUserName());
        Assert.assertEquals("cc123456", userRepository.findByUserNameOrEmail("bb1", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }

}
