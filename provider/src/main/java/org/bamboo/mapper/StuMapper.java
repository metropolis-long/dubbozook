package org.bamboo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.bamboo.pojo.Stu;

import java.util.List;

public interface StuMapper extends BaseMapper<Stu> {
    List<Stu> queryAll();
}
