package com.jpabook.ch02.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("start");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabook");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            System.out.println("=========트랜잭션 시작========");
            tx.begin();
            logic();
            tx.commit();
        } catch (Exception e ) {
            tx.rollback();
        } finally {
            System.out.println("==========close()===========");
            entityManagerFactory.close();
        }
    }

    private static void logic() {
        System.out.println("비즈니스 로직 실행");
    }
}
