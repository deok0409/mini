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
	
	public void Insert_Lank(String ID) { //회원가입과 동시에 랭킹테이블에 점수 초기화


		try { 
			connect();
			String sql = "insert into rank values(?, ?)";
			
			 pst = conn.prepareStatement(sql);
			//다시 실행해주세요!
			//5. 바인드 변수 채우기
			pst.setInt(1, 0);
			pst.setString(2, ID);
			

			int cnt = pst.executeUpdate();
			


		} catch (Exception e) { 
			e.printStackTrace(); 
			
			
		}finally {
			
			close();
		}
		
	}
	
	public int select_SCORE(String ID) { //ID로 점수 찾는 메소드
		int SCORE=0;
		ResultSet rs = null;
	
		try {
			//1. Oracle JDBC driver 동적로딩(실행할때 가지고 오겠다!)
			connect();
			
			//3. 실행할 sql문 정의
			String sql = "select score from rank where ID=?";
					
			//4. sql구문 준비 객체(PreparedStatement) 생성
			pst = conn.prepareStatement(sql);
			pst.setString(1, ID);
			
			//5. sql문을 실행하고 결과 처리
			 rs = pst.executeQuery();
			
			//STUDENT 테이블에 있는 값을 읽어서 출력 (각 학생의 정보 출력)
			while(rs.next()) {
				
				SCORE = rs.getInt("score");
				

				
			}

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			//객체들 (Connection, PrepaaredStatement, ResultSet) 마무리
			close();
			
			
		}
		return SCORE;
	}
	
	
	public void Update_Lank(String ID, int cn) {
		
		try { 
			connect();
			String sql = "update rank set SCORE=?, ID=? where ID=?";
			
			 pst = conn.prepareStatement(sql);
			//다시 실행해주세요!
			//5. 바인드 변수 채우기
			pst.setInt(1, cn);
			pst.setString(2, ID);
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
			String sql = "select id, score, "
					+ "dense_rank() over(order by score desc) as rank "
					+ "from rank";
			
			//4. sql구문 준비 객체(PreparedStatement) 생성
			pst = conn.prepareStatement(sql);
			
			//5. sql문을 실행하고 결과 처리
			 rs = pst.executeQuery();
			
			//STUDENT 테이블에 있는 값을 읽어서 출력 (각 학생의 정보 출력)
			while(rs.next()) {
				String ID = rs.getString("ID");
				//int Rank = rs.getInt("Rank");
				int COUNT = rs.getInt("score");
				int RANK = rs.getInt("rank");
				
								
				al.add( new RankVO(RANK,ID,COUNT));
				
				
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
