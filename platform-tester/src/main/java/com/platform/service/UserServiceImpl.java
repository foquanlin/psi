package com.platform.service;

import com.platform.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl {
    @CachePut(key = "#user.id")
    public User createUser(User user){
        System.out.println(user);
        return user;
    }
    @CachePut(key = "#user.id")
    public void updateUser(User user){

    }
    @Cacheable(key = "#user.id")
    public void findUser(User user){

    }
    @Cacheable(key = "#id")
    public User getUser(String id){
        return new User();
    }
    @CacheEvict(key = "#user.id")
    public void deleteUser(User user){

    }
}
