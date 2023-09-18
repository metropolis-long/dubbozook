package org.bamboo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.bamboo.pojo.Student;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    List<Student> queryAll();
}
