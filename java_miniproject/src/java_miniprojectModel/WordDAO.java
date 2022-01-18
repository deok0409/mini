package java_miniprojectModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordDAO {
	
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
	public String Load_words() {

		ResultSet rs = null;
		MemberVO words = null;
		String a =null;
		
		try {
			
			connect();
			
			String sql = "select * from("
					+ "    select * from words"
					+ "    order by DBMS_RANDOM.RANDOM"
					+ ") where rownum < 2";
				
			pst = conn.prepareStatement(sql);
			
			 rs = pst.executeQuery();
				 if(rs.next()) {
					
					  a=rs.getString("words"); 
					 
					 words = new MemberVO(a);
					 
					 a=words.getWords();
					

				 }
				 
				 else {
					 
					 words = null;

				 }
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			//객체들 (Connection, PrepaaredStatement, ResultSet) 마무리
			close();

		}
	
		return a;
	}
}
