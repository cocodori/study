package com.jpabook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Address;
import com.jpabook.domain.Book;
import com.jpabook.domain.Item;
import com.jpabook.domain.Member;
import com.jpabook.domain.Order;
import com.jpabook.domain.OrderStatus;
import com.jpabook.exception.NotEnoughStockException;
import com.jpabook.repository.OrderRepository;

@Transactional
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {
	
	@PersistenceContext
	EntityManager entityManager;

	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() {
		//Given
		Member member = createMember();
		Item item = createBook("시골 즈파", 10000, 10);
		
		int orderCount = 11; //재고보다 많이
		
		//When 
		orderService.order(member.getId(), item.getId(), orderCount);
		
		//Then
		fail("예외가 발생하지 않으면 test fail");
	}
	
	@Test
	public void 상품주문() {
		//Given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
		int orderCount = 2;
		
		//When
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
		
		//Then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 X 수량이다.", 10000*2, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
		
	}

	private Item createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		
		book.setName(name);
		book.setStockQuantity(stockQuantity);
		book.setPrice(price);
		
		entityManager.persist(book);
		
		return book;

	}

	private Member createMember() {
		Member member = new Member();
		
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "111-111"));
		entityManager.persist(member);	
		
		return member;
	}
}
