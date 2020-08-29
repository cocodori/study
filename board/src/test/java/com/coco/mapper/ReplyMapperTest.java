package com.coco.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.domain.PageInfo;
import com.coco.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class ReplyMapperTest {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void testGetReply() {
		ReplyVO replyVO = replyMapper.getReply(1L);
		assertNotNull(replyVO);
		log.info(replyVO);
	}
	
	@Test
	public void testReplyMapper() {
		assertNotNull(replyMapper);
		log.info(replyMapper);
	}
	
	@Test
	public void testInsert() {	//댓글 등록 테스트
		Long[] bnoArr = {514L, 513L, 511L, 509L, 508L};
		
		IntStream.rangeClosed(1, 9).forEach(i-> {
			ReplyVO replyVO = ReplyVO.builder()
					.bno(bnoArr[i%5])
					.reply("두 번째 테스트요"+i)
					.replyer("manager")
					.build();
			
			int result = replyMapper.insert(replyVO);
			
			assertNotNull(result);
			assertTrue(result == 1);
		});
	}
	
	@Test
	public void testGetReplyList() {	//댓글 목록 테스트
		List<ReplyVO> replyList = replyMapper.getReplyList(514L, new PageInfo());
		assertTrue(replyList.size() >= 0);
	}
	
	@Test
	public void testUpdate() {
		ReplyVO replyVO = ReplyVO.builder()
				.rno(14L)
				.reply("수정 테스트22")
				.build();
		
		int result = replyMapper.update(replyVO);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void testDelete() {
		int result = replyMapper.delete(5L);
		
		assertTrue(result == 1);
	}
}
