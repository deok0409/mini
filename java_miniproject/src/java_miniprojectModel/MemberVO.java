package java_miniprojectModel;

public class MemberVO {
	//회원 정보 (ID, PASSWORD, NICKNAME)
	
	private String ID;
	private String Password;
	private String Nickname;
	private String Words;
	private int count = 0;
	private String Note;
	private String faillist = "";
	
	public MemberVO() {
		
	}
	
	public MemberVO(String ID, String Password, String Nickname) {
		this.ID=ID;
		this.Password=Password;
		this.Nickname=Nickname;
	}
	
	public MemberVO(String Words) {
		this.Words=Words;
	}
	
	public MemberVO(String ID, int count) {
		this.ID=ID;
		this.count=count;
	}

	


	public void setWords(String words) {
		Words = words;
	}

	public String getID() {
		return ID;
	}


	public String getPassword() {
		return Password;
	}


	public String getNickname() {
		return Nickname;
		
		
	}

	public String getWords() {
		return Words;
	}
	
	public int getCount() {
		return count;
	}
	public String getFaillist() {
		return faillist;
	}

}
