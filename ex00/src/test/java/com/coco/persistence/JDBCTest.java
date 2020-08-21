package com.coco.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class JDBCTest {
	
    private static final String URL= "jdbc:mysql://127.0.0.1:3306/springex?serverTimezone=Asia/Seoul";;
    private static final String USER= "springuser";;
    private static final String PW= "springuser";;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testConnection() {
		try(
			Connection con = DriverManager.getConnection(URL, USER , PW);
			){
			
			assertNotNull(con);
			log.info(con);
			
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

}
