package com.test.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.coco.vo.BoardVO;

public class BoardDAOTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";
    private static final String USER = "servlet";
    private static final String PW = "1234";
    
	@Test
	public void getListTest() throws ClassNotFoundException {
		
		List<BoardVO> list = new ArrayList<>();
		
		Class.forName(DRIVER);
		
		String sql = "SELECT CASE WHEN LEVEL-1 > 0 then CONCAT(CONCAT(REPEAT('    ', level  - 1),'ㄴ'), t.title)\r\n" + 
				"                 ELSE t.title\r\n" + 
				"           END AS title\r\n" + 
				"     , t.bno\r\n" + 
				"     , t.p_bno\r\n" + 
				"     ,t.content\r\n" + 
				"     ,t.id\r\n" + 
				"     ,regdate\r\n" + 
				"     , fnc.level\r\n" + 
				"  FROM\r\n" + 
				"     (SELECT fnc_hierarchy() AS id, @level AS level\r\n" + 
				"        FROM (SELECT @start_with:=0, @id:=@start_with, @level:=0) vars\r\n" + 
				"          JOIN t_board\r\n" + 
				"         WHERE @id IS NOT NULL) fnc\r\n" + 
				"  JOIN t_board t ON fnc.id = t.bno";
		
		System.out.println(sql);
		
		try(	//try with resources
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareCall(sql);
			ResultSet rs = pstmt.executeQuery();
			) {
			
			assertNotNull(conn);
			
			while(rs.next()) {
				int level = rs.getInt("level");
				int bno = rs.getInt("bno");
				int p_bno = rs.getInt("p_bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date regdate = rs.getDate("regdate");

				BoardVO vo = new BoardVO(bno, p_bno, level, title, content, id, regdate);
				list.add(vo);

				assertNotNull(vo);
				System.out.println(vo);
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
