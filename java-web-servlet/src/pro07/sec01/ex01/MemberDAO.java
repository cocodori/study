package pro07.sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	/* JDBC 설정
	 * private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //DRIVER
	 * NAME private static final String URL =
	 * "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul"; //JDBC
	 * URL/스키마 private static final String USER = "servlet"; //DB ID private static
	 * final String PWD = "1234"; //DB PW
	 */	
	
	private Connection con;
	private PreparedStatement pstmt;
	//DataSource설정
	private DataSource ds;
	
	public MemberDAO() {
		try {
			//JNDI에 접근하기 위해 기본 경로를 지정
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//context.xml에 설정한 name값을 이용해 톰캣이 미리 연결한 DataSource를 받아온다.
			ds = (DataSource) envContext.lookup("jdbc/mysql");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //생성자
	
	List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
//			connectDB(); //JDBC설정
			
			//DataSource를 이용해 데이터베이스에 연결한다.
			con = ds.getConnection();
			String sql = "select * from t_member";
			System.out.println("Query : " + sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * select문을 날려서 받아올 칼럼들.
				 * getString(String columnLabel)으로 받아온다.
				 * */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				/*
				 * 받아온 데이터를
				 * MemberVO객체에 담는다.
				 * */
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}
			//연결했던 반대순서로 닫는다.
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	} //listMembers()

	
	/* JDBC설정
	 * private void connectDB() { try { Class.forName("com.mysql.cj.jdbc.Driver");
	 * System.out.println("DRIVER LOADING....."); 
	 * con = DriverManager.getConnection(URL, USER, PWD);
	 * System.out.println("Connection 생성");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
}
