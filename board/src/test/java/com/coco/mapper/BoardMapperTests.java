package com.coco.mapper;

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

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardMapperTests {

	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void getAllPostTest() {
		List<BoardVO> allPostList= boardMapper.getAllPost();
		
		assertNotNull(allPostList);

		allPostList.forEach(element -> log.info(element));
	}
	
	@Test
	public void insertTest() {
		BoardVO boardVO = BoardVO.builder()
				.title("Mapper Insert Test")
				.content("Hello MyBatis Mapper")
				.writer("user0")
				.build();
		
		int result = boardMapper.insert(boardVO);
		
		log.info("result : " + result);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void getPostTest() {
		BoardVO boardVO = boardMapper.getPost(4L);
		
		assertNotNull(boardVO);
		log.info(boardVO);
	}
	
	@Test
	public void updateTest() {
		BoardVO boardVO = boardMapper.getPost(1L);
		boardVO.setTitle("1등입니다.");
		boardVO.setContent("하이룽");
		
		
		int result = boardMapper.update(boardVO);
		
		log.info(result);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void deleteTest() {
		BoardVO boardVO= boardMapper.getPost(4L);
		
		int result = boardMapper.delete(boardVO.getBno());
		assertTrue(result == 1);
	}
}
