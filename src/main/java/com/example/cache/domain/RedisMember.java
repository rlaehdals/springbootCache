package com.example.cache.domain;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash(value = "memberDto")
@Getter
public class RedisMember {

    @Id
    private String id;
    private String name;
    private int age;

    public RedisMember(String name, int age) {
        this.id= name;
        this.name = name;
        this.age = age;
    }
}
