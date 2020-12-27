package com.coco.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	@Autowired
	MemberMapper memberMapper;
	
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
}
