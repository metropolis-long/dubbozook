package org.bamboo;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.pojo.Student;
import org.bamboo.service.StudentService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@SpringBootTest
public class ProviderAppTest {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @DubboReference(version = "1.0",registry = {"zookeeper://localhost:2181"})
    private StudentService studentService;
    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test(){

        List<Student> students = studentMapper.queryAll();
        Student student = studentMapper.getStudentById("1");
        System.out.println("students = " + students);
        System.out.println(student);
    }
    @Test
    public void test3 (){

        List<Student> students = studentService.getStudents("111");
        System.out.println("students = " + students);
    }
    @Test
    public void testRedis(){
        ValueOperations operations = redisTemplate.opsForValue();
//        operations.set("name33","记得记得");
        Object o = operations.get("name33");
        System.out.println(o);
        Jedis resource = jedisPool.getResource();
        String name33 = resource.get("\\xAC\\xED\\x00\\x05t\\x00\\x06name33");
        resource.set("name33","记得记得");
        String name331 = resource.get("name33");
        resource.close();
        System.out.println(name33);
        System.out.println(name331);


    }


}
