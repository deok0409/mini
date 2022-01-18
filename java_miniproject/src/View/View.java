package View;

import java.util.ArrayList;
import java.util.Scanner;

import java_miniproject.Controller;
import java_miniprojectModel.MemberDAO;
import java_miniprojectModel.MemberVO;


public class View {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Controller M_C = new Controller();
		MemberDAO M_DAO = new MemberDAO();
		
		int menu, InMenu,level;

		while (true) {
			System.out.print("[1]�α��� [2]ȸ������ [3]���� => ");
			menu = sc.nextInt();

			if (menu == 1) {//�α���
				System.out.println("=============�α���============");
				System.out.print("ID �Է� : ");
				String ID = sc.next();
				System.out.print("PW �Է� : ");
				String Password = sc.next();
				boolean check = M_DAO.Login(ID, Password);
			
				
				
				if (check == true) {//�α��� ������
					Controller Game = new Controller();
					
					System.out.println("�α��� ����!.\n");
					
					while(true) {
					System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]�����Ʈ [4]���� => ");
					InMenu=sc.nextInt();
					
					if(InMenu==1) { //���̵� ����
						System.out.println("=============���̵� ����============");
						 System.out.print("[1]EASY [2]NORMAL [3]HARD >> ");
						level=sc.nextInt();
						
						Game.run(ID, level);
				
					}
					
					
				
					
					else if(InMenu==2) { //��ŷȮ��
						ArrayList<MemberVO> al = M_DAO.select_Lank();
						
						System.out.println("==============��ŷ===============");
						for(MemberVO v: al) {
							System.out.println(v.getID()+ " " + v.getCount());
						
						}
					
						
						System.out.println("\n");
						
					}
					
					else if (InMenu==3) { //�����Ʈ 
						
					}
					
					else if (InMenu==4) {//����
						System.out.println("����");
						break;
					}
				
					}
					
				} 
				
				else {//�α��� ���н�
					System.out.println("�α��� ����. �ٽ� �õ����ּ��� \n");
				}
				System.out.println("\n");
				
				
				
				
				

			}

			else if (menu == 2) { //ȸ������ 
				System.out.println("=============ȸ������============");
				System.out.print("ID �Է� : ");
				String ID = sc.next(); //ȸ������ D
				System.out.print("PW �Է� : ");
				String Password = sc.next(); //ȸ������ PASSWORD
				System.out.print("�г��� �Է� : ");
				String Nickname = sc.next(); //ȸ������ �г���
				
				boolean check = M_DAO.MemInsert(ID, Password, Nickname); 
				M_DAO.Insert_Lank(ID);
				
				if (check == true) {
					System.out.println("ȸ�������� �����մϴ�.\n");
				} else {
					System.out.println("ȸ�������� ���� �߽��ϴ�. �ٽ� �õ����ּ��� \n");
				}
				
				System.out.println("\n");
			}

			else if (menu == 3) {
				System.out.println("���� ����!");
				break;
				

			}

			else {
				System.out.println("\n�ٽ� �Է��ϼ���");

			}

		}

	}

}
