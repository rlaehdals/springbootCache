package com.example.cache;

import com.example.cache.domain.Member;
import com.example.cache.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching // 추가
@RequiredArgsConstructor
public class CacheApplication {

    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }


    @PostConstruct
    public void createMember(){
        for(int i=0; i<10; i++){
            Member member = Member.createMember("m" + i, i + 1, "s" + i);
            memberRepository.save(member);
            System.out.println(memberRepository);
        }
    }

}
