package com.tobybook.user.dao;

import com.tobybook.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao6 dao = context.getBean("userDao", UserDao6.class);

        User user = new User();
        user.setId("hoon2");
        user.setName("이지훈");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println("ID : " + user2.getId());
        System.out.println("Name : " + user2.getName());
        System.out.println("UserDao의 get()이 제대로 작동했다면, user와 user2가 같은 데이터를 가지고 있어야 한다.");
        System.out.println("user.getId()와 user2.getId()가 같은가");
        System.out.println("동등성 비교 equals : " + user.getId().equals(user2.getId()));
        System.out.println("user 1 : " + user);
        System.out.println("user 2 : " + user2);


    }
}
