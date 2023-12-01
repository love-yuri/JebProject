package com.yuri.exp_9.mapper;

import com.yuri.exp_9.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int insert(Book book);
    int deleteById(int id);

    List<Book> getBooks();
}