package com.coco.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.coco.config.RootConfig;
import com.coco.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@Log4j
//ServletContext의 WebApplicationContext를 이용하기 위해서 선언
@WebAppConfiguration
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	//테스트를 실행하기 전에 매번 실행한다.
	@Before
	public void setup() {	//MockMvc객체를 초기화 하는 메서드
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
				.build();
	}

	
	@Test
	public void getAllPostTest() {
		ModelMap result;
		
		try {
			 result = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap();
			
			assertNotNull(result);
			log.info(result);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void registerTest() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "mockMvcTest")
					.param("content", "volvoKO")
					.param("writer", "Elec")
					).andReturn().getModelAndView().getViewName();
			
			assertTrue(resultPage.length()!=0||!resultPage.equals("")||resultPage!=null);
			log.info("resultPage : " + resultPage);
			
		} catch (Exception e) {
			fail(e.getMessage());
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void getPostTest() {
		try {
			ModelMap result = mockMvc.perform(MockMvcRequestBuilders
					.get("/board/post")
					.param("no", "1"))
			.andReturn()
			.getModelAndView()
			.getModelMap();
			
			assertNotNull(result);
			log.info(result);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testModify() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
					.param("bno", "1")
					.param("title", "MockMVC MODIFYING")
					.param("content", "HELLO, MOCK MVC?"))
			.andReturn()
			.getModelAndView()
			.getViewName();
			
			assertTrue(resultPage != null 
						|| !resultPage.equals("")
						|| resultPage.length() != 0);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRemove() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
					.param("bno", "253"))
			.andReturn()
			.getModelAndView()
			.getViewName();
			
			assertNotNull(resultPage);
			
			log.info("resultPage : " + resultPage);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
}
