package org.bamboo.tools;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ServerProperties.class, WebMvcProperties.class})
@Configuration
public class CommonConfig implements CommandLineRunner {


    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }



    @Override
    public void run(String... args) throws Exception {

    }

    @ConditionalOnClass(RedisTemplate.class)
    @Configuration
    static class DependenceRedisConfig {

        @Bean("redisTemplate")
        @ConditionalOnMissingBean
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory,
                                                           Jackson2ObjectMapperBuilder builder) {
            ObjectMapper objectMapper = builder.createXmlMapper(false).build();
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
            // fastjson序列化
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
            redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
            redisTemplate.setConnectionFactory(connectionFactory);
            return redisTemplate;
        }

    }


}