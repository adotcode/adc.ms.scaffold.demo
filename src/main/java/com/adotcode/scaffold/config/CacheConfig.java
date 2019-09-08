package com.adotcode.scaffold.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 缓存配置
 *
 * @author risfeng
 * @date 2019/09/07
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    return RedisCacheManager.builder(connectionFactory).build();
  }

  @Bean
  public <TValue> RedisTemplate<String, TValue> redisTemplate(
      RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, TValue> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    //默认使用GenericFastJsonRedisSerializer来序列化和反序列化redis的value值
    GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
    //使用StringRedisSerializer来序列化和反序列化redis的key值
    StringRedisSerializer keySerializer = new StringRedisSerializer();
    template.setKeySerializer(keySerializer);
    template.setHashKeySerializer(keySerializer);
    template.setDefaultSerializer(genericFastJsonRedisSerializer);
    template.setValueSerializer(genericFastJsonRedisSerializer);
    template.setHashValueSerializer(genericFastJsonRedisSerializer);
    template.setEnableDefaultSerializer(true);
    template.afterPropertiesSet();
    return template;
  }
}
