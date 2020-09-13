package com.coco.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coco.domain.AuthVO;
import com.coco.domain.MemberVO;
import com.coco.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper memberMapper;
	private final PasswordEncoder pwEncoder;
	
	@Transactional
	@Override
	public int register(MemberVO memberVO) {
		AuthVO authVO = new AuthVO();
		String rawPassword = memberVO.getUserpw();
		String passwordEncoding = pwEncoder.encode(rawPassword);
		
		authVO.setUserid(memberVO.getUserid());
		//모든 권한 임의로 ADMIN 설정
		authVO.setAuth("ROLE_ADMIN");
		
		memberVO.setUserpw(passwordEncoding);
		memberMapper.register(memberVO);
		
		return memberMapper.addAuth(authVO);
	}

}
