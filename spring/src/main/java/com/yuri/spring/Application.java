package com.yuri.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuri.spring.mapper")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
