package java_miniproject;

import java.util.Random;
import java.util.Scanner;

import java_miniprojectModel.MemberDAO;
import java_miniprojectModel.MemberVO;
import java_miniprojectModel.RankDAO;
import java_miniprojectModel.RankVO;
import java_miniprojectModel.WordDAO;
import java_miniprojectModel.WordVO;

public class Controller {
	
	MemberVO M_VO = new MemberVO();
	MemberDAO m = new MemberDAO();
	RankVO R_VO = new RankVO();
	RankDAO r = new RankDAO();
	WordVO W_VO = new WordVO();
	WordDAO w = new WordDAO();
	
	int hiddenChar; // 빈칸 개수
	StringBuffer hiddenWord; // 빈칸뚫은 단어 저장
	String newWord=null; // 선출된 랜덤 단어.
	Scanner scanner;
	int failCount; // 틀린 횟수
	int cnt =0;
	
	public int turn() {
		System.out.println(cnt);
		return cnt;
	}
	
	public Controller(){
		scanner = new Scanner(System.in);
	}
	
	
	public void run(String ID,int level){ //게임 실행하는 메소드
		
	      
	      while(true){
	 
	    	  hiddenChar=level;
		   
		      
		      if (hiddenChar < 1 || hiddenChar > 3) {
		         System.out.println("Error!! :: choose only 1-3");
		         return;
		      }
		      
	    	  while(true){
	    		  newWord = w.Load_words();
	    		  if(newWord ==null) System.out.println(newWord);
	    		  
	    		  if (newWord.length() <= hiddenChar) // 단어길이짧으면
	                  continue; // while다시
	              else
	                  break; // 단어길면 탈출.
	    	  }
	    	  
	    	  
    		  makeHidden(); // 글자를 숨긴 단어 만들기
    		  go(ID ,level);
    		  
    		  System.out.print("Continue? (y/n)");
    		  String answer = scanner.next();
    		  if(answer.equals("n")) {
    			  System.out.println("종료!\n");
    			  break;
    		  }
    			  
	      }
	  }
	      
	      
      void makeHidden(){
    	  hiddenWord = new StringBuffer(newWord);
    	  Random ran = new Random();
    	  
    	  for(int i=0; i<hiddenChar+1; i++){
    		  int index = ran.nextInt(newWord.length());//단어길이의 난수발생
    		  char c = newWord.charAt(index);// 난수번째의 글자 저장. c에.
    		  for(int j=0; j<newWord.length(); j++){
    			  if(hiddenWord.charAt(j)==c) // j번째 글자가 c랑 같으면
    				  hiddenWord.setCharAt(j, '-'); 
                                    // j번째 글자를 -로 바꿈.
    		  }
    	  }
      }
      
      void go(String ID,int level){
    	  failCount = 0;
    	  char key;
    	  do{
    		  if(failCount == 5){
    			  System.out.println("Failed.");
    			  break;
    		  }
    		  
    		  System.out.println(hiddenWord);
    		  System.out.print(">>");
    		  String text = scanner.next();
    		  key = text.charAt(0); 
                  // 입력받은 문자열에서 가장앞에있는것만 뽑아냄.
    	  } while(!complete(key));
    	  
    	  System.out.println("\nWord >> "+newWord);
    	  
    	  if(level==1) {
    		  cnt+=1;
    		  r.Update_Lank(ID, cnt);
    	  }
    	  else if (level==2)   {
    		  cnt+=3;
    		  r.Update_Lank(ID, cnt);
    		 
    		  }
    	  else cnt+=5;{
    		  r.Update_Lank(ID, cnt);
    		 
    	  }
    	  
      }
      
      
      boolean complete(char key){
    	  boolean hit = false;
    	  for(int i=0; i<newWord.length(); i++){
    		  if(hiddenWord.charAt(i)=='-'&&newWord.charAt(i)==key){
    			  // i번째 빈칸의 글자를 맞춘경우.
    			  hiddenWord.setCharAt(i, key); // I를 key로 바꿈.
    			  hit = true;
    		  }
    	  }
    	  
    	  if(!hit) // hit이 false면 카운트 증가.
    		  failCount++;
    	  
    	  for(int i=0; i<newWord.length(); i++){
    		  if(hiddenWord.charAt(i) == '-') // 단어에 빈칸이있는지 검사
    			  return false; // 빈칸이있으면 false를 반환. while문지속.
    	  }
    	  return true; //빈칸검사했는데 빈칸없으면 true반환. while탈출.
      } 
      
      
      public static void main(String[] args) {
    	  
      }
}

