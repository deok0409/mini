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
	
			System.out.print("[1].�α��� [2].ȸ������ [3].����" );
			menu1 = sc.nextInt();
		
/*		while(true) {
			if(menu1 == 1) {
				System.out.print("ID : ");
				id = sc.next();
				System.out.print("PW : ");
				pw = sc.next();
				System.out.println("�α��� �Ǿ����ϴ�.");
				System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]�����Ʈ [4] ����");
				menu2 = sc.nextInt();
				if(menu2 == 1) {
					System.out.println("���� ���̵��� ������ �ּ���!");
					System.out.print("[1]easy [2]normal [3]hard");
					menu3 = sc.nextInt();
					if(menu3 == 1) {
						
						if() {
						score_arr[cnt1] = score;
						cnt1++;
						score1++;
						System.out.println("���� : "+score1);
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
						System.out.println("���� : "+score2);
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
						System.out.println("���� : "+score3);
						}
					
						else {
							fail_arr[cnt2] = fail_arr;
							cnt2++;
						}
					}
					else {
						System.out.println("���̵� ������ �ٽ����ּ���!");
					}
				}
			}
			else if(menu1 == 2) {
				System.out.print("ID�� �Է��� �ּ��� : ");
				id = sc.next();
				System.out.print("PW�� �Է��� �ּ��� : ");
				pw = sc.next();
				System.out.println("ȸ�������� �Ϸ� �Ǿ����ϴ�.");
			}
			else if(menu1 == 3) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			else {
				System.out.println("�߸� �Է��Ͽ����ϴ�. �ٽ��Է����ּ���!");
			}
		}*/
		
				
	}
}

