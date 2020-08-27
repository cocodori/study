package com.coco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coco.domain.SampleVO;
import com.coco.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	@GetMapping(value = "/getText", produces = "text/plain;charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "필요한 데이터만 전송";
	}
	
	@GetMapping(value = "/getSample")
	public SampleVO getSample() {
		
		return new SampleVO(123, "리", "코코");
	}
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"FIRST", i + "LAST"))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/getMap")
	public Map<String , SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap();
		
		map.put("FIRST MEMBER", new SampleVO(1, "페페", "포포"));
		map.put("SECOND MEMBER", new SampleVO(2, "프프", "생코"));
		map.put("THIRD MEMBER", new SampleVO(3, "콩콩", "김코"));
		
		return map;
	}
	
	/* ResponseEntity타입
	 * REST방식으로 데이터를 주고 받는 경우, 요청하는 쪽에서는 지금 받은 데이터가 정상인지, 아닌지 알 수 없다.
	 * 따라서 보내는 쪽에서 전송할 때, 이것이 올바른 데이터인지 아닌지를 알 수 있는 방법을 제공해야 한다.
	 * ResponseEntity객체로 데이터와 함께 HTTP의 상태 코드를 함께 보낼 수 있다.
	 * 올바른 데이터가 아니라면 에러 메세지를, 올바른 데이터라면 정상적인 헤더 메세지를 전달한다.
	 * */
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO sampleVO = new SampleVO(0, "" + height, "" + weight);
		
		return height < 150 ?
				ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(sampleVO)
				: ResponseEntity.status(HttpStatus.OK).body(sampleVO);
	}
	
	/* @PathVariable은 쿼리스트링을 경로의 일부로 삼는다.
	 * {cat}이라고 지정하고, @PathVariable("cat")String cat이라고 정의 했을 때,
	 * {cat}의 값이 String cat에 대입된다.
	 * */
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat
			,@PathVariable("pid") Integer pid
			) {
		return new String[] {"category : " + cat, "productID : " + pid};
	}
	
	/*
	 * @RequestBody - JSON데이터를 객체로 변환할 때 사용한다.
	 * 전달된 요청(Request)의 내용(Body)을 이용해서 파라미터의 타입으로 변환을 요구한다.
	 * 내부적으로 HttpMessageConverter를 이용한다.
	 * */
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert ticket :" + ticket );
		
		return ticket;
	}
}
