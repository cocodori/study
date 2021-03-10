package com.jpabook.ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveRelation {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();

    public void removeRelation() {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }
}
