package com.coco.mapper;

import com.coco.domain.MemberVO;

/*
 * 회원에 대한 정보는 MyBatis를 이용해서 처리하므로 해당 매퍼를 작성한다.
 * tbl_member와 tbl_member_auth테이블에 데이터를 추가/조회할 수 있도록 작성.
 * Member객체를 가져오는 경우, 한 번에 두 개의 테이블을 join해서 처리할 수 있는 방식으로,
 * MyBatis의 ResultMap 기능을 사용한다.
 * 
 * 하나의 MemberVO 인스턴스는 내부적으로 여러 개의 AuthVO를 가진다. (admin계정이 ROLE_MANAGE와 ROLE_ADMIN 권한을 가지는 것)
 * 이것을 1:N. 1대 N의 관계라고 한다. 하나의 데이터가 여러 하위 데이터를 포함하는 것을 말한다.
 * MyBatis의 ResultMap을 이용하면 하나의 쿼리로 MemberVO와 그 하위의 AuthVO의 리스트까지 처리할 수 있다.
 * */
public interface MemberMapper {
	public MemberVO read(String userid);
}
