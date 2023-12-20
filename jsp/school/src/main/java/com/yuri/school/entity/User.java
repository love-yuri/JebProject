package com.yuri.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer id; // 主键id

    @Column(name = "username", length = 100)
    private String username; // 账号

    @Column(name = "password", length = 100)
    private String password; // 密码
}
