package java_miniproject;

import java.util.Random;
import java.util.Scanner;

public class HangManGame {
	public static void main(String[] arge) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		int menu1;
		int menu2;
		int menu3;
		
		int[] score_arr = new int[3];
		int score1 = 1;
		int score2 = 3;
		int score3 = 5;
		int[] fail_arr = new int[3];
		int cnt1 = 0;
		int cnt2 = 0;
		String id;
		String pw;
	
			System.out.print("[1].로그인 [2].회원가입 [3].종료" );
			menu1 = sc.nextInt();
		
/*		while(true) {
			if(menu1 == 1) {
				System.out.print("ID : ");
				id = sc.next();
				System.out.print("PW : ");
				pw = sc.next();
				System.out.println("로그인 되었습니다.");
				System.out.print("[1]게임시작 [2]랭킹확인 [3]오답노트 [4] 종료");
				menu2 = sc.nextInt();
				if(menu2 == 1) {
					System.out.println("게임 난이도를 선택해 주세요!");
					System.out.print("[1]easy [2]normal [3]hard");
					menu3 = sc.nextInt();
					if(menu3 == 1) {
						
						if() {
						score_arr[cnt1] = score;
						cnt1++;
						score1++;
						System.out.println("총점 : "+score1);
						}
						else {
							fail_arr[cnt2] = fail_arr;
							cnt2++;
							
						}
					}
					else if(menu3 == 2) {
						if() {
						score_arr[cnt1] = score;
						cnt1++;
						score2++;
						System.out.println("총점 : "+score2);
						}
						else {
							fail_arr[cnt2] = fail_arr;
							cnt2++;
						}
					}
					else if(menu3 == 3) {
						if() {
						score_arr[cnt1] = score;
						cnt1++;
						score3++;
						System.out.println("총점 : "+score3);
						}
					
						else {
							fail_arr[cnt2] = fail_arr;
							cnt2++;
						}
					}
					else {
						System.out.println("난이도 선택을 다시해주세요!");
					}
				}
			}
			else if(menu1 == 2) {
				System.out.print("ID를 입력해 주세요 : ");
				id = sc.next();
				System.out.print("PW를 입력해 주세요 : ");
				pw = sc.next();
				System.out.println("회원가입이 완료 되었습니다.");
			}
			else if(menu1 == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else {
				System.out.println("잘못 입력하였습니다. 다시입력해주세요!");
			}
		}*/
		
				
	}
}

