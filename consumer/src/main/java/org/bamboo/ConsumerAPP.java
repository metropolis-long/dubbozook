package org.bamboo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.bamboo.plugins.annotation.EnableOperator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@EnableOperator
public class ConsumerAPP {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAPP.class,args);
    }
}
