package com.jpabook.ch05;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JpaTest {
    EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("jpabook");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    public void test() {
        SaveTest();
        List<Member> members = queryLogicJoin();

        assertEquals("회원1", members.get(0).getUsername());
    }

    private List<Member> queryLogicJoin() {
        String jpql = "select m from Member m join m.team t" +
                "  where t.name =: teamName";

        return entityManager.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
    }

    private void SaveTest() {
        Team team1 = new Team("team1", "팀1");
        entityManager.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        entityManager.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        entityManager.persist(member2);
    }
}
