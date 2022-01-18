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
	
	int hiddenChar; // ��ĭ ����
	StringBuffer hiddenWord; // ��ĭ���� �ܾ� ����
	String newWord=null; // ����� ���� �ܾ�.
	Scanner scanner;
	int failCount; // Ʋ�� Ƚ��
	int cnt =0;
	
	public int turn() {
		System.out.println(cnt);
		return cnt;
	}
	
	public Controller(){
		scanner = new Scanner(System.in);
	}
	
	
	public void run(String ID,int level){ //���� �����ϴ� �޼ҵ�
		
	      
	      while(true){
	 
	    	  hiddenChar=level;
		   
		      
		      if (hiddenChar < 1 || hiddenChar > 3) {
		         System.out.println("Error!! :: choose only 1-3");
		         return;
		      }
		      
	    	  while(true){
	    		  newWord = w.Load_words();
	    		  if(newWord ==null) System.out.println(newWord);
	    		  
	    		  if (newWord.length() <= hiddenChar) // �ܾ����ª����
	                  continue; // while�ٽ�
	              else
	                  break; // �ܾ��� Ż��.
	    	  }
	    	  
	    	  
    		  makeHidden(); // ���ڸ� ���� �ܾ� �����
    		  go(ID ,level);
    		  
    		  System.out.print("Continue? (y/n)");
    		  String answer = scanner.next();
    		  if(answer.equals("n")) {
    			  System.out.println("����!\n");
    			  break;
    		  }
    			  
	      }
	  }
	      
	      
      void makeHidden(){
    	  hiddenWord = new StringBuffer(newWord);
    	  Random ran = new Random();
    	  
    	  for(int i=0; i<hiddenChar+1; i++){
    		  int index = ran.nextInt(newWord.length());//�ܾ������ �����߻�
    		  char c = newWord.charAt(index);// ������°�� ���� ����. c��.
    		  for(int j=0; j<newWord.length(); j++){
    			  if(hiddenWord.charAt(j)==c) // j��° ���ڰ� c�� ������
    				  hiddenWord.setCharAt(j, '-'); 
                                    // j��° ���ڸ� -�� �ٲ�.
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
                  // �Է¹��� ���ڿ����� ����տ��ִ°͸� �̾Ƴ�.
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
    			  // i��° ��ĭ�� ���ڸ� ������.
    			  hiddenWord.setCharAt(i, key); // I�� key�� �ٲ�.
    			  hit = true;
    		  }
    	  }
    	  
    	  if(!hit) // hit�� false�� ī��Ʈ ����.
    		  failCount++;
    	  
    	  for(int i=0; i<newWord.length(); i++){
    		  if(hiddenWord.charAt(i) == '-') // �ܾ ��ĭ���ִ��� �˻�
    			  return false; // ��ĭ�������� false�� ��ȯ. while������.
    	  }
    	  return true; //��ĭ�˻��ߴµ� ��ĭ������ true��ȯ. whileŻ��.
      } 
      
      
      public static void main(String[] args) {
    	  
      }
}

