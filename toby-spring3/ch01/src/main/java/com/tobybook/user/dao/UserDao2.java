package com.tobybook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tobybook.user.domain.User;

/*
 *  중복 메서드 분리하기
 * **/
public class UserDao2 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao2 dao = new UserDao2();
		
		User user = new User();
		user.setId("hoon");
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
		System.out.println("동일성 비교 == : " + user.getId() == user2.getId());
		
		
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}

	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/springstudy?serverTimezone=UTC", "bookex", "1234");
	}
}
