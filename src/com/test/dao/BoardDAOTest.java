package com.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.coco.vo.BoardVO;

public class BoardDAOTest {
	private final static Logger log = Logger.getGlobal();
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";
    private static final String USER = "servlet";
    private static final String PW = "1234";
    
    @Test
    public void updateTest() throws ClassNotFoundException {
    	Class.forName(DRIVER);
    	String sql = "UPDATE t_board " + 
    			" set title = ?, content= ?" + 
    			" where bno = ?";
    	log.info(sql);
    	
    	try(
    		Connection conn = DriverManager.getConnection(URL,USER,PW);
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		) {
    		
    		pstmt.setString(1, "update");
    		pstmt.setString(2, "con...ten...t...");
    		pstmt.setInt(3, 1);
    		
    		//성공하면 1
    		int result = pstmt.executeUpdate();
    		
    		assertTrue(result==1);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void getPostTest() throws ClassNotFoundException {
    	Class.forName(DRIVER);
    	String sql = "SELECT * FROM t_board"
    			+" WHERE bno > 0 AND bno = ?";
    	log.info(sql);
    	
    	try(
    		Connection conn = DriverManager.getConnection(URL,USER,PW);
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		) {
			pstmt.setInt(1, 7);
			ResultSet rs = pstmt.executeQuery();
    		
			assertNotNull(rs);
			
			rs.next();

			BoardVO vo = new BoardVO();
			
			vo.setBno(rs.getInt("bno"));
			vo.setP_bno(rs.getInt("p_bno"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setImgName(rs.getString("imgName"));
			vo.setId(rs.getString("id"));
			vo.setRegdate(rs.getDate("regdate"));

			log.info("vo : " + vo);

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void insertTest() throws ClassNotFoundException{
    	Class.forName(DRIVER);
    	
    	String sql = "INSERT INTO t_board(title, content, imgName, id)"
    			+" VALUES(?,?,?,?)";
    	log.info(sql);
    	
    	try(
    		Connection conn = DriverManager.getConnection(URL,USER,PW);
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		) {
    		
    		log.info(conn.toString());
    		log.info(pstmt.toString());
    		
    		for (int i=1; i<=10; i++) {
    			pstmt.setString(1, "저도 도배 " + i);
    			pstmt.setString(2, "hello world " + i);
    			pstmt.setString(3, null);
    			pstmt.setString(4, "coco");
    			
    			//성공하면 1을 반환한다.
        		int result = pstmt.executeUpdate();
        		
        		//result가 1이 아니라면 테스트 실패
        		assertTrue(result == 1);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
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
		
		log.info(sql);
		
		try(	//try with resources
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareCall(sql);
			ResultSet rs = pstmt.executeQuery();
			) {
			
			log.info("conn : " + conn);
			log.info("pstmt : " + pstmt);
			
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
				log.info("vo : " + vo);
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
