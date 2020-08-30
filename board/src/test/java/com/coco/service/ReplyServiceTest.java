package com.coco.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.domain.PageInfo;
import com.coco.domain.ReplyPageDTO;
import com.coco.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class ReplyServiceTest {
	
	@Autowired
	ReplyService replyService;
	
	@Test
	public void testGetReply() {
		ReplyVO replyVO = replyService.getReply(1L);
		log.info(replyVO);
		assertNotNull(replyVO);
		assertTrue(replyVO instanceof ReplyVO);
	}
	
	@Test
	public void diTest() {
		assertNotNull(replyService);
		log.info(replyService);
	}
	
	@Test
	public void registerTest() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO replyVO = ReplyVO.builder()
					.bno(514L)
					.reply("replyservice register test")
					.replyer("tester")
					.build();
			
			int result = replyService.register(replyVO);
			
			assertTrue(result == 1);
		});
	}
	
	@Test
	public void getReplyListTest() {
		ReplyPageDTO replyList = replyService.getReplyList(new PageInfo(1, 10), 514L);
		
		assertNotNull(replyList);
	}
	
	@Test
	public void modifyTest() {
		ReplyVO replyVO = ReplyVO.builder()
				.rno(28L)
				.reply("service modify TEST")
				.build();
		
		replyService.modify(replyVO);
	}
	
	@Test
	public void removeTest() {
		int result = replyService.remove(24L);
		
		assertTrue(result == 1);
	}
	
}
