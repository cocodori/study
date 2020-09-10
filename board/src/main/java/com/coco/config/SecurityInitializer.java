package com.coco.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 * AbstractSecurityWebApplicationInitializer클래스는 내부적으로 DelegatingFilterProxy를 스프링에 등록한다.
 * 이 작업은 별도의 구현 없이 클래스를 추가하는 것만으로 진행된다.
 * */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
}
