package com.example.boot_project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class BootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootProjectApplication.class, args);
    }

}
