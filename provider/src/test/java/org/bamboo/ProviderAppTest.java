package org.bamboo;

import org.bamboo.mapper.StudentMapper;
import org.bamboo.pojo.Student;

import org.bamboo.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class ProviderAppTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testRedis(){
        redisCache.setCacheObject("jiejie","姐姐");
        Object jiejie = redisCache.getCacheObject("jiejie");
        System.out.println(jiejie);
    }

    @Test
    public void test(){

        List<Student> students = studentMapper.queryAll();
        Student student = studentMapper.getStudentById("1");
        System.out.println("students = " + students);
        System.out.println(student);
    }


}
