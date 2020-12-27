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
import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;
import com.coco.domain.PageDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardMapperTests {

	@Autowired
	BoardMapper boardMapper;
	
	
	@Test
	public void searchTest() {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setKeyword("coco");	//검색 키워드
		pageInfo.setType("twc");	//제목 검색
		
		List<BoardVO> allPostList = boardMapper.getAllPost(pageInfo);
		
		assertNotNull(allPostList);
		assertTrue(allPostList.size() == 10);
		
		allPostList.forEach(element -> log.info(allPostList));
		
		log.info(boardMapper.getTotal(pageInfo));
	}
	
//	@Test
//	public void pageDTOTest() {
//		Long total = boardMapper.getTotal();
//		
//		PageDTO dto = new PageDTO(new PageInfo(90, 10), total);
//		
//		assertNotNull(dto);
//
//		log.info(dto);
//		
//	}
	@Test
	public void getAllPostTest() {
		List<BoardVO> allPostList= boardMapper.getAllPost(new PageInfo(-2, 10));
		
		assertNotNull(allPostList);

		allPostList.forEach(element -> log.info(element));
	}
	
	@Test
	public void insertTest() {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			BoardVO boardVO = BoardVO.builder()
					.title("Mapper Insert Test2" +i)
					.content("Hello MyBatis Mapper" +i)
					.writer("user0"+i)
					.build();
			
			int result = boardMapper.insert(boardVO);
			
			log.info("result : " + result);
			
			assertTrue(result == 1);
			
			Long lastInsertId = boardMapper.lastInsertId();
			
			log.info("LAST INSERT ID : " + lastInsertId);
			
		});
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
