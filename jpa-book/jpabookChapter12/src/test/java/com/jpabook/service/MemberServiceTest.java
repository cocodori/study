package com.jpabook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Member;
import com.jpabook.repository.MemberRepository;

@Transactional
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void 회원가입() {
		//given
		Member member = new Member();
		member.setName("Lee");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	/*
	 * 	@Test의 expected속성에 예외 클래스를 지정하면 테스트 결과로 지정한 예외가 발생해야 테스트가 성공한다.
	 * */
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() {
		//Given
		Member member1 = new Member();
		member1.setName("lee");
		
		Member member2 = new Member();
		member2.setName("lee");
		
		//When
		memberService.join(member1);
		memberService.join(member2);
		
		//Then
		fail("다른 예외 발생");
	}
}
