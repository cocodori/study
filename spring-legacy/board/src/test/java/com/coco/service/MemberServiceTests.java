package com.coco.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.config.SecurityConfig;
import com.coco.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j
public class MemberServiceTests {
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void testService() {
		log.info(memberService);
	}
	
	@Test
	public void testRegisterService() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUserid("service");
		memberVO.setUsername("김서빕");
		memberVO.setUserpw("1234");
		
		int result = memberService.register(memberVO);
	}
}
