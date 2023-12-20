package com.yuri.school.service;

import com.yuri.school.entity.User;
import com.yuri.school.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import com.yuri.school.Repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Component
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public R<Boolean> register(User user) {
        R<Boolean> r = R.success(true);
        User newUser = userRepository.findUserByUsername(user.getUsername());
        if(newUser != null) {
            r.setMsg("该用户已存在!!");
            r.setIsSuccess(false);
            return r;
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            r.setMsg("注册错误!!");
            r.setIsSuccess(false);
        }
        return r;
    }

    public R<Boolean> login(User user) {
        R<Boolean> r = R.success(true);
        User newUser = userRepository.findUserByUsername(user.getUsername());
        if(newUser == null) {
            r.setMsg("该用户不存在，请先去注册");
            r.setIsSuccess(false);
        } else if(!Objects.equals(newUser.getPassword(), user.getPassword())) {
            r.setMsg("密码错误!");
            r.setIsSuccess(false);
        }
        return r;
    }
}
