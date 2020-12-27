package com.coco.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coco.domain.MemberVO;
import com.coco.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class CommonController {
	private final MemberService memberService;

	@GetMapping("/accessError")
	public void accessDenied(Authentication authentication, Model model) {
		log.info("Access Denied : " + authentication);
		
		model.addAttribute("message", authentication+"");
	}
	
	@GetMapping("/login")
	public void loginInput(String error, String logout, Model model) {
		log.info("error : " + error );
		log.info("logout : " + logout);
		
		if(error != null ) {
			model.addAttribute("error", "입력하신 정보가 틀렸습니다. 다시 입력하세요.");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "로그아웃 되었습니다.");
		}
	}
	
	@GetMapping("/logout")
	public void logoutGet() {
		log.info("LOGOUT !!!! ");
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("==============register===============");
	}
	
	@PostMapping("/register")
	public String registerPost(MemberVO memberVO) {
		log.info("====POST Register====");
		log.info("memberVO : " + memberVO);
		
		memberService.register(memberVO);
		
		return "redirect:/board/list";
	}
}
