package org.bamboo.controller;

import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.pojo.Student;
import org.bamboo.service.StreamObserverService;
import org.bamboo.service.StreamObserverServiceImpl;
import org.bamboo.service.StudentService;
import org.bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping
public class UserCtl {

    @DubboReference
    public UserService userService;

    @DubboReference(version = "1.0")
    private StudentService studentService;

    @Autowired
    private JedisPool jedisPool;

    @DubboReference
    private StreamObserverService streamObserverService;
    @GetMapping("/time")
    public String get(){
        String username = jedisPool.getResource().get("username");
        if (username == null || "".equals(username)){
            username = userService.getUser();
            jedisPool.getResource().set("username",username);
        }
        List<Student> bamboo = studentService.getStudents("bamboo");
        for (Student s :bamboo) {
            System.out.println("s = " + s);
        }
        return username;
    }

    @GetMapping("/st/{c}")
    public String testBI(@PathVariable String c){
        StreamObserver<String> streamObserver = streamObserverService.sayHelloStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println(data);
                System.out.println("返回数据了 "+data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("交流结束   onCompleted");
            }
        });

        streamObserver.onNext(c);
        streamObserver.onNext(c+"第二次");
        streamObserver.onCompleted();
        return "ok";
    }


}
