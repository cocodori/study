package com.coco.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

/*
 * 접근 제한이 된 경우, 다양한 처리를 하고 싶을 때 직접 AccessDeniedHandler인터페이스를 구현하는 방식을 채택한다.
 * */
@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	/*
	 * AccessDeniedHandler의 유일한 추상메서드인 handle()은 HttpServletResponse를 파라미터로 선언해두었기
	 * 때문에 서블릿 API를 이용해서 처리할 수 있다.
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.error("ACCESS DENIED HANDLER!!!");
		log.error("Redirect......");
		response.sendRedirect("/accessError");
	}

	
}
