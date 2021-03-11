package com.jpabook.ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Save {
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();

    //단방향 저장
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

    //양방향 저장 예제. 단방향 관계 엔티티 저장과 다른 점이 없다.
    public void biDirectionEntitySave() {
        Team team = new Team("team1", "팀1");
        em.persist(team);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team);
        em.persist(member2);
    }

    //연관관계의 주인에는 엔티티를 저장하지 않는 잘못된 저장 방식
    public void testSaveNonOwner() {
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        //연관관계의 주인에는 Team을 저장하지 않고 주인이 아닌 쪽에 저장하면 아무 소용 없다
        Team team1 = new Team("team1", "팀1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);
    }

    //순수 객체 관점에서 봤을 때는 연관관계의 주인인 쪽과 주인이 아닌 쪽 모두 저장해야 한다.
    public void test순수한_객체(){
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1","회원1");
        Member member2 = new Member("member2","회원2");

        //주인 쪽에 주인이 아닌 쪽 저장
        member1.setTeam(team1);
        member2.setTeam(team1);

        //주인이 아닌 쪽에 주인인 쪽 저장
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        List<Member> members = team1.getMembers();
        System.out.println("members size : " + members.size());
    }

    //양방향 관계 최종 저장
    public void orm_양방향_저장() {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("m1", "m1");
        member1.setTeam(team1);
        em.persist(member1);
    }
}
