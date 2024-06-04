package com.lcwd.product.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcwd.product.entites.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService  {
    @Autowired
    private RedisTemplate redisTemplate;
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key,value);
    }
    public Product get(String key, Class<Product> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        }
        catch (Exception e)
        {
            log.error("Exceptioin",e);
            return null;
        }
    }
    public void set(String key,Object o,Long ttl) {
        try {
            redisTemplate.opsForValue().set(key,o,ttl, TimeUnit.SECONDS );
        }
        catch (Exception e)
        {
            log.error("Exceptioin",e);
        }
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}
