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
	
	public void Insert_Lank(String ID) { //��ŷ���̺� ���� ����


		try { 
			connect();
			String sql = "insert into rank values(?, ?)";
			
			 pst = conn.prepareStatement(sql);
			//�ٽ� �������ּ���!
			//5. ���ε� ���� ä���
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
			//�ٽ� �������ּ���!
			//5. ���ε� ���� ä���
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
			//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
			connect();
			
			//3. ������ sql�� ����
			String sql = "select * from rank order by count desc";
			
			//4. sql���� �غ� ��ü(PreparedStatement) ����
			pst = conn.prepareStatement(sql);
			
			//5. sql���� �����ϰ� ��� ó��
			 rs = pst.executeQuery();
			
			//STUDENT ���̺� �ִ� ���� �о ��� (�� �л��� ���� ���)
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
			//��ü�� (Connection, PrepaaredStatement, ResultSet) ������
			close();
			
			
		}
		return al;
	}
}
