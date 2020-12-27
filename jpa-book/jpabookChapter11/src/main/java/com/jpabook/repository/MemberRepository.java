package com.jpabook.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jpabook.domain.Member;
/*
 *  @Repository 어노테이션이 붙어 있으면 context.xml의 <context:component-scan>에 의해 스프링 빈으로 자동 등록된다.
 *  또한 JPA전용 예외가 발생하면 스프링이 추상화한 예외로 변환해준다. 따라서 서비스 계층은 따로 JPA에 대한 예외처리를 하지 않아도 된다.
 * **/
@Repository
public class MemberRepository {

	/*
	 * 순수 자바 환경에서는 엔티티 매니저 팩토리를 만들고, 거기서 엔티티 매니저를 생성했다.
	 * 스프링이나 J2EE 컨테이너를 사용하면 컨테이너가 엔티티매니저를 관리하고 제공한다.
	 * 따라서 엔티티 매니저 팩토리에서 엔티티매니저를 직접 생성하지 않아도 된다.
	 * 그것에 대한 설정이 @PersistenceContext다.
	 * */
	@PersistenceContext
	EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	
	
}
