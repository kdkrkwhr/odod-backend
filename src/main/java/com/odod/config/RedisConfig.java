package com.odod.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String REDIS_HOST;

  @Value("${spring.redis.port}")
  private int REDIS_PORT;

  @Value("${spring.redis.database}")
  private int REDIS_DATABASE_INDEX;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(REDIS_HOST, REDIS_PORT);
    lettuceConnectionFactory.setDatabase(REDIS_DATABASE_INDEX);
    return lettuceConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
    stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
    stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
    stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
    return stringRedisTemplate;
  }
}
