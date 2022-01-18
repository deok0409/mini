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
	
	
	//DB ���� �޼ҵ�
	public void connect() {
		try {
		//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password="hr";

		//2. ����� ���� ����, db ���� ��ü (Connection) ����
		 conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////////���м� /////////////////////////////////
	
	public void close() {
		try {
			if(rs!=null) {
			rs.close(); //selectStds(), selectOneStd() ������ ����ϴ� ��ü
						//ResultSet ��ü�� �����Ǿ������� ȣ�� ������ �޼���
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
	public ArrayList<String> select_AllMiss() { //�����Ʈ ���
		
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
