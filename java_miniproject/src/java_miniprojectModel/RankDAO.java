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
	
	public void Insert_Lank(String ID) { //ȸ�����԰� ���ÿ� ��ŷ���̺� ���� �ʱ�ȭ


		try { 
			connect();
			String sql = "insert into rank values(?, ?)";
			
			 pst = conn.prepareStatement(sql);
			//�ٽ� �������ּ���!
			//5. ���ε� ���� ä���
			pst.setInt(1, 0);
			pst.setString(2, ID);
			

			int cnt = pst.executeUpdate();
			


		} catch (Exception e) { 
			e.printStackTrace(); 
			
			
		}finally {
			
			close();
		}
		
	}
	
	public int select_SCORE(String ID) { //ID�� ���� ã�� �޼ҵ�
		int SCORE=0;
		ResultSet rs = null;
	
		try {
			//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
			connect();
			
			//3. ������ sql�� ����
			String sql = "select score from rank where ID=?";
					
			//4. sql���� �غ� ��ü(PreparedStatement) ����
			pst = conn.prepareStatement(sql);
			pst.setString(1, ID);
			
			//5. sql���� �����ϰ� ��� ó��
			 rs = pst.executeQuery();
			
			//STUDENT ���̺� �ִ� ���� �о ��� (�� �л��� ���� ���)
			while(rs.next()) {
				
				SCORE = rs.getInt("score");
				

				
			}

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			//��ü�� (Connection, PrepaaredStatement, ResultSet) ������
			close();
			
			
		}
		return SCORE;
	}
	
	
	public void Update_Lank(String ID, int cn) {
		
		try { 
			connect();
			String sql = "update rank set SCORE=?, ID=? where ID=?";
			
			 pst = conn.prepareStatement(sql);
			//�ٽ� �������ּ���!
			//5. ���ε� ���� ä���
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
			//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
			connect();
			
			//3. ������ sql�� ����
			String sql = "select id, score, "
					+ "dense_rank() over(order by score desc) as rank "
					+ "from rank";
			
			//4. sql���� �غ� ��ü(PreparedStatement) ����
			pst = conn.prepareStatement(sql);
			
			//5. sql���� �����ϰ� ��� ó��
			 rs = pst.executeQuery();
			
			//STUDENT ���̺� �ִ� ���� �о ��� (�� �л��� ���� ���)
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
			//��ü�� (Connection, PrepaaredStatement, ResultSet) ������
			close();
			
			
		}
		return al;
	}
}
