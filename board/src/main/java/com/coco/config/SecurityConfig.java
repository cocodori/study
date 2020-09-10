package com.coco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * WebSecurityConfigurerAdapter클래스를 상속해서 필요한 기능을 오버라이딩해서 구현한다.
 * 선언부의 @EnableWebSecurity는 스프링MVC와 스프링 시큐리티를 결합하는 용도로 사용된다.
 * configure()메서드를 오버라이딩해서 security-context.xml에서 사용하던 <security:http>태그 관련 설정을 대신한다.
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * configure()는 파라미터가 여러 개이므로 주의해서 오버라이딩 한다. 
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/board/list").permitAll();
//			.antMatchers("/board/")
	}
	
	
	
}
