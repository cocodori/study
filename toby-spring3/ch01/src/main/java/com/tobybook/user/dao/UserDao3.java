package com.tobybook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tobybook.user.domain.User;
/*
 *  Connection 만드는 부분을 추상 메소드로 만들어서 
 *  하위 클래스에서 구현해서 사용할 수 있도록 하기
 *  (UserDao를 구현하는 각각의 클래스들이 다른 DB커넥션을 사용할 수 있도록 하기 위함)
 *  
 *  이렇게 슈퍼클래스에 기본적인 로직의 흐름(커넥션 생성, SQL생성, 실행, 반환)을 만들고,
 *  그 기능의 일부를 추상 메소드나 오버라이딩이 가능한 protected 메소드 등으로 만든 뒤,
 *  서브클래스에서 이런 메소드를 필요에 맞게 구현해서 사용하도록 하는 방법을 
 *  디자인 패턴에서 템플릿 메소드 패턴이라고 한다.(1장 오브젝트와 의존 관계 69p)
 * **/
public abstract class UserDao3 {
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

	
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
