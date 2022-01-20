package java_miniprojectModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	
	Connection conn=null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	//DB 연결 메소드
	public void connect() {
		try {
		//1. Oracle JDBC driver 동적로딩(실행할때 가지고 오겠다!)
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
		String user = "campus_d_1_0115";
		String password="smhrd1";

		//2. 사용할 계정 선택, db 연결 객체 (Connection) 생성
		 conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////////구분선 /////////////////////////////////
	
	public void close() {
		try {
			if(rs!=null) {
			rs.close(); //selectStds(), selectOneStd() 에서만 사용하는 객체
						//ResultSet 객체가 생성되었을때만 호출 가능한 메서드
			}
			
			if(pst!=null) {
			pst.close();
			}
			
			if(conn!=null) {
			conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//////////////////////////////////구분선 /////////////////////////////////
	
		//기능1. 회원가입 
		public boolean MemInsert(String name, String Password, String Nickname) {
	

			boolean check = false;
			
			try { 
				
				connect();
				
			
				String sql = "insert into ENGLISHGAME_USER values(?, ?, ?)";
			
				 pst = conn.prepareStatement(sql);
				 
				
				pst.setString(1, name );
				pst.setString(2, Password);
				pst.setString(3, Nickname);
			
				
			
				int cnt = pst.executeUpdate();
				
				if (cnt>0) { //추가 성공
					check = true;
				}
				else {	//추가 실패
					check = false;
				}

			
			} catch (Exception e) { 
			
				e.printStackTrace(); 
				
			
				
			}finally {
			
				close();
			}
			return check;

		
		
		
	}
		
//////////////////////////////////구분선 /////////////////////////////////
		//기능2. 로그인
		public boolean Login(String ID, String Password) {

			ResultSet rs = null;
			
			boolean check = false;
			
			
			try {
		
				connect();
				
				String sql = "select * from ENGLISHGAME_USER where ID=?"
						+ " and password=?";
				

				pst = conn.prepareStatement(sql);
				
				pst.setString(1, ID);
				pst.setString(2, Password);
				
		
				 rs = pst.executeQuery();
				
			
					 if(rs.next()) {
						check = true;

					 }
					 
					 else {
						 
						 check = false;
						 
					 }
		
			}
			catch(Exception e) {
				e.printStackTrace();
			}finally {
	
				close();
				
				
			}
		
			return check;
		}
//////////////////////////////////구분선 /////////////////////////////////
		
}
	

