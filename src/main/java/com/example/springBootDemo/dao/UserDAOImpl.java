package com.example.springBootDemo.dao;

import com.example.springBootDemo.domain.MongodbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository(value="userDAO")
public class UserDAOImpl  implements UserDAO  {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(MongodbUser user) {
        mongoTemplate.save(user);
    }

    @Override
    public MongodbUser findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        MongodbUser user =  mongoTemplate.findOne(query , MongodbUser.class);
        return user;
    }

    @Override
    public void updateUser(MongodbUser user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,MongodbUser.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,MongodbUser.class);
    }
}
