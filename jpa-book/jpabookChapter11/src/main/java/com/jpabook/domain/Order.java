package com.jpabook.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Order {
	
	@Id @GeneratedValue
	@Column(name="ORDER_ID")
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;
	
	private Date orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	//주문
	public static Order createOrder(Member member, Delivery delivery, OrderItem...orderItems) {
		Order order = new Order();
		
		order.setMember(member);
		order.setDelivery(delivery);
		
		for(OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(new Date());
		
		return order;
	}
	
	//주문 취소
	public void cancel() {
		if (delivery.getStatus() == DeliveryStatus.COMP) {
			throw new RuntimeException("이미 배송이 완료된 상품입니다.");
		}
		
		this.setStatus(OrderStatus.CANCLE);
		
		for(OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
	
	//전체 주문 가격 조회
	public int getTotalPrice() {
		int totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		
		return totalPrice;
	}
}
