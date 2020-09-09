package com.coco.security;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
						,"file:src/main/webapp/WEB-INF/spring/security-context.xml"
						})
@Log4j
public class MemberTests {
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	//회원 등록
	@Test
	public void testInsertMember() {
		String sql = "INSERT INTO tbl_member(userid, userpw, username)"
				+ "values(?,?,?)";
		
		for (int i=0; i < 30; i++) {
		
			try (	Connection conn = dataSource.getConnection();
					PreparedStatement pstmt= conn.prepareStatement(sql);
					){
				//모든 비밀번호는 1234
				pstmt.setString(2, pwEncoder.encode("1234"));
				
				if(i < 10) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "사용자"+i);
				} else if(i < 20) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "매니저"+i);
				} else if(i<30) {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
				}

				int result = pstmt.executeUpdate();
				
				log.info("result : " + result);
				
				assertTrue(result == 1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//권한 부여
	@Test
	public void testInsertAuth() {
		
		String sql = "INSERT INTO tbl_member_auth(userid, auth)"
				+"VALUES(?,?)";
		for (int i=0; i < 30; i++) {
			
			try (	Connection conn = dataSource.getConnection();
					PreparedStatement pstmt= conn.prepareStatement(sql);
					){
				
				if(i < 10) { //유저 권한
					pstmt.setString(1, "user"+i);
					pstmt.setString(2, "ROLE_USER");
				} else if(i < 20) {	//매니저 권한
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2, "ROLE_MANAGER");
				} else if(i<30) {	//관리자 권한
					pstmt.setString(1, "admin"+i);
					pstmt.setString(2, "ROLE_ADMIN");
				}

				int result = pstmt.executeUpdate();
				
				log.info("result : " + result);
				
				assertTrue(result == 1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
