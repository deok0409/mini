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
			System.out.print("[1]로그인 [2]회원가입 [3]종료 => ");
			menu = sc.nextInt();

			if (menu == 1) {//로그인
				System.out.println("=============로그인============");
				System.out.print("ID 입력 : ");
				String ID = sc.next();
				System.out.print("PW 입력 : ");
				String Password = sc.next();
				boolean check = M_DAO.Login(ID, Password);
			
				
				
				if (check == true) {//로그인 성공시
					Controller Game = new Controller();
					
					System.out.println("로그인 성공!.\n");
					
					while(true) {
					System.out.print("[1]게임시작 [2]랭킹확인 [3]오답노트 [4]종료 => ");
					InMenu=sc.nextInt();
					
					if(InMenu==1) { //난이도 선택
						System.out.println("=============난이도 선택============");
						 System.out.print("[1]EASY [2]NORMAL [3]HARD >> ");
						level=sc.nextInt();
						
						Game.run(ID, level);
				
					}
					
					
				
					
					else if(InMenu==2) { //랭킹확인
						ArrayList<MemberVO> al = M_DAO.select_Lank();
						
						System.out.println("==============랭킹===============");
						for(MemberVO v: al) {
							System.out.println(v.getID()+ " " + v.getCount());
						
						}
					
						
						System.out.println("\n");
						
					}
					
					else if (InMenu==3) { //오답노트 
						
					}
					
					else if (InMenu==4) {//종료
						System.out.println("종료");
						break;
					}
				
					}
					
				} 
				
				else {//로그인 실패시
					System.out.println("로그인 실패. 다시 시도해주세요 \n");
				}
				System.out.println("\n");
				
				
				
				
				

			}

			else if (menu == 2) { //회원가입 
				System.out.println("=============회원가입============");
				System.out.print("ID 입력 : ");
				String ID = sc.next(); //회원가입 D
				System.out.print("PW 입력 : ");
				String Password = sc.next(); //회원가입 PASSWORD
				System.out.print("닉네임 입력 : ");
				String Nickname = sc.next(); //회원가입 닉네임
				
				boolean check = M_DAO.MemInsert(ID, Password, Nickname); 
				M_DAO.Insert_Lank(ID);
				
				if (check == true) {
					System.out.println("회원가입을 축하합니다.\n");
				} else {
					System.out.println("회원가입을 실패 했습니다. 다시 시도해주세요 \n");
				}
				
				System.out.println("\n");
			}

			else if (menu == 3) {
				System.out.println("게임 종료!");
				break;
				

			}

			else {
				System.out.println("\n다시 입력하세요");

			}

		}

	}

}
