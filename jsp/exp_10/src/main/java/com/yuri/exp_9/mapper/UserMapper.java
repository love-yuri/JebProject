package com.yuri.exp_9.mapper;

import com.yuri.exp_9.entity.User;

public interface UserMapper {
    int insert(User user);
    User findByUserName(String username);
}
