package org.bamboo.plugins.register;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class NyBeanRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        System.out.println("我是处理中...");
        Map<String, Object> attributes = metadata.getAnnotationAttributes(DubboReference.class.getName());
       attributes.forEach((a,v)->{
           System.out.println(a+"--------------"+v);
       });
    }
}
