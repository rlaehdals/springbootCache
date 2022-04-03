package com.example.cache;

import com.example.cache.domain.RedisMember;
import com.example.cache.repository.MemberRedisRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    @Autowired
    MemberRedisRepository memberRedisRepository;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    @DisplayName("레디시 저장 테스트")
    void redisSave(){

        RedisMember asd = new RedisMember( "asd", 1);

        memberRedisRepository.save(asd);
    }


    @Test
    @DisplayName("레디시 템플릿 테스트")
    void redisTemplate(){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

        stringStringValueOperations.set("hi","bye");

        String hi = stringStringValueOperations.get("hi");

        Assertions.assertThat(hi).isEqualTo("key");
    }
}
