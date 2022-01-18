package java_miniprojectModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDAO {
	
	Connection conn=null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	//DB 연결 메소드
	public void connect() {
		try {
		//1. Oracle JDBC driver 동적로딩(실행할때 가지고 오겠다!)
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password="hr";

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
	
	public void Insert_Lank(String ID) { //랭킹테이블에 점수 삽입


		try { 
			connect();
			String sql = "insert into rank values(?, ?)";
			
			 pst = conn.prepareStatement(sql);
			//다시 실행해주세요!
			//5. 바인드 변수 채우기
			pst.setString(1, ID);
			pst.setInt(2, 0);
			

			int cnt = pst.executeUpdate();
			


		} catch (Exception e) { 
			e.printStackTrace(); 
			
			
		}finally {
			
			close();
		}
		
	}
	
	public void Update_Lank(String ID, int cn) {
		
		try { 
			connect();
			String sql = "update rank set ID=?, COUNT=? where ID=?";
			
			 pst = conn.prepareStatement(sql);
			//다시 실행해주세요!
			//5. 바인드 변수 채우기
			pst.setString(1, ID);
			pst.setInt(2, cn);
			pst.setString(3, ID);
			

			int cnt = pst.executeUpdate();


		} catch (Exception e) { 
			e.printStackTrace(); 
			
			
		}finally {
			
			close();
		}
		
	}
	
	
	public ArrayList <RankVO> select_Lank() {

		ResultSet rs = null;
		
		ArrayList<RankVO> al = new ArrayList<RankVO>();
		
		try {
			//1. Oracle JDBC driver 동적로딩(실행할때 가지고 오겠다!)
			connect();
			
			//3. 실행할 sql문 정의
			String sql = "select * from rank order by count desc";
			
			//4. sql구문 준비 객체(PreparedStatement) 생성
			pst = conn.prepareStatement(sql);
			
			//5. sql문을 실행하고 결과 처리
			 rs = pst.executeQuery();
			
			//STUDENT 테이블에 있는 값을 읽어서 출력 (각 학생의 정보 출력)
			while(rs.next()) {
				String nickname = rs.getString("nickname");
				int Rank = rs.getInt("Rank");
				int COUNT = rs.getInt("COUNT");
				
								
				al.add( new RankVO(Rank,nickname,COUNT));
				
				
			}

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			//객체들 (Connection, PrepaaredStatement, ResultSet) 마무리
			close();
			
			
		}
		return al;
	}
}
