package org.bamboo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvCtrl {

    @Autowired
    private Environment environment;
    @GetMapping("/name")
    public String get(){

        System.out.println("=============="+environment.getProperty("system-name")+"的值");
        return environment.getProperty("system-name");
    }
}
