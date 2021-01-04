package com.tobybook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletoneTest {
    public static void main(String[] args) {
        DaoFactory factory = new DaoFactory();
        UserDao6 factoryDao1 = factory.userDao();
        UserDao6 factoryDao2 = factory.userDao();

        System.out.println("factoryDao1 : " + factoryDao1);
        System.out.println("factoryDao2 : " + factoryDao2);
        System.out.println("factoryDao1 == factoryDao2 ? " + (factoryDao1 == factoryDao2));

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao6 contextDao1 = context.getBean("userDao", UserDao6.class);
        UserDao6 contextDao2 = context.getBean("userDao", UserDao6.class);

        System.out.println("contextDao1 : " + contextDao1);
        System.out.println("contextDao2 : " + contextDao2);
        System.out.println("contextDao1 == contextDao2 ? " + (contextDao1 == contextDao2));


    }
}
