package java_miniprojectModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs  = null;
	public void connect() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
			
			conn = DriverManager.getConnection(url);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pst != null) {
				pst.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertRank(String nickname, int score) {
		
		boolean check = false;
		
		try {
			connect();
			
			String sql = "insert into rank valuse (";
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}
}

