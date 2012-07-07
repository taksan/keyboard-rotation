package taksan.labs;

import android.content.Context;
import android.media.MediaPlayer;

public class AlertPlayer {

	public static void play(Context activity) {
	     MediaPlayer mMediaPlayer = MediaPlayer.create(activity, R.raw.pitfall);
	     try {
	    	 mMediaPlayer.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
