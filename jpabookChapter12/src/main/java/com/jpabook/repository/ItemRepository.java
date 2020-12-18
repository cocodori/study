package com.jpabook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpabook.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
