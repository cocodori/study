package com.coco.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.config.SecurityConfig;
import com.coco.domain.AuthVO;
import com.coco.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j
public class MemberMapperTests {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Test
	public void testaddAuth() {
		AuthVO authVO = new AuthVO();
		
		authVO.setUserid("cocolog");
		authVO.setAuth("ROLE_ADMIN");
		
		int result = memberMapper.addAuth(authVO);
		
		assertTrue(1==result);
	}
	
	@Test
	public void testMapper() {
		assertNotNull(memberMapper);
		log.info(memberMapper);
	}
	
	@Test
	public void testRead() {
		MemberVO memberVO = memberMapper.read("admin20");
		assertNotNull(memberVO);
		log.info(memberVO);
		memberVO.getAuthList().forEach(authVO -> log.info(authVO));
	}
	
	@Test
	public void testRegister() {
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("cocolog");
		memberVO.setUsername("코로그");
		memberVO.setUserpw(pwEncoder.encode("1234"));
		
		log.info(memberVO);
		int result = memberMapper.register(memberVO);
		log.info("---------------------------");
		log.info("result : " + result);
		log.info("---------------------------");
		
		assertTrue(1==result);

	}
}
