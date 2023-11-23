package org.bamboo;

import org.bamboo.anno.EnableOperator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOperator
public class ThirdApp {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(ThirdApp.class);
    }
}