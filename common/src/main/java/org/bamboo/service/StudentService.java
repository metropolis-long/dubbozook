package org.bamboo.service;

import org.apache.dubbo.common.stream.StreamObserver;
import org.bamboo.pojo.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(String name);

    default void sayHelloSteam(String param, StreamObserver<Student> stuStreamObserver){

    }
}
