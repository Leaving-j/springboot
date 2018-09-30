package com.restful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.restful.bo")
public class RestfulprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulprojectApplication.class, args);
    }
}
