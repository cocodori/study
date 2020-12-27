package com.jpabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Member;
import com.jpabook.repository.MemberRepository;

@Transactional
@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	//회원가입
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	//전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

}
