package org.bamboo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.bamboo.mapper.StuMapper;
import org.bamboo.pojo.Stu;
import org.bamboo.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
@Slf4j
public class StuServiceImpl implements StuService {


    @Autowired
    private StuMapper stuMapper;

    @Override
    public List<Stu> getStudents(String name) {
        return stuMapper.queryAll();
    }

    @Override
    public void sayHelloSteam(String param, StreamObserver<Stu> stuStreamObserver) {
        org.bamboo.service.StuService.super.sayHelloSteam(param, stuStreamObserver);
    }
}
