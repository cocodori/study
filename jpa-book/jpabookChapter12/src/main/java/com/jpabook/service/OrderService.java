package com.jpabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Delivery;
import com.jpabook.domain.Item;
import com.jpabook.domain.Member;
import com.jpabook.domain.Order;
import com.jpabook.domain.OrderItem;
import com.jpabook.domain.OrderSearch;
import com.jpabook.repository.MemberRepository;
import com.jpabook.repository.OrderRepository;

@Transactional
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired 
	ItemService itemService;
	
	//주문
	public Long order(long memberId, Long itemId, int count) {
		
		//엔티티 조회
		Member member = memberRepository.findById(memberId).get();
		Item item = itemService.findOne(itemId);
		
		//배송 정보 생성
		Delivery delivery = new Delivery(member.getAddress());
		
		//주문 상품 생성
		OrderItem orderItem =
				OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}
	
	public void cancelOrder(Long orderId) {
		//주문 엔티티 조회
		Order order = orderRepository.findById(orderId).get();
		
		//주문 취소
		order.cancel();
	}
	
	//주문 검색
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch.toSpecification());
	}
}
