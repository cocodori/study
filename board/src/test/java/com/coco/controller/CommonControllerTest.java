package com.coco.controller;

import static org.junit.Assert.assertTrue;

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
import org.springframework.web.context.WebApplicationContext;

import com.coco.config.RootConfig;
import com.coco.config.SecurityConfig;
import com.coco.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
public class CommonControllerTest {

	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	//MockMvc객체 초기화
	@Before	//org.junit.Before
	public void setup() {	
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(webContext)
						.build();
	}
	
	//테스트 실패.
	//CSRF토큰 때문인가?
	@Test
	public void testMemberRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/register")
									.param("userid", "controllerTest")
									.param("userpw", "1234")
									.param("username", "자바킴")
									).andReturn()
									 .getModelAndView()
									 .getViewName();
	
		assertTrue(resultPage != null 
				|| !resultPage.equals("")
				|| resultPage.length() != 0);
	}
	
}
