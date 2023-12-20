package com.yuri.school.Repository;

import com.yuri.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsername(String username);
}
