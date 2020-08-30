package com.coco.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coco.mapper.Sample1Mapper;
import com.coco.mapper.Sample2Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class SampleTxServiceImpl implements SampleTxService {

	private final Sample1Mapper sample1Mapper;
	private final Sample2Mapper sample2Mapper;
	
	@Transactional
	@Override
	public void addData(String value) {
		log.info("Sample1Mapper");
		sample1Mapper.insertCol1(value);
		
		log.info("Sample2Mapper");
		sample2Mapper.insertCol2(value);
		
		log.info("end");
	}

}
