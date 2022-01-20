package View;

import java.util.ArrayList;
import java.util.Scanner;

import BGM.BGM_Controller;
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
		BGM_Controller MP3 = new BGM_Controller() ;
		
		int menu, InMenu, level;
		String ID, Password;
		boolean check;
		
		while (true) {
			
	    	MP3.play("C:\\Users\\smhrd\\Desktop\\music\\game-music-7408.mp3");
	    	
			System.out.print("[1]로그인 [2]회원가입 [3]종료 => ");
			menu = sc.nextInt();

			if (menu == 1) {// 로그인
				
				MP3.stop();
				System.out.println("\n─────────────로그인─────────────");
				System.out.print("ID 입력 : ");
				ID = sc.next();
				System.out.print("PW 입력 : ");
				Password = sc.next();
				check = M_DAO.Login(ID, Password);

				if (check == true) {// 로그인 성공시
					Controller Game = new Controller();
					System.out.println("\n");
					System.out.println("┌───────────────────────────┐");
					System.out.print("│ ");
					for(int i=0; i<5; i++) {
					System.out.print("■■");
					try {
						Thread.sleep(150);
					}
					catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
					}
					System.out.print("■■");
					System.out.print("   │");
					System.out.println("\n│\t   로그인 성공!   \t    │");
					System.out.println("└───────────────────────────┘");
					

					while (true) {
						System.out.print("[1]게임시작 [2]랭킹확인 [3]종료 => ");
						InMenu = sc.nextInt();

						if (InMenu == 1) { // 난이도 선택

							System.out.println("\n\n");
							System.out.println("┌───────────────────────────────────────────────────┐");
							System.out.println("│	 	   HANGMAN GAME\t\t\t    │");
							System.out.println("└───────────────────────────────────────────────────┘\n\n");

							System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
									+ "'0MMMMMMMM\r\n"
									+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
									+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
									+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
									+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
									+ "MMWx'    . lWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
									+ "MMX;       :KWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
									+ "MMWx'       .;xNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
									+ "MMMWXo.        :KMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMMM0,          ;KMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMM0,        ..  oWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMNc  ';     ;:  ,KMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MM0'  oo     :d. .kMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMO. .xd     :k' .dMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMXo,c0d.    cXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
									+ "MMMWWWWd     lNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
									+ "MMMMMMNc     cNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
									+ "MMMMMMK;     :XMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
									+ "MMMMMMO.  .  ,KMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
									+ "MMMMMMx. .;. .OMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
									+ "MMMMMMKl;dk:.:KMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
									+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
									+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");	

							System.out.println("──────────────────────난이도 선택────────────────────────");
							System.out.print("[1]EASY [2]NORMAL [3]HARD >> ");
							level = sc.nextInt();

							System.out.println();
							Game.run(ID, level, R_DAO.select_Nickname(ID) );

						}

						else if (InMenu == 2) { // 랭킹확인
							ArrayList<RankVO> al = R_DAO.select_Lank();
							System.out.println("\n");
							System.out.println("┌─────────────────────────────────────┐");
							System.out.println("│Nickname\tSCORE\t\tRANK  │");
							System.out.println("│─────────────────────────────────────│");
							
							for (RankVO v : al) {
								
								System.out.print("│"+ v.getNickname() + "\t\t");
								System.out.print(v.getCount()+ "\t\t");
								System.out.println(v.getRank()+"     │");
							}

							System.out.println("└─────────────────────────────────────┘");
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
				MP3.stop();
				System.out.println("=============회원가입============");
				System.out.print("ID 입력 : ");
				ID = sc.next(); // 회원가입 D
				System.out.print("PW 입력 : ");
				Password = sc.next(); // 회원가입 PASSWORD
				System.out.print("닉네임 입력 : ");
				String Nickname = sc.next(); // 회원가입 닉네임

				check = M_DAO.MemInsert(ID, Password, Nickname);
				R_DAO.Insert_Lank(ID, Nickname);

				if (check == true) {
					System.out.println("회원가입을 축하합니다.\n");
				} else {
					System.out.println("회원가입을 실패 했습니다. 다시 시도해주세요 \n");
				}

				System.out.println("\n");
			} else if (menu == 3) {
				MP3.stop();
				System.out.println("==============종료==============");
				break;
			} else {
				MP3.stop();
				System.out.println("다시 입력해주세요!");
			}

		}

	}

	public void draw(int life) {
		BGM_Controller MP3 = new BGM_Controller() ;
		
		switch (life) {
		case 5:
			MP3.play("C:\\Users\\smhrd\\Desktop\\music\\허무한 결말.mp3");
			System.out.println("\n\n");
			System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
					+ "'0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
					+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
					+ "MMWxMMMMMMMMWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
					+ "MMX;MMMMMMMMKWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
					+ "MMWxMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
					+ "MMMWXoMMMMMMMMMMKMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMM0MMMMMMMMMMMMKMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMM0MMMMMMMMMMMMMMWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMNMMMMMMMMMMMMMMMKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MM0MMMooMMMMMMMMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMOMMMxdMMMMMMMMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMXoMc0dMKMMMcXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMWWWWdMMMMMlNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
					+ "MMMMMMNMMMMMMcNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
					+ "MMMMMMKMMMMMMMXMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
					+ "MMMMMMOMMMMMMMKMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
					+ "MMMMMMxMMMMMMMOMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
					+ "MMMMMMKMMMMMMMKMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
					+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
					+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");
			System.out.println("\n\n");
			break;
		case 4:
		
			System.out.println("\n\n");
			
			System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
					+ "'0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
					+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
					+ "MMWx'    . lWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
					+ "MMX;       :KWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
					+ "MMWx'       .;xNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
					+ "MMMWXo.     MMMMKMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMM0MMMMMMMMMMMMKMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMM0MMMMMMMMMMMMMMWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMNMMMM;MMMMMMMMMMKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MM0MMMooMMMMMMMMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMOMMMxdMMMMMMMMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMXo,c0dMKMMMcXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMWWWWdMMMMMlNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
					+ "MMMMMMNcMMMMMcNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
					+ "MMMMMMKMMMMMMMXMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
					+ "MMMMMMOMMMMMMMKMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
					+ "MMMMMMxMMMMMMMOMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
					+ "MMMMMMKMMMMMMMKMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
					+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
					+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");
			System.out.println("\n\n");
			break;
			
		case 3:
			
			System.out.println("\n\n");
			System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
					+ "'0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
					+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
					+ "MMWx'    . lWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
					+ "MMX;       :KWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
					+ "MMWx'       .;xNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
					+ "MMMWXo.       MMKMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMM0MM       MMMKMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMM0MMM       MMMMWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMNMMMM;      MMMMKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MM0MMMoo      MMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMOMMMxd      MMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMXo,c0d.    cXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMWWWWdMMMMMlNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
					+ "MMMMMMNcMMMMMcNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
					+ "MMMMMMKMMMMMMMXMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
					+ "MMMMMMOMMMMMMMKMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
					+ "MMMMMMxMMMMMMMOMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
					+ "MMMMMMKMMMMMMMKMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
					+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
					+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");
			System.out.println("\n");
			break;
		case 2:
			
			System.out.println("\n\n");
			System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
					+ "'0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
					+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
					+ "MMWx'    . lWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
					+ "MMX;       :KWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
					+ "MMWx'       .;xNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
					+ "MMMWXo.       MMKMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMM0,        MMMKMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMM0,         MMMMWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMNc  ';      MMMMKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MM0'  oo      MMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMO. .xd      MMMMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMXo,c0d.    cXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMWWWWdMMMMMlNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
					+ "MMMMMMNcMMMMMcNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
					+ "MMMMMMKMMMMMMMXMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
					+ "MMMMMMOMMMMMMMKMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
					+ "MMMMMMxMMMMMMMOMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
					+ "MMMMMMKMMMMMMMKMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
					+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
					+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");
			System.out.println("\n\n");
			break;
		case 1:
			
			System.out.println("\n\n");
			System.out.println("MMMMK;'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"
					+ "'0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMNk;.  .:OWMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMNk;.. .:OWMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMNXk;. .:OWMMWo  '0MMMMMMMM\r\n"
					+ "MMMMMMMMMk.lWMMMMMMMMMMMMMMNk;  .:OWWo  '0MMMMMMMM\r\n"
					+ "MMMWXkddOd.lWMMMMMMMMMMMMMMMMNk;. .:Ol  '0MMMMMMMM\r\n"
					+ "MMWx'    . lWMMMMMMMMMMMMMMMMMMNk;. ..  '0MMMMMMMM\r\n"
					+ "MMX;       :KWMMMMMMMMMMMMMMMMMMMNk;    '0MMMMMMMM\r\n"
					+ "MMWx'       .;xNMMMMMMMMMMMMMMMMMMMNk,  '0MMMMMMMM\r\n"
					+ "MMMWXo.        :KMMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMM0,          ;KMMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMM0,        ..  oWMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMNc  ';     ;:  ,KMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MM0'  oo     :d. .kMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMO. .xd     :k' .dMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMXo,c0d.    cXkloKMMMMMMMMMMMMMMMMMWo  '0MMMMMMMM\r\n"
					+ "MMMWWWWdMMMMMlNMMMMMMMMMMMMMMMMMMMMMNc  .kWMMMMMMM\r\n"
					+ "MMMMMMNcMMMMMcNMMMMMMMMMMMMMMMMMMMMWd.   'OWMMMMMM\r\n"
					+ "MMMMMMKMMMMMMMXMMMMMMMMMMMMMMMMMMMWx.     'OWMMMMM\r\n"
					+ "MMMMMMOMMMMMMMKMMMMMMMMMMMMMMMMMMWk.       'OWMMMM\r\n"
					+ "MMMMMMxMMMMMMMOMMMMMMMMMMMMMMMMMMO'     ..  ,OMMMM\r\n"
					+ "MMMMMMKMMMMMMMKMMMMMMMMMMMMMMMMM0,  ;:  'x:  ,0MMM\r\n"
					+ "MMMMMMMWWMMNXNMMMMMMMMMMMMMMMMMX:  ;0o  '0K;  ;KMM\r\n"
					+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK; .kWk. :XMO' ,0MM\n\n");
			System.out.println("\n\n");
			break;

		}
	}

}
