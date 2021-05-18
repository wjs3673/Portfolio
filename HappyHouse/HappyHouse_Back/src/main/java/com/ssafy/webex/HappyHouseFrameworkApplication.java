package com.ssafy.webex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.ssafy.webex.model.repo")
public class HappyHouseFrameworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(HappyHouseFrameworkApplication.class, args);
	}
}
