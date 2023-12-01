package com.yuri.exp_9.mapper;

import com.yuri.exp_9.entity.User;
import com.yuri.exp_9.entity.UserBook;

import java.util.List;

public interface UserBookMapper {
    List<UserBook> findByUserId(Integer id);
    UserBook findByBookId(Integer id);
    int deleteById(int id);
    int insert(UserBook userBook);
}
