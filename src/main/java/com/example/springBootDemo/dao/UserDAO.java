package com.example.springBootDemo.dao;

import com.example.springBootDemo.domain.MongodbUser;

public interface UserDAO {
    public void saveUser(MongodbUser user);

    public MongodbUser findUserByUserName(String userName);

    public void updateUser(MongodbUser user);

    public void deleteUserById(Long id);
}
