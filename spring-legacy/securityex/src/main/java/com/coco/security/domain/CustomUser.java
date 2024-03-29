package com.coco.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.coco.domain.MemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;

	private MemberVO member;
	
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public CustomUser(MemberVO memberVO) {
		super(memberVO.getUserid(), memberVO.getUserpw(), 
				memberVO.getAuthList().stream()
					.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
					.collect(Collectors.toList()));
		this.member = memberVO;
	}
}
