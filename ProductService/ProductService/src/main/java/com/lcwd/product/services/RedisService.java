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
        log.info("get key:{}",key);
        Product p= (Product) redisTemplate.opsForValue().get(key);
        try
        {
            log.info("got data for Redis");
            return p;
        }
        catch (Exception e)
        {
            log.error("ExceptionGet",e);
            return null;
        }
    }
    public void set(String key,Product p,Long ttl) {
        try {
            redisTemplate.opsForValue().set(key,p,ttl, TimeUnit.SECONDS );
            log.info("Set Done");
        }
        catch (Exception e)
        {
            log.error("ExceptionSet",e);
        }
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
            log.info("Delete Done");
        } catch (Exception e) {
            log.error("ExceptionDel", e);
        }
    }
}
