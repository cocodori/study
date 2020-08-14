package com.coco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coco.vo.MemberVO;

public class MemberDAO {
	private DataSource ds;
	
	public MemberDAO() {
		try {
			//JNDI에 접근하기 위한 객체. 기본 경로를 지정
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//context.xml에 설정한 name값을 이용해 톰캣이 미리 연결한 DataSource를 받아온다.
			ds = (DataSource) envContext.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} // MemberDAO()
	
	public List<MemberVO> memberList() {
		List<MemberVO> memberList  = new ArrayList<>();
		final String SQL = "select * from t_member order by regdate desc";
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				ResultSet rs = pstmt.executeQuery()				
				) {
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				MemberVO vo = new MemberVO(id, pwd, name, email, regdate);
				memberList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}

	public int addMember(MemberVO vo) {
		if(vo == null) return -1;
		System.out.println(vo);

		String sql = "insert into t_member(id, pwd, name, email)"
				+" values(?,?,?,?)";
		
		String id = vo.getId();
		
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return -1;
		
	}
}
