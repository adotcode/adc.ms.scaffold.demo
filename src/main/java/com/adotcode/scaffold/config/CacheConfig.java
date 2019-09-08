package com.adotcode.scaffold.config;

import com.adotcode.scaffold.core.cache.FastJson2JsonRedisSerializer;
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
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    //使用FastJson2JsonRedisSerializer来序列化和反序列化redis的value值
    FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer<>(
        Object.class);
    //使用StringRedisSerializer来序列化和反序列化redis的key值
    StringRedisSerializer keySerializer = new StringRedisSerializer();
    template.setKeySerializer(keySerializer);
    template.setHashKeySerializer(keySerializer);
    template.setValueSerializer(fastJson2JsonRedisSerializer);
    template.setHashValueSerializer(fastJson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }
}
