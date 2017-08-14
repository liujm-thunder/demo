package com.appchina.collect.service;

import com.appchina.collect.dao.UserDao;
import com.appchina.collect.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by liujianmeng on 2017/8/14.
 */
@Service
public class UserDaoService {
    @Autowired
    private UserDao userDao;

    public List<User> getUserByUserCode(Integer id) {
        List<User> user = userDao.getUserByUserCode(id);
        return user;
    }

}
