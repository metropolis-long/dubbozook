package org.bamboo.plugins.beans;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是启动时加载...");
    }
}