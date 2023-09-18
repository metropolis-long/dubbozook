package org.bamboo.service;

import org.apache.dubbo.common.stream.StreamObserver;

public interface StreamObserverService {
    String StreamTest(String param);
    public String sayHello(String name);
    StreamObserver<String> sayHelloStream(StreamObserver<String> response);

    void sayHelloServerStream(String request, StreamObserver<String> response);
}

