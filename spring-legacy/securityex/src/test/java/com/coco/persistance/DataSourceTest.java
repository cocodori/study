package com.coco.persistance;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testExist() {
		log.info(dataSource);
		assertNotNull(dataSource);
	}
	
	@Test
	public void testGetTime() {
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select now()");
				ResultSet rs = pstmt.executeQuery();
				) {
			
			
			rs.next();
			String time = rs.getString(1);

			log.info(conn);
			log.info(time);
			
			assertNotNull(conn);
			assertNotNull(time);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
