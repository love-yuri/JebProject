package com.yuri.exp_9.service;

import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.entity.User;
import com.yuri.exp_9.mapper.BookMapper;
import com.yuri.exp_9.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserService {
    public int insert(User user) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int ret = userMapper.insert(user);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public User findByUserName(String username) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findByUserName(username);
            sqlSession.commit();
            sqlSession.close();
            return user;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}