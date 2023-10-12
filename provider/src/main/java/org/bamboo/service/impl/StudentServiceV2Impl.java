package org.bamboo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.pojo.Student;
import org.bamboo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@DubboService(version = "2.0")
@Slf4j
public class StudentServiceV2Impl implements StudentService {


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudents(String name) {
        log.info("someone has been :  "+name);
        return studentMapper.queryAll();
    }

}
