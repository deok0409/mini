package java_miniprojectModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MissDAO {
	
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
	public void Insett_Miss(String Miss_answer) {
		
		try {
			connect();
			String sql = "insert into miss values(?)";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, Miss_answer);
			
			int cnt = pst.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public ArrayList<String> select_AllMiss() { //오답노트 출력
		
		ResultSet rs = null;
		String a = null;
		
		ArrayList<String> al = new ArrayList<String>();
		
		try {
			connect();
			
			String sql = "select * from miss";
			
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				a = rs.getString("words");
				
				al.add(a);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		finally {
			close();
		}
		return al;
	}

}
