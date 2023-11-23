package org.bamboo.anno;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OperatorComponentRegister implements ImportBeanDefinitionRegistrar , EnvironmentAware, ResourceLoaderAware {

    private Environment  environment;
    private ResourceLoader resourceLoader;

    @Override
    public void setEnvironment(Environment environment) {
            this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
//        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
        System.out.println("start  register。。。。。。。。。。。。。。。。。");
        Set<String> basePages = new HashSet<>();
        Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableOperator.class.getName());
        for (String base : ((String[]) attributes.get("value"))) {
            if (StringUtils.hasText(base)){
                basePages.add(base);
            }
        }

        if (basePages.isEmpty()){
            basePages.add(ClassUtils.getPackageName(metadata.getClassName()));
        }

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(){

            @Override
            public  boolean  isCandidateComponent(AnnotatedBeanDefinition annotatedBeanDefinition){
                return  true;
            }

        };

        scanner.setEnvironment(environment);
        scanner.setResourceLoader(resourceLoader);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Operator.class));

        for(String base: basePages) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(base);
            for (BeanDefinition beanDefinition : candidateComponents) {
                if (beanDefinition instanceof AnnotatedBeanDefinition) {
                    AnnotatedBeanDefinition definition = (AnnotatedBeanDefinition) beanDefinition;
                    AnnotationMetadata meta = definition.getMetadata();
                    registerOperatorComponent(meta, registry);
                }
            }
        }
    }


    private void registerOperatorComponent(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(OperatorFactoryBean.class);
        String className = metadata.getClassName();
        beanDefinitionBuilder.addPropertyValue("type",className);
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Operator.class.getName());
        if(StringUtils.isEmpty(annotationAttributes)) return;
        beanDefinitionBuilder.addPropertyValue("expression",annotationAttributes.get("value"));

        beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();

        try {
            abstractBeanDefinition.setAttribute(FactoryBean.OBJECT_TYPE_ATTRIBUTE,Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(abstractBeanDefinition, className);
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,registry);
        System.out.println("finish  register。。。。。。。。。。。。。。。。。");
    }


}
