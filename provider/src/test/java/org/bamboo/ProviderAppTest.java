package org.bamboo;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.pojo.Student;
import org.bamboo.service.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class ProviderAppTest {

    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void test(){

        List<Student> students = studentMapper.queryAll();
        Student student = studentMapper.getStudentById("1");
        System.out.println("students = " + students);
        System.out.println(student);
    }


}
