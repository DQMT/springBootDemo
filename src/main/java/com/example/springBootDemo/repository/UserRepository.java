package com.example.springBootDemo.repository;

import com.example.springBootDemo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring data jpa实现数据访问层；
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository可以根据方法名来自动生产SQL
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);

    //分页查询
    Page<User> findByPassWord(String passWord, Pageable pageable);

    //在Query注解中自定义sql
    @Transactional(timeout = 10)
    @Query("select u from User u where u.nickName = ?1")
    User findByNickName(String nickName);

    //修改数据库时必须加事务@Transactional+@Modifying
    @Transactional(timeout = 10)
    @Modifying
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);
}
