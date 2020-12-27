package com.jpabook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jpabook.domain.Order;

public interface OrderRepository 
	extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
