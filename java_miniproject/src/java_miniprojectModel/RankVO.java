package java_miniprojectModel;

public class RankVO {
	private String nickname;
	private int count=0;
	private int Rank;
	
	public RankVO() {
		
	}
	public RankVO(int Rank,String nickname, int count) {
		this.Rank = Rank;
		this.nickname = nickname;
		this.count = count;
	}

	public String getNickname() {
		return nickname;
	}
	public int getRank() {
		return Rank;
	}
	public int getCount() {
		return count;
	}
}
