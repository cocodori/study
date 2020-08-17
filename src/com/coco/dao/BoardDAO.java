package com.coco.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coco.vo.BoardVO;

public class BoardDAO {
	private final static Logger log = Logger.getGlobal();
	DataSource ds;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:/comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} //BoardDAO()
	
	public BoardVO getPost(int no) {
		log.info("no : " + no);
    	String sql = "SELECT * FROM t_board"
    			+" WHERE bno > 0 AND bno = ?";
    	log.info(sql);
       	try(
        	Connection conn = ds.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	) {
    		pstmt.setInt(1, no);
    		ResultSet rs = pstmt.executeQuery();
    			
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
    		
    		return vo;

        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		return null;
	}
	
	public int insert(BoardVO vo) {
    	String sql = "INSERT INTO t_board(title, content, imgName, id)"
    			+" VALUES(?,?,?,?)";
		
    	log.info(sql);
    	
    	try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImgName());
			pstmt.setString(4, vo.getId());
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {e.printStackTrace();}
		return -1;
	}
	
	public List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<>();
		
		String sql = "SELECT CASE WHEN LEVEL-1 > 0 then CONCAT(CONCAT(REPEAT('    ', level  - 1),''), t.title)\r\n" + 
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
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareCall(sql);
			ResultSet rs = pstmt.executeQuery();
			) {
			
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

			} // while
			
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	} //getList()
}
