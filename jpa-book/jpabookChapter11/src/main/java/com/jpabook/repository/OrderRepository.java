package com.jpabook.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jpabook.domain.Member;
import com.jpabook.domain.Order;
import com.jpabook.domain.OrderSearch;

@Repository
public class OrderRepository {
	
	@PersistenceContext
	EntityManager entityManger;
	
	public void save(Order order) {
		entityManger.persist(order);
	}
	
	public Order findOne(Long id) {
		return entityManger.find(Order.class, id);
	}
	
	public List<Order> findAll(OrderSearch orderSearch) {
		
		CriteriaBuilder criteriaBuilder = entityManger.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery =  criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		
		List<Predicate> criteria = new ArrayList<>();
		
		//주문 상태 검색
		if (orderSearch.getOrderStatus() != null) {
			Predicate status = 
					criteriaBuilder.equal(root.get("status"), orderSearch.getOrderStatus());
			
			criteria.add(status);
		}
		
		//회원 이름 검색
		if(StringUtils.hasText(orderSearch.getMemberName())) {
			//회원과 조인
			Join<Order, Member> join = root.join("member", JoinType.INNER);
			Predicate name = criteriaBuilder.like(join.<String>get("name"), "%"+orderSearch.getMemberName()+"%");
			
			criteria.add(name);
		}
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
		
		TypedQuery<Order> query = entityManger.createQuery(criteriaQuery)
												.setMaxResults(1000); // 최대 1000건
		
		return query.getResultList();
	}
}
