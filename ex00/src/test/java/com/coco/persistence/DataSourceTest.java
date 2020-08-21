package com.coco.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coco.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class DataSourceTest {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
			Connection conn = session.getConnection();
			) {
			
			assertNotNull(session);
			assertNotNull(conn);
			log.info(session);
			log.info(conn);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConnection() {
		
		try(
			Connection con = ds.getConnection();
			) {
			assertNotNull(con);
			log.info(con);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	} //testConnection()
	
}
