package com.tobybook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao6 dao = context.getBean("userDao", UserDao6.class);

        dao.get("hoon");
        dao.get("hoon2");
        dao.get("hoon3");

        CountingConnectionMaker ccm =
                context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection count : " + ccm.getCounter());

    }
}
