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
			System.out.print("[1]로그인 [2]회원가입 [3]종료 => ");
			menu = sc.nextInt();

			if (menu == 1) {// 로그인
				System.out.println("=============로그인============");
				System.out.print("ID 입력 : ");
				ID = sc.next();
				System.out.print("PW 입력 : ");
				Password = sc.next();
				check = M_DAO.Login(ID, Password);

				if (check == true) {// 로그인 성공시
					Controller Game = new Controller();
					
					System.out.println("=============================");
					for(int i=0; i<5; i++) {
					System.out.print("■■■■■■");
					try {
						Thread.sleep(300);
					}
					catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
					}

					System.out.println("\n로그인 성공!.\n");
					

					while (true) {
						System.out.print("[1]게임시작 [2]랭킹확인 [3]종료 => ");
						InMenu = sc.nextInt();

						if (InMenu == 1) { // 난이도 선택

							System.out.println("\n\n");
							System.out.println("===============================");
							System.out.println("	HANGMAN GAME");
							System.out.println("===============================\n");

							System.out.println("	    _______ ");
							System.out.println("	    |     | ");
							System.out.println("	    |     | ");
							System.out.println("	    ●     | ");
							System.out.println("	   -|-    | ");
							System.out.println("	    ㅅ     |  \n\n");

							System.out.println("=============난이도 선택============");
							System.out.print("[1]EASY [2]NORMAL [3]HARD >> ");
							level = sc.nextInt();

							System.out.println();
							Game.run(ID, level);

						}

						else if (InMenu == 2) { // 랭킹확인
							ArrayList<RankVO> al = R_DAO.select_Lank();
							System.out.println("\n");
							System.out.println("==============랭킹===============");
							System.out.println("   ID      SCORE      RANK");
							System.out.println("===============================");
							for (RankVO v : al) {
								System.out.print("   ");
								System.out.print(v.getNickname());

								// ID 글자수에 따라 가변 띄어쓰기
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
						 * else if (InMenu==3) { //오답노트 ArrayList<String> al = Mi_DAO.select_AllMiss();
						 * 
						 * System.out.println("================오답노트=============="); for(String v : al)
						 * { System.out.println(v); } System.out.println("\n");
						 * 
						 * }
						 */

						else if (InMenu == 3) {// 종료
							System.out.println("종료");
							break;
						}

					}

				}

				else {// 로그인 실패시
					System.out.println("로그인 실패. 다시 시도해주세요 \n");
				}
				System.out.println("\n");

			}

			else if (menu == 2) { // 회원가입
				System.out.println("=============회원가입============");
				System.out.print("ID 입력 : ");
				ID = sc.next(); // 회원가입 D
				System.out.print("PW 입력 : ");
				Password = sc.next(); // 회원가입 PASSWORD
				System.out.print("닉네임 입력 : ");
				String Nickname = sc.next(); // 회원가입 닉네임

				check = M_DAO.MemInsert(ID, Password, Nickname);
				R_DAO.Insert_Lank(ID);

				if (check == true) {
					System.out.println("회원가입을 축하합니다.\n");
				} else {
					System.out.println("회원가입을 실패 했습니다. 다시 시도해주세요 \n");
				}

				System.out.println("\n");
			} else if (menu == 3) {
				System.out.println("==============종료==============");
				break;
			} else {
				System.out.println("다시 입력해주세요!");
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
			System.out.println("  ●     | ");
			System.out.println("        | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;
		case 3:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ●     | ");
			System.out.println("  |     | ");
			System.out.println("        | ");
			System.out.println("\n");
			break;
		case 2:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ●     | ");
			System.out.println(" -|     | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;
		case 1:
			System.out.println("\n\n");
			System.out.println("  _______ ");
			System.out.println("  |     | ");
			System.out.println("  |     | ");
			System.out.println("  ●     | ");
			System.out.println(" -|-    | ");
			System.out.println("        | ");
			System.out.println("\n\n");
			break;

		}
	}

}
