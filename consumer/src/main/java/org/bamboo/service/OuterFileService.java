package org.bamboo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OuterFileService {
    @Autowired
    private Environment environment;


    public String getA(){
        System.out.println("============   is "+environment.getProperty("system-name")+"的值");

        return environment.getProperty("system-name");
    }
}
