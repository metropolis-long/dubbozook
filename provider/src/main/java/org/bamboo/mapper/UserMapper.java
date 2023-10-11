package org.bamboo.mapper;


import org.bamboo.pojo.User;

import java.util.List;

public interface UserMapper {

    //定义方法
    List<User> findAll();
}
