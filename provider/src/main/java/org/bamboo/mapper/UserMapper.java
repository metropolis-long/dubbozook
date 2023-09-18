package org.bamboo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.bamboo.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    //定义方法
    List<User> findAll();
}
