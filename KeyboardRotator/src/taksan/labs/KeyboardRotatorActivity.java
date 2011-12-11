package taksan.labs;

import java.util.TimerTask;

import taksan.labs.android.CurrentPlayerManagerImpl;
import taksan.labs.android.PlayerManager;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class KeyboardRotatorActivity extends Activity implements PlayerManager, RotationListener {
	
	final private Handler mHandler = new Handler();
    private Button rotation;
	private CurrentPlayerManagerImpl currentPlayerManager;
	private TextView playerText;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rotation = (Button) findViewById(R.id.rotationButton);
        playerText = (TextView) findViewById(R.id.currentPlayer);
        
		TimerTaskFactory rotationTaskFactory = new TimerTaskFactory() {
			
			public TimerTask create() {
				currentPlayerManager = new CurrentPlayerManagerImpl(KeyboardRotatorActivity.this);
				return new RotationTask(currentPlayerManager);
			}
		};
		RotationManagerImpl rotationManager = new RotationManagerImpl(rotationTaskFactory, new TimerManagerImpl(), this);
		RotationClickListener rotationClickListener = new RotationClickListener(rotationManager);
		rotation.setOnClickListener(rotationClickListener);
		
    }

	public void setCurrentPlayer(final String player) {
		vibrate();
		
		final Runnable mUpdateUITimerTask = new Runnable() {
		    public void run() {
		    	playerText.setText(player);
		    	if (player.equals("player 1"))
		    		playerText.setTextColor(Color.BLUE);
		    	else
		    		playerText.setTextColor(Color.RED);
		    }
		};
		mHandler.post(mUpdateUITimerTask);
	}

	public void fireRotationEnabled() {
		setCurrentPlayer(currentPlayerManager.getCurrentPlayer());
		rotation.setText(R.string.rotationStop);
	}

	public void fireRotationDisabled() {
		rotation.setText(R.string.rotationStart);
		playerText.setText(R.string.rotationStopped);
	}
	
	private void vibrate() {
		try {
			Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			long[] pattern = new long[] { 100, 250, 100, 500};
			vibrator.vibrate(pattern, -1);
		}
		catch(Exception e) {
			Log.println(Log.INFO, "TestRunner", "Vibrations needs permission");
		}
	}
}