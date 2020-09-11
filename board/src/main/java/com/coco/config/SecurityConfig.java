package com.coco.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.coco.security.CustomLoginSuccessHandler;
import com.coco.security.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 * WebSecurityConfigurerAdapter클래스를 상속해서 필요한 기능을 오버라이딩해서 구현한다.
 * 선언부의 @EnableWebSecurity는 스프링MVC와 스프링 시큐리티를 결합하는 용도로 사용된다.
 * configure()메서드를 오버라이딩해서 security-context.xml에서 사용하던 <security:http>태그 관련 설정을 대신한다.
 * */

@Configuration
@EnableWebSecurity
@Log4j
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;
	
	/*
	 * configure()는 파라미터가 여러 개이므로 주의해서 오버라이딩 한다. 
	 * 	아래의 시큐리티xml 설정을 대신하는 메서드라고 보면 된다.
		<security:http>
		<!-- 접근 제한 -->
		<security:intercept-url pattern="/sample/all" access="permitAll"/>
		<security:intercept-url pattern="/sample/member" access="hasRole('ROLE_MANAGER')"/>
		<security:intercept-url pattern="/sample/admin" access="hasRole('ROLE_ADMIN')"/>
		<security:form-login login-page="/login"
			authentication-success-handler-ref="customLoginSuccess"/>
		</security:http>

	 * 
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/sample/all").permitAll()
//			.antMatchers("/sample/member").access("hasRole('ROLE_MANAGER')")
//			.antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')");
		
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.successHandler(loginSuccessHandler());
		
		http.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me", "JSESSION_ID");
		
		//자동 로그인 설정
		http.rememberMe()
			.key("coco")
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
		
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
			.passwordEncoder(passwordEncoder());
	}
	
	//로그인 성공 시 처리
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	//패스워드 인코딩
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}	
	
	//자동 로그인
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
		repository.setDataSource(dataSource);
		return repository;
	}
}
