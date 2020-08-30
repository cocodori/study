package com.coco.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyPageDTO;
import com.coco.domain.ReplyVO;
import com.coco.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@RestController
@Log4j
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;
	
	/* ResponseEntity타입
	 * REST방식으로 데이터를 주고 받는 경우, 요청하는 쪽에서는 지금 받은 데이터가 정상인지, 아닌지 알 수 없다.
	 * 따라서 보내는 쪽에서 전송할 때, 이것이 올바른 데이터인지 아닌지를 알 수 있는 방법을 제공해야 한다.
	 * ResponseEntity객체로 데이터와 함께 HTTP의 상태 코드를 함께 보낼 수 있다.
	 * 올바른 데이터가 아니라면 에러 메세지를, 올바른 데이터라면 정상적인 헤더 메세지를 전달한다.
	 * */
	
	/*
	 * @RequestBody - JSON데이터를 객체로 변환할 때 사용한다.
	 * 전달된 요청(Request)의 내용(Body)을 이용해서 파라미터의 타입으로 변환을 요구한다.
	 * 내부적으로 HttpMessageConverter를 이용한다.
	 * */
	
	//댓글 등록하기
	@PostMapping(value = "/new",
			consumes = "application/json"	//제이슨 데이터를 받겠다.
			//,produces = {MediaType.TEXT_PLAIN_VALUE} //반드시 지정할 필요는 없다.
			)
	public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) {
		log.info("ReplyVO : " + replyVO);
		
		int registerResult = replyService.register(replyVO);
		
		log.info("registerResult : " + registerResult);
		

		//제대로 등록 됐다면 성공했다는 텍스트와 함께 OK 헤더 메세지를,
		//실패했다면 에러가 발생했다는 헤더 메세지를 보낸다.
		ResponseEntity<String> result = registerResult == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return result;
	}
	
	/* @PathVariable은 쿼리스트링을 경로의 일부로 삼는다.
	 * {bno}이라고 지정하고, @PathVariable("bno")Long bno라고 선언 했을 때,
	 * {bno}의 값이 파라미터 bno에 대입된다.
	 * */
	
	//게시물의 댓글 조회하기
	@GetMapping(value = "/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> getList (
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			) {
		log.info("bno : " + bno);
		log.info("page : " + page);
		
		PageInfo pageInfo = new PageInfo(page, 10);
		log.info("pageInfo : " + pageInfo);
		
		ReplyPageDTO replyList = replyService.getReplyList(pageInfo, bno);
		
		return new ResponseEntity<>(replyList, HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno")Long rno) {
		log.info("rno : " + rno);
		
		return replyService.remove(rno) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/{rno}",
			consumes = "application/json")
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO replyVO,
			@PathVariable("rno") Long rno
			) {
		//어떤 댓글을 수정할 것인지 지정
		replyVO.setRno(rno);
		
		log.info("rno : " + rno);
		log.info("ReplyVO : " + replyVO);
		
		
		return replyService.modify(replyVO) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{rno}")
	public ResponseEntity<ReplyVO> get(@PathVariable("rno")Long rno) {
		log.info("rno : " + rno);
		return new ResponseEntity<>(replyService.getReply(rno), HttpStatus.OK);
	}
	
	
	
}
