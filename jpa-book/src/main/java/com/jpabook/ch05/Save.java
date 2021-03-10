package com.jpabook.ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Save {
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();

    public void save() {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }
}
