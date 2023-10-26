package org.bamboo.service;


public interface JedisService {

    String set(String key, String value);

    String set(String key, Object value);

    Object getObject(String key);

    String get(String key);
    Boolean isKeyExists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);    
    Long hdel(String key,String... field);//删除hkey    
}