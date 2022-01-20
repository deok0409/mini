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
	
	
	//DB ���� �޼ҵ�
	public void connect() {
		try {
		//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
		String user = "campus_d_1_0115";
		String password="smhrd1";

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
		
	//////////////////////////////////���м� /////////////////////////////////
	
		//���1. ȸ������ 
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
				
				if (cnt>0) { //�߰� ����
					check = true;
				}
				else {	//�߰� ����
					check = false;
				}

			
			} catch (Exception e) { 
			
				e.printStackTrace(); 
				
			
				
			}finally {
			
				close();
			}
			return check;

		
		
		
	}
		
//////////////////////////////////���м� /////////////////////////////////
		//���2. �α���
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
//////////////////////////////////���м� /////////////////////////////////
		
}
	

