package org.bamboo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("org.bamboo.mapper")
public class ProviderAPP {

    @Value("${bamboo.name}")
    private static  String name;
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println(name);
        SpringApplication.run(ProviderAPP.class,args);
    }
}
