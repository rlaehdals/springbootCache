package com.example.cache;

import com.example.cache.domain.Member;
import com.example.cache.domain.RedisMember;
import com.example.cache.repository.MemberRedisRepository;
import com.example.cache.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableCaching // 추가
@RequiredArgsConstructor
public class CacheApplication {


    private final RedisTemplate<String, RedisMember> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);

    }

    @PostConstruct
    public void init(){
        for(int i=0; i<10; i++){
            RedisMember redisMember = new RedisMember("m" + i, i);
            redisTemplate.opsForValue().set(redisMember.getId(),redisMember);
        }
    }
}
