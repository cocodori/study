package com.coco.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.coco.domain.MemberVO;
import com.coco.mapper.MemberMapper;
import com.coco.security.domain.CustomUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("Load User By User Name : " + username);
		
		//username은 userid를 의미한다.
		MemberVO memberVO = memberMapper.read(username);
		
		return memberVO == null ? null : new CustomUser(memberVO);
	}
}
