package pro09.sec05;

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
	private Connection con;
	private PreparedStatement pstmt;
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
			con = ds.getConnection();
			String sql = "select * from t_member";
			System.out.println("Query : " + sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} //listMembers()

	public void addMember(MemberVO vo) {
		try {
			con = ds.getConnection();
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			
			String sql = "insert into t_member(id, pwd, name, email)" +
			"values(?,?,?,?)"; //?,?,?,? -> 1,2,3,4
			
			System.out.println("SQL : " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			//쿼리 전송
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delMember(String id) {
		try {
			con = ds.getConnection();
			String sql = "delete from t_member "
					+"where id = ?";
			System.out.println("SQL : " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //delMember()
	
	public boolean isExisted(MemberVO vo) {
		boolean result = false;
		String id = vo.getId();
		String pwd = vo.getPwd();
		
		try {
			//db - servlet 연결
			con = ds.getConnection();
			
			//조회하는 ID가 있으면  'true', 없으면 'false' (as result는 레코드 이름)
			String sql = "SELECT IF(COUNT(*) = 1 , 'true', 'false') AS RESULT"
					+ " FROM t_member"
					+" WHERE id = ? AND pwd = ?";
			
			//SQL문을 PrepareStatement에 등록하고 각각의 자리(?)를 채운다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);			
			pstmt.setString(2, pwd);
			
			//DB에 쿼리를 전송하고, 결과 값을 받는다.
			ResultSet rs = pstmt.executeQuery();

			rs.next();	//커서를 첫 번째 레코드에 위치한다.
			
			//'result'라는 레코드의  값을 boolean으로 변환
			result = Boolean.parseBoolean(rs.getString("result"));
			
			System.out.println("result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}