package org.bamboo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.pojo.Stu;
import org.bamboo.service.StuService;
import org.bamboo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserCtl {

    @DubboReference
    public UserService userService;

    @DubboReference
    private StuService stuService;
    @GetMapping("/time")
    public String get(){
        String time = userService.getUser();
        List<Stu> bamboo = stuService.getStudents("bamboo");
        for (Stu s :bamboo) {
            System.out.println("s = " + s);
        }
        return time;
    }
}
