package com.jpabook.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jpabook.domain.Item;

@Repository
public class ItemRepository {
	
	@PersistenceContext
	EntityManager em;
	
	/*
	 *  저장과 수정(병합)을 같은 메서드에서 처리한다.
	 *  식별자(id)값이 없다면 새로운 엔티티라고 판단하고 persist()로 영속화한다.
	 *  식별자 값이 있으면 한 번 영속화 되었던 엔티티롤 판단하고 merge()로 수정(병합)한다.
	 *  이렇게 함을로써 클라이언트는 저장과 수정을 구분할 필요가 없다.
	 * */
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	} // save
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}

}
