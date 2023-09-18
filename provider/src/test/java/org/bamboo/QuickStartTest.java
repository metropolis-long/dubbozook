package org.bamboo;

import org.bamboo.mapper.StudentMapper;
import org.bamboo.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration
public class QuickStartTest {
    @Autowired
    private StudentMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = userMapper.queryAll();
        userList.forEach(System.out::println);
    }
}