package org.bamboo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo
@MapperScan("org.bamboo.mapper")
@ComponentScan(basePackages = {"org.bamboo.pojo"})
public class ProviderAPP {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAPP.class,args);
    }
}
