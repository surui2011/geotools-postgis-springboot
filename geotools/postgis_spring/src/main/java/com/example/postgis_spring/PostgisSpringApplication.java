package com.example.postgis_spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.postgis_spring")
public class PostgisSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgisSpringApplication.class, args);
    }

}
