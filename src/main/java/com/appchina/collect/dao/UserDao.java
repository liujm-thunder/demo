package com.appchina.collect.dao;

import com.appchina.collect.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liujianmeng on 2017/8/14.
 */
public interface UserDao {
    List<User> getUserByUserCode(@Param("id") Integer id);
}
