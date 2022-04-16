package com.example.cache;

import com.example.cache.domain.RedisMember;
import com.example.cache.repository.MemberRedisRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    MemberRedisRepository memberRedisRepository;

    @Autowired
    RedisTemplate<String, RedisMember> redisMemberRedisTemplate;

    @Test
    @DisplayName("redisRepository 테스트")
    void repositoryTest(){

        memberRedisRepository.save(new RedisMember("hi",10));

        RedisMember redisMember = memberRedisRepository.findById("hi").get();

        assertThat(redisMember.getName()).isEqualTo("hi");

    }

    @Test
    @DisplayName("redisRepository 데이터변경 테스트")
    void repositoryUpdateTest(){

        memberRedisRepository.save(new RedisMember("hi",10));

        memberRedisRepository.save(new RedisMember("hi",11));

        RedisMember redisMember = memberRedisRepository.findById("hi").get();

        assertThat(redisMember.getAge()).isEqualTo(11);

    }

    @Test
    @DisplayName("redisRepository 전체 반환 테스트")
    void repositoryFindAllTest(){
        memberRedisRepository.save(new RedisMember("hi1",10));

        memberRedisRepository.save(new RedisMember("hi2",11));


        Iterable<RedisMember> all = memberRedisRepository.findAll();

        List<RedisMember> list = new ArrayList<>();

        all.forEach(list::add);

        assertThat(list.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("redisTemplate value 테스트")
    void redisTemplateTest(){
        ValueOperations<String, RedisMember> stringRedisMemberValueOperations = redisMemberRedisTemplate.opsForValue();

        stringRedisMemberValueOperations.set("hi", new RedisMember("hi", 10));

        RedisMember redisMember = redisMemberRedisTemplate.opsForValue().get("hi");

        assertThat(redisMember.getAge()).isEqualTo(10);

    }

    @Test
    @DisplayName("redisTemplate list 테스트")
    void redisTemplateListTest(){
        ListOperations<String, RedisMember> stringRedisMemberListOperations = redisMemberRedisTemplate.opsForList();

        stringRedisMemberListOperations.rightPush("redisMemberList",new RedisMember("hi1",10));

        stringRedisMemberListOperations.rightPush("redisMemberList",new RedisMember("hi2",11));

        Long size = stringRedisMemberListOperations.size("redisMemberList");

        RedisMember result = stringRedisMemberListOperations.index("redisMemberList", 1);

        List<RedisMember> redisMemberList = stringRedisMemberListOperations.range("redisMemberList", 0, 1);

        stringRedisMemberListOperations.set("redisMemberList",0,new RedisMember("m1",10));

    }
    @Test
    @DisplayName("redisTemplate set 테스트")
    void redisTemplateSetTest(){
        SetOperations<String, RedisMember> stringRedisMemberSetOperations = redisMemberRedisTemplate.opsForSet();

        stringRedisMemberSetOperations.add("memberSet",new RedisMember("h1",10));
        stringRedisMemberSetOperations.add("memberSet",new RedisMember("h2",10));

        RedisMember randomRedisMember = stringRedisMemberSetOperations.pop("memberSet");

        List<RedisMember> randomMemberSet = stringRedisMemberSetOperations.pop("memberSet", 2);

        Set<RedisMember> memberSet = stringRedisMemberSetOperations.members("memberSet");

        Boolean result = stringRedisMemberSetOperations.isMember("memberSet", new RedisMember("h1", 10));

        Long re = stringRedisMemberSetOperations.remove("memberSet", new RedisMember("hi2", 10));

    }
    @Test
    @DisplayName("redisTemplate zSet 테스트")
    void redisTemplateZSetTest(){
        ZSetOperations<String, RedisMember> stringRedisMemberZSetOperations = redisMemberRedisTemplate.opsForZSet();
        stringRedisMemberZSetOperations.add("memberZSet",new RedisMember("hi1",10),1);
        stringRedisMemberZSetOperations.add("memberZSet",new RedisMember("hi2",10),2);

        Long memberZSet = stringRedisMemberZSetOperations.count("memberZSet", 0, 1);

        RedisMember result = stringRedisMemberZSetOperations.popMin("memberZSet").getValue();

    }

    @Test
    @DisplayName("redisTemplate hash 테스트")
    void redisTemplateHashTest(){

        HashOperations<String, Object, Object> stringObjectObjectHashOperations =
                redisMemberRedisTemplate.opsForHash();
        stringObjectObjectHashOperations.put("memberHashOne","name",new RedisMember("hi3",10));
        RedisMember redisMember = (RedisMember) stringObjectObjectHashOperations.get("memberHashOne", "name");

        Map<String, RedisMember> map = new HashMap<>();

        map.put("hi1",new RedisMember("hi1", 10));
        map.put("hi2",new RedisMember("hi2", 10));

        stringObjectObjectHashOperations.putAll("memberHashMap",map);

        RedisMember result = (RedisMember) stringObjectObjectHashOperations.get("memberHashMap", "hi1");

        System.out.println(result.getName());
    }
}
