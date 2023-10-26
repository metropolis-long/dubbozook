package org.bamboo.task;

import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.service.StreamObserverService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

//@Component
public class ConsumerTask implements CommandLineRunner {
    @DubboReference()
    private StreamObserverService streamObserverService;

    private AtomicLong atomic = new AtomicLong(1L);

    @Override
    public void run(String... args) throws Exception {
        String result = streamObserverService.sayHello("world"+atomic.getAndIncrement());
        System.out.println("Receive result ======> " + result);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + " Receive result ======> "
                            + streamObserverService.sayHello("world"+atomic.getAndIncrement()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
