package org.bamboo.outerc.onfig.file;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

public class OuterFileConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> , Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //获取配置文件的路径
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //在application配置文件中的设置外部文件，没有设置就读取默认值
        String property = environment.getProperty("config-app.file", "outer-file.properties");
        //读取内容
        OuterFileCachedProperty cachedProperty = new OuterFileCachedProperty(property);
        MapPropertySource propertySource = new MapPropertySource("my-config",cachedProperty);
        //注册到spring
        environment.getPropertySources().addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,propertySource);

    }

    @Override
    public int getOrder() {
        //优先级设置为1
        return 0;
    }
}
