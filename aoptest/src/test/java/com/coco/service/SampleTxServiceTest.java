package com.coco.service;

import static org.junit.Assert.assertNotNull;

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
public class SampleTxServiceTest {

	@Autowired
	SampleTxService sampleTxService;
	
	@Test
	public void testServiceExist() {
		assertNotNull(sampleTxService);
		log.info(sampleTxService);
	}
	
	@Test
	public void testLong() {
		String str = "Transaction";
		log.info(str);
		
		sampleTxService.addData(str);
	}
}
