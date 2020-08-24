package com.coco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coco.domain.BoardVO;
import com.coco.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void getAllPost(Model model) {
		
		log.info("/board/list");
		
		model.addAttribute("list", boardService.getAllPost());
	}
	
	@GetMapping("/write")
	public void write() {
		log.info("/board/write");
	}
	
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes redirect) {
		log.info("/board/register");
		log.info("boardVO : "  + boardVO);

		redirect.addAttribute("no", boardService.register(boardVO));
		
		return "redirect:/board/post";
	}
	
	@GetMapping("/post")
	public void getPost(Long no, Model model) {
		log.info("/board/post");
		
		model.addAttribute("post", boardService.getPost(no));
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, RedirectAttributes redirect) {
		log.info("/board/modify");
		log.info("BoardVO : " + boardVO);
		
		int result = boardService.modify(boardVO);
		
		log.info("MODIFY RESULT : " + result);
		
		redirect.addAttribute("no",boardVO.getBno());
		
		return "redirect:/board/post";
	}

	@PostMapping("/remove")
	public String remove(Long bno) {
		log.info("/board/remove");
		log.info("bno : " + bno);
		
		int result = boardService.remove(bno);
		log.info("result : " + result);
		
		return "redirect:/board/list";
	}
}
