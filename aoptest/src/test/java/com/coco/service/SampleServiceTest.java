package com.coco.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class SampleServiceTest {
 
	@Autowired
	private SampleService sampleService;
	
	@Test
	public void testClass() {
		assertTrue(sampleService instanceof SampleService);
		log.info(sampleService);
	}
	
	/*
	 * @Before를 이용한 AOP 로그 테스트
	 * */
	@Test
	public void testAdd() throws Exception {
		int result = sampleService.doAdd("123", "456");
		
		assertNotNull(result);
		
		log.info(result);
	}
	
	/*
	 * @AfterThrowing을 이용한 aop 예외 처리
	 * */
	@Test
	public void testException() throws Exception {
		log.info(sampleService.doAdd("123", "abc"));
	}
	
}	
