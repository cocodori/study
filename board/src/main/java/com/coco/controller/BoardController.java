package com.coco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coco.domain.BoardAttachVO;
import com.coco.domain.BoardVO;
import com.coco.domain.PageDTO;
import com.coco.domain.PageInfo;
import com.coco.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	
	/*
	 * RestController가 아니므로 메서드에 직접 @ResponsBody를 적용하여 JSON데이터를 반환한다.
	 * 
	 * */
	@ResponseBody
	@GetMapping(value = "/getAttachList")
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
		log.info("-------------getAttachList()--------------");
		log.info("bno : " + bno);
		
		return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public void getAllPost(PageInfo page,Model model) {
		log.info("/board/list");
		log.info("page : " + page);
		
		Long total = boardService.getTotal(page);
		model.addAttribute("list", boardService.getAllPost(page));
		model.addAttribute("pageDTO", new PageDTO(page, total));
	}
	
	@PreAuthorize("isAuthenticated()") //로그인한 사용자만 접근 가능
	@GetMapping("/write")
	public void write() {
		log.info("/board/write");

	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO boardVO, RedirectAttributes redirect) {
		log.info("/board/register");
		log.info("register : " + boardVO);
		
		if(boardVO.getAttachList() != null ) {
			boardVO.getAttachList().forEach(attach -> log.info(attach));
		}

		redirect.addAttribute("no", boardService.register(boardVO));
		
		return "redirect:/board/post";
	}
	
	@GetMapping("/post")
	public void getPost(Long no, PageInfo PageInfo, Model model) {
		log.info("/board/post");
		
		model.addAttribute("post", boardService.getPost(no));
	}
	
	//수정하는 사람이 글쓴이와 같아야 한다.
	@PreAuthorize("principal.username == #boardVO.writer")
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, PageInfo pageInfo,RedirectAttributes redirect) {
		log.info("/board/modify");
		log.info("BoardVO : " + boardVO);
		
		int result = boardService.modify(boardVO);
		
		log.info("MODIFY RESULT : " + result);
		
		redirect.addAttribute("no",boardVO.getBno());
		
		log.info("pageInfo.getUrlList() : " + pageInfo.getUrlList());
		
		return "redirect:/board/post" + pageInfo.getUrlList();
	}
	
	//삭제하는 사람이 글쓴이와 같아야 한다.
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(Long bno, PageInfo pageInfo, String writer) {
		log.info("/board/remove");
		log.info("writer : " + writer);
		
		List<BoardAttachVO> attachList = boardService.getAttachList(bno);
		
		log.info(attachList);
		
		if(boardService.remove(bno) == 1) {
			deleteFiles(attachList);
		}
		
		return "redirect:/board/list" + pageInfo.getUrlList();
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		log.info("----------------deleteFiles()--------------------------");
		log.info("attachList : " + attachList);
		
		attachList.forEach(attach -> {
			//삭제할 파일 경로
			try {
				Path file = Paths.get("C:\\work\\springex\\uploadFolder\\"
			+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
				
				log.info("file : " + file);
				
				Files.deleteIfExists(file);
				
				//삭제하는 파일이 이미지 파일이라면
				if(Files.probeContentType(file).startsWith("image")) {
					//삭제하는 파일의 섬네일 경로
					Path thumbnail = Paths.get("C:\\work\\springex\\uploadFolder\\"
				+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					
					//섬네일 삭제
					Files.delete(thumbnail);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
		});	//forEach()
	}
}
