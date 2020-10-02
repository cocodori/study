package com.coco.board.repository;


import com.coco.board.entity.Member;
import com.coco.board.repositroy.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i+"@test.com")
                    .password("1234")
                    .name("USER" + i)
                    .build();

            memberRepository.save(member);
        });
    }

}
