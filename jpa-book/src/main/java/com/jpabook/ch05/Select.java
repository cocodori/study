package com.jpabook.ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Select {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();

    public void select() {
        String jpql = "select m from Ch06Member m join m.team t where t.name = teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username ? " + member.getUsername());
        }
    }

    //일대다 컬렉션 조회
    public void biDirection(){
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers(); //팀 -> 회원 객체 그래프 탐색

        for (Member member : members) {
            System.out.println(member.getUsername());
        }
    }
}
