package com.jpabook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "ORDER_ITEM")
@Entity
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		
		return orderItem;
	}

	public void cancel() {
		getItem().addStock(count); // 주문 취소한 만큼 재고 증가
	}

	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
	
}
