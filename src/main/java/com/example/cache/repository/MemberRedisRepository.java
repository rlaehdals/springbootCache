package com.example.cache.repository;

import com.example.cache.domain.RedisMember;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRedisRepository extends CrudRepository<RedisMember,String> {
}
