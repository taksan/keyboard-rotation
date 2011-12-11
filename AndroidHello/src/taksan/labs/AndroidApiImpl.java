package taksan.labs;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class AndroidApiImpl implements AndroidApi {
	
	private final Activity app;

	public AndroidApiImpl(Activity app) {
		this.app = app;
		
	}

	public void vibrate() {
		Vibrator vibrator = (Vibrator)app.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = new long[] { 100, 250, 100, 500};
		vibrator.vibrate(pattern, -1);
	}

}
