package com.coco.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

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
}
