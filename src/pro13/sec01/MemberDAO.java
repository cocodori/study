package pro13.sec01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource ds;
	
	//DataSource 초기화
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	} //MemberDAO()
	
	//모든 회원을 조회하는 메서드
	public List<MemberBean> memberList() {
		List<MemberBean> result = new ArrayList<>();
		try {
			//DB연동
			con = ds.getConnection();
			
			//최근 가입한 순으로 회원 목록을 조회하는 쿼리
			String sql = "select * from t_member order by regdate desc";
			System.out.println("PrepareStatement : " + sql);
			
			//쿼리를 Statement에 등록
			pstmt = con.prepareStatement(sql);

			//쿼리를 전송하고 결과를 받는다.
			ResultSet rs = pstmt.executeQuery();
			
			//값을 모두 소진할 때까지 반복
			while(rs.next()) {
				//컬럼 명으로 값을 받아올 수 있다.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				//조회한 회원 정보를 MemberBean객체에 담고, 다시 list에 저자한다.
				MemberBean vo = new MemberBean(id, pwd, name, email);
				vo.setRegdate(regdate);
				
				result.add(vo);
			}
			
			//사용한 sql관련 객체들은 닫는다.
			rs.close();
			pstmt.close();
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	//회원을 추가하는 메서드
	public void addMember(MemberBean memberBean) {
		try {
			//DB와 연결
			Connection con = ds.getConnection();

			//인자로 받은 객체의 값을 받아온다.
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			
			System.out.println("memberBean : " + memberBean);
			
			final String SQL = "insert into t_member(id,pwd,name,email)"
					+"values(?, ?, ?, ?)";
			
			System.out.println("SQL : " + SQL);
			
			//쿼리 등록
			pstmt = con .prepareStatement(SQL);
			
			//각 위치에 매핑
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			//전송
			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
