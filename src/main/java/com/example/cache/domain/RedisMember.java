package com.example.cache.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@RedisHash(value = "redisMember")
public class RedisMember{

    @Id
    private String id;
    private String name;
    private int age;

    public RedisMember(String name, int age) {
        this.id= name;
        this.name = name;
        this.age = age;
    }

    public void changeAge(int age){
        this.age=age;
    }

    @Override
    public boolean equals(Object obj) {
        RedisMember obj1 = (RedisMember) obj;
        return this.name.equals(obj1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
