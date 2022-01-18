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
		
	//////////////////////////////////���м� /////////////////////////////////
	
		//���1. ȸ������ 
		public boolean MemInsert(String name, String Password, String Nickname) {
	

			boolean check = false;
			
			try { 
				
				connect();
				
			
				String sql = "insert into mem values(?, ?, ?)";
			
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
			//selectStd -> ���� : ArrayList�� ���� �ʿ� ����!
			//sql�� ���� (������ �ʿ�! : �л���ȣ��(����� �Է�) �� �˻�), insert����
			//��ȯŸ��, �Ű����� ����!

			ResultSet rs = null;
			
			boolean check = false;
			
			
			try {
				//1. Oracle JDBC driver �����ε�(�����Ҷ� ������ ���ڴ�!)
				connect();
				
				//3. ������ sql�� ����
				String sql = "select * from mem where ID=? and password=?";
				
				//4. sql���� �غ� ��ü(PreparedStatement) ����
				//String sql = "select * from student where num=";
				//pst = conn.prepareStatement(sql + n); �� ����
				
				pst = conn.prepareStatement(sql);
				
				
				//4-1. ���ε� ���� ä���
				pst.setString(1, ID);
				pst.setString(2, Password);
				
				//5. sql���� �����ϰ� ��� ó��
				 rs = pst.executeQuery();
				
				//STUDENT ���̺� �ִ� ���� �о ��� (�� �л��� ���� ���)
				
				//->resultset �� �ش� ��ȣ�� table�� �����ϸ� �ִ� 1������ �����͸� ����
				 //�������� ������ ������ X
				 
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
				//��ü�� (Connection, PrepaaredStatement, ResultSet) ������
				close();
				
				
			}
		
			return check;
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
		
		
		public ArrayList <MemberVO> select_Lank() {

			ResultSet rs = null;
			
			ArrayList<MemberVO> al = new ArrayList<MemberVO>();
			
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
					String ID = rs.getString("ID");
					int COUNT = rs.getInt("COUNT");
					
					
					
					al.add( new MemberVO(ID,COUNT));
					
					
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
