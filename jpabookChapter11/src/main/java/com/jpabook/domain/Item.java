package com.jpabook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.jpabook.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Item {
	
	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity; //잔여 재고
		
		if (restStock < 0) {
			throw new NotEnoughStockException("재고가 필요합니다.");
		}
		
		this.stockQuantity = restStock;
	}
}
