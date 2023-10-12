package org.bamboo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.bamboo.mapper.StudentMapper;
import org.bamboo.service.StreamObserverService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@DubboService
@Slf4j
public class StreamObserverServiceImpl implements StreamObserverService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public String StreamTest(String param) {
        return studentMapper.getStudentById(param).getName();
    }

    @Override
    public StreamObserver<String> sayHelloStream(StreamObserver<String> response) {
        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println(data);
                response.onNext("hello,"+data);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
                response.onCompleted();
            }
        };
    }

    @Override
    public void sayHelloServerStream(String request, StreamObserver<String> response) {
        for (int i = 0; i < 10; i++) {
            response.onNext("hello," + request);
        }
        response.onCompleted();
    }

    @Override
    public String sayHello(String name) {
        log.info(new Date()+"someone has been there........"+name);
        return "Hello " + name;
    }
}
