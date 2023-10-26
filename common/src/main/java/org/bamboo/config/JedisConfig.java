package org.bamboo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
@Slf4j
public class JedisConfig extends CachingConfigurerSupport {
	@Value("${spring.data.redis.host}")
	private String host;

	@Value("${spring.data.redis.port}")
	private int port;

	@Value("${spring.data.redis.password}")
	private String password;
	@Value("${spring.data.redis.timeout}")
	private int timeout;

	@Value("${spring.data.redis.database}")
	private int database;
	@Value("${spring.data.redis.jedis.pool.max-active}")
	private int maxActive;

	@Value("${spring.data.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.data.redis.jedis.pool.min-idle}")
	private int minIdle;

	@Bean
	public JedisPool redisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxTotal(maxActive);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password,database);
		return jedisPool;	
	}

}
