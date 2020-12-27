package com.test.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Test;

public class JDBCTest {
	private final static Logger log = Logger.getGlobal();
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";
    private static final String USER = "servlet";
    private static final String PW = "1234";
	
	@Test
	public void connectionTest() throws ClassNotFoundException {
		
		Class.forName(DRIVER);
		
		String sql = "select now() as time";
		try(
				Connection conn = DriverManager.getConnection(URL,USER,PW);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
			) {
			
			assertNotNull(conn);
			log.info("conn : " + conn);
			log.info("pstmt : " + pstmt);
			
			rs.next();
			log.info(rs.getString("time"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // connection()
}
