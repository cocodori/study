package com.coco.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;
import com.coco.persistence.DataSourceTest;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class TimeMapperTest {

	@Autowired
	TimeMapper mapper;
	
	@Test
	public void getTime() {
		assertNotNull(mapper);
		log.info(mapper);
		log.info(mapper.getClass().getName());
		log.info(mapper.getTime());
	}
}
