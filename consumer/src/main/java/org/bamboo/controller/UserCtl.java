package org.bamboo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.pojo.Student;
import org.bamboo.service.StudentService;
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
    private StudentService studentService;
    @GetMapping("/time")
    public String get(){
        String time = userService.getUser();
        List<Student> bamboo = studentService.getStudents("bamboo");
        for (Student s :bamboo) {
            System.out.println("s = " + s);
        }
        return time;
    }
}
