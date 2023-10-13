package org.bamboo;

import org.bamboo.mapper.UserMapper;
import org.bamboo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@SpringBootTest
public class SpringBootMybatisPlusTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test(){

        List<User> users = userMapper.findAll();

        System.out.println("users = " + users);
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
