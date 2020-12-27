package com.coco.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class MarketTest {
	
	@Autowired
	private Market market;
	
	@Test
	public void testExist() {
		
		assertNotNull(market);
		log.info(market);
		log.info(market.getMeat());
		log.info(market.getVegetable());
		log.info(market.getSnack());

	}
}
