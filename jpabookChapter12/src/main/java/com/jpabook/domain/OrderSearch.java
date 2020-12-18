package com.jpabook.domain;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.data.jpa.domain.Specification.where;
import static com.jpabook.domain.OrderSpec.memberNameLike;
import static com.jpabook.domain.OrderSpec.orderStatusEq;

@Getter @Setter 
public class OrderSearch {

	private String memberName;	//회원명
	private OrderStatus orderStatus;	//주문 상태
	
	public Specification<Order> toSpecification() {
		return where(memberNameLike(memberName))
				.and(orderStatusEq(orderStatus));
	}
		
}
