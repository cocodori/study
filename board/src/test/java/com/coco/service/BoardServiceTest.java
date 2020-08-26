package com.coco.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardServiceTest {

	@Autowired
	BoardService boardService;
	
	@Test
	public void serviceIsExistTest() {
		assertNotNull(boardService);
		log.info(boardService);
	}
	
	@Test
	public void registerTest() {
		BoardVO boardVO = BoardVO.builder()
				.title("BUSINESS LAYER REGISTER TEST")
				.content("HELLO, BUSINESS?")
				.writer("angryBird")
				.build();
		
		//성공하면 last insert id를 반환
		//실패하면 0을 반환
		Long result = boardService.register(boardVO);
		
		assertTrue(result>0);
		log.info(result);
	}
	
	@Test
	public void getAllPostTest() {
		List<BoardVO> allPostList = boardService.getAllPost(new PageInfo(3, 10));
		
		assertNotNull(allPostList);
		
		allPostList.forEach(element -> log.info(element));
	}
	
	@Test
	public void getPostTest() {
		BoardVO boardVO = boardService.getPost(15L);
		assertNotNull(boardVO);
		
		log.info(boardVO);
	}
	
	@Test
	public void modifyTest() {
		BoardVO boardVO = boardService.getPost(15L);
		
		boardVO.setTitle("BUSINESS LAYER MODIFY TEST");
		boardVO.setContent("이럴 거면 왜 같이 온 거야?");
		boardVO.setWriter("impo");
		
		int result = boardService.modify(boardVO);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void removeTest() {
		
		int result = boardService.remove(217L);
		assertTrue(result == 1);
	}
}
