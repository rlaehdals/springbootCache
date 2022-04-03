package com.example.cache.repository;

import com.example.cache.domain.RedisMember;
import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<RedisMember,String> {
}
