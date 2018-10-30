package com.appchina.collect.test.mybatis.dao;

import com.appchina.collect.test.mybatis.model.DeveloperInfo;

import java.util.List;

public interface DeveloperInfoMapper {
    List<DeveloperInfo> selectByMinUserId(Integer userId);
}
