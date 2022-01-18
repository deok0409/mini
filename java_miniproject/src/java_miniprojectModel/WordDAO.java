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
			//��ü�� (Connection, PrepaaredStatement, ResultSet) ������
			close();

		}
	
		return a;
	}
}
