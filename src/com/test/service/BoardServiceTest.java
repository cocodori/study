package com.test.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Test;

import com.coco.dao.BoardDAO;

public class BoardServiceTest {
	
	DataSource ds;
	
	public BoardServiceTest () {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"jeus.jndi.JNSContextFactory");
		env.put(Context.PROVIDER_URL, "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul");
		env.put(Context.SECURITY_PRINCIPAL, "sevlet");
		env.put(Context.SECURITY_CREDENTIALS, "1234");
		System.getProperty(Context.PROVIDER_URL);
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	@Test
	public void getListTest() {
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
