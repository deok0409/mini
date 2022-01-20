package BGM;


import javazoom.jl.player.MP3Player;

public class BGM_Controller {
	
	//static에 mp3 기능을 넣어주자.
	static MP3Player mp3 = new MP3Player();


	
	public static void play(String path) {
		mp3.play(path);	
		
	}
	
	public static void stop() {
		if(mp3.isPlaying() == true) {
			
			mp3.stop();
			
		}
		
		else System.out.println("재생중이 아닙니다.");
	}


}
