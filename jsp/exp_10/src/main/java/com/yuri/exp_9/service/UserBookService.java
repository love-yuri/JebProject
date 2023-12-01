package com.yuri.exp_9.service;

import com.yuri.exp_9.entity.User;
import com.yuri.exp_9.entity.UserBook;
import com.yuri.exp_9.mapper.BookMapper;
import com.yuri.exp_9.mapper.UserBookMapper;
import com.yuri.exp_9.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserBookService {
    public int insert(UserBook user) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserBookMapper userBookMapper = sqlSession.getMapper(UserBookMapper.class);
            int ret = userBookMapper.insert(user);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<UserBook> findByUserName(Integer id) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserBookMapper userBookMapper = sqlSession.getMapper(UserBookMapper.class);
            List<UserBook> ret = userBookMapper.findByUserId(id);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public UserBook findByBookId(Integer id) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserBookMapper userBookMapper = sqlSession.getMapper(UserBookMapper.class);
            UserBook ret = userBookMapper.findByBookId(id);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int deleteById(Integer id) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserBookMapper userBookMapper = sqlSession.getMapper(UserBookMapper.class);
            int ret = userBookMapper.deleteById(id);
            System.out.println(ret);
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}