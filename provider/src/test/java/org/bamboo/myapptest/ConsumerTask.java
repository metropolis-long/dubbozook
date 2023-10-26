package org.bamboo.myapptest;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.service.StreamObserverService;
import org.bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ConsumerTask implements CommandLineRunner {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Receive result ======> " );

        new Thread(() -> {
           studentMapper.queryAll().stream().forEach(
                   student -> System.out.println(student));
        }).start();
    }
}
