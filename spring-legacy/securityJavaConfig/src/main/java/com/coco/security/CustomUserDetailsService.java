package com.coco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.coco.domain.MemberVO;
import com.coco.mapper.MemberMapper;
import com.coco.security.domain.CustomUser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 * @RequireArgsConstructor를 사용하면 SecurityConfig에서 
 * 이 클래스에 기본 생성자가 없다는 에러가 발생한다.
 * @NoArgs...를 같이 선언해주면 'final'키워드 때문에 빨간 줄이...
 * */
@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("Load User By User Name : " + username);
		
		//username은 userid를 의미한다.
		MemberVO memberVO = memberMapper.read(username);
		
		return memberVO == null ? null : new CustomUser(memberVO);
	}
}
