package View;

import java.util.ArrayList;
import java.util.Scanner;

import java_miniproject.Controller;
import java_miniprojectModel.MemberDAO;
import java_miniprojectModel.MemberVO;
import java_miniprojectModel.MissDAO;
import java_miniprojectModel.RankDAO;
import java_miniprojectModel.RankVO;

public class View {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Controller M_C = new Controller();
		MemberDAO M_DAO = new MemberDAO();
		Controller R_C = new Controller();
		RankDAO R_DAO = new RankDAO();
		Controller Mi_C = new Controller();
		MissDAO Mi_DAO = new MissDAO();

		int menu, InMenu, level;
		String ID, Password;
		boolean check;

		while (true) {
			System.out.print("[1]�α��� [2]ȸ������ [3]���� => ");
			menu = sc.nextInt();

			if (menu == 1) {// �α���
				System.out.println("=============�α���============");
				System.out.print("ID �Է� : ");
				ID = sc.next();
				System.out.print("PW �Է� : ");
				Password = sc.next();
				check = M_DAO.Login(ID, Password);

				if (check == true) {// �α��� ������
					Controller Game = new Controller();
					
					System.out.println("=============================");
					for(int i=0; i<5; i++) {
					System.out.print("�������");
					try {
						Thread.sleep(300);
					}
					catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
					}

					System.out.println("\n�α��� ����!.\n");
					

					while (true) {
						System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]���� => ");
						InMenu = sc.nextInt();

						if (InMenu == 1) { // ���̵� ����

							System.out.println("\n\n");
							System.out.println("===============================");
							System.out.println("	HANGMAN GAME");
							System.out.println("===============================\n");

							System.out.println("	    _______ ");
							System.out.println("	    |     | ");
							System.out.println("	    |     | ");
							System.out.println("	    ��     | ");
							System.out.println("	   -|-    | ");
							System.out.println("	    ��     |  \n\n");

							System.out.println("=============���̵� ����============");
							System.out.print("[1]EASY [2]NORMAL [3]HARD >> ");
							level = sc.nextInt();

							System.out.println();
							Game.run(ID, level);

						}

						else if (InMenu == 2) { // ��ŷȮ��
							ArrayList<RankVO> al = R_DAO.select_Lank();
							System.out.println("\n");
							System.out.println("==============��ŷ===============");
							System.out.println("   ID      SCORE      RANK");
							System.out.println("===============================");
							for (RankVO v : al) {
								System.out.print("   ");
								System.out.print(v.getNickname());

								// ID ���ڼ��� ���� ���� ����
								for (int i = 0 + v.getNickname().length(); i < 10; i++) {
									System.out.print(" ");

								}
								System.out.print(v.getCount());
								System.out.print("          ");
								System.out.println(v.getRank());
							}

							System.out.println("===============================");
							System.out.println("\n");

						}
						/*
						 * else if (InMenu==3) { //�����Ʈ ArrayList<String> al = Mi_DAO.select_AllMiss();
						 * 
						 * System.out.println("================�����Ʈ=============="); for(String v : al)
						 * { System.out.println(v); } System.out.println("\n");
						 * 
						 * }
						 */

						else if (InMenu == 3) {// ����
							System.out.println("����");
							break;
						}

					}

				}

				else {// �α��� ���н�
					System.out.println("�α��� ����. �ٽ� �õ����ּ��� \n");
				}
				System.out.println("\n");

			}

			else if (menu == 2) { // ȸ������
				System.out.println("=============ȸ������============");
				System.out.print("ID �Է� : ");
				ID = sc.next(); // ȸ������ D
				System.out.print("PW �Է� : ");
				Password = sc.next(); // ȸ������ PASSWORD
				System.out.print("�г��� �Է� : ");
				String Nickname = sc.next(); // ȸ������ �г���

				check = M_DAO.MemInsert(ID, Password, Nickname);
				R_DAO.Insert_Lank(ID);

				if (check == true) {
					System.out.println("ȸ�������� �����մϴ�.\n");
				} else {
					System.out.println("ȸ�������� ���� �߽��ϴ�. �ٽ� �õ����ּ��� \n");
				}

				System.out.println("\n");
			} else if (menu == 3) {
				System.out.println("==============����==============");
				break;
			} else {
				System.out.println("�ٽ� �Է����ּ���!");
			}

		}

	}

	public void draw(int life) {
		switch (life) {
		case 5:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("        | ");
			System.out.println("        | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;
		case 4:
			System.out.println("\n\n");
			;
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ��     | ");
			System.out.println("        | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;
		case 3:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ��     | ");
			System.out.println("  |     | ");
			System.out.println("        | ");
			System.out.println("\n");
			break;
		case 2:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ��     | ");
			System.out.println(" -|     | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;
		case 1:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ��     | ");
			System.out.println(" -|-    | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;

		}
	}

}
