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
			//selectStd -> 수정 : ArrayList에 담을 필요 없음!
			//sql문 수정 (조건이 필요! : 학생번호로(사용자 입력) 로 검색), insert참고
			//반환타입, 매개변수 결정!

			ResultSet rs = null;
			
			boolean check = false;
			
			
			try {
				//1. Oracle JDBC driver 동적로딩(실행할때 가지고 오겠다!)
				connect();
				
				//3. 실행할 sql문 정의
				String sql = "select * from ENGLISHGAME_USER where ID=? and password=?";
				
				//4. sql구문 준비 객체(PreparedStatement) 생성
				//String sql = "select * from student where num=";
				//pst = conn.prepareStatement(sql + n); 도 가능
				
				pst = conn.prepareStatement(sql);
				
				
				//4-1. 바인드 변수 채우기
				pst.setString(1, ID);
				pst.setString(2, Password);
				
				//5. sql문을 실행하고 결과 처리
				 rs = pst.executeQuery();
				
				//STUDENT 테이블에 있는 값을 읽어서 출력 (각 학생의 정보 출력)
				
				//->resultset 상에 해당 번호가 table상에 존재하면 최대 1명에대한 데이터만 존재
				 //존재하지 않으면 데이터 X
				 
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
				//객체들 (Connection, PrepaaredStatement, ResultSet) 마무리
				close();
				
				
			}
		
			return check;
		}
//////////////////////////////////구분선 /////////////////////////////////
		//3. 관리자 전체 삭제 및 부분삭제
		public boolean Admin_Allclear(String name, String Password, String Nickname) {
			

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
}
	

