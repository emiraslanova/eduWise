package com.example.eduwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.example.eduwise" , "com.example.eduwise.mapper"})
public class EduWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduWiseApplication.class, args);
    }

}
