package com.appchina.collect.test.mybatis;


import com.appchina.collect.test.mybatis.model.DeveloperInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectDemo {


    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatisConfig.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        SqlSession sqlSession  = sqlSessionFactory.openSession();
        Map<String,Object> params = new HashMap<>();

        params.put("userId",2);

        Date first = new Date();
        List<DeveloperInfo> result = sqlSession.selectList("com.appchina.collect.test.mybatis.dao.DeveloperInfoMapper.selectByMinUserId",params);

        System.out.println("first query cost : "+(new Date().getTime()-first.getTime())+" ms");

        System.out.println(result.size());

//        sqlSession.clearCache();
        Date second = new Date();
        List<DeveloperInfo> result2 = sqlSession.selectList("com.appchina.collect.test.mybatis.dao.DeveloperInfoMapper.selectByMinUserId",params);

        System.out.println("first query cost : "+(new Date().getTime()-second.getTime())+" ms");

        System.out.println(result2.size());
    }
}
