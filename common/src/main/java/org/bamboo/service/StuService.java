package org.bamboo.service;

import org.apache.dubbo.common.stream.StreamObserver;
import org.bamboo.pojo.Stu;

import java.util.List;

public interface StuService {

    List<Stu> getStudents(String name);

    default void sayHelloSteam(String param, StreamObserver<Stu> stuStreamObserver){

    }
}
