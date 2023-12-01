package com.yuri.exp_9.service;

import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BookService {
    public int insert(Book book) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            int ret = bookMapper.insert(book);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<Book> getBooks() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            List<Book> books = bookMapper.getBooks();
            sqlSession.commit();
            sqlSession.close();
            return books;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int delete(Integer id) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            int ret = bookMapper.deleteById(id);
            sqlSession.commit();
            sqlSession.close();
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}