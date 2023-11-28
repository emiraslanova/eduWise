package com.example.eduwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.example.eduwise" , "com.example.eduwise.mapper"})
public class EduWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduWiseApplication.class, args);
    }

}
