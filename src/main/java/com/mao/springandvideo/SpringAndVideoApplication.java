package com.mao.springandvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.mao.springandvideo.mapper")
@SpringBootApplication
public class SpringAndVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAndVideoApplication.class, args);
    }
}
