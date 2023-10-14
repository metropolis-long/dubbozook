package org.bamboo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.mapper.UserMapper;
import org.bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

//@DubboService
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUser() {
        return userMapper.findAll().get(1).getName();
    }
}
