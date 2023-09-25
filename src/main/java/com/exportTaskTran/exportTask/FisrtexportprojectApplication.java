package com.miniproject.fisrtexportproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.miniproject")
@SpringBootApplication
public class FisrtexportprojectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FisrtexportprojectApplication.class, args);
        
        System.out.println("Line1");//so luong column ko duoc qua lon

    }

}
