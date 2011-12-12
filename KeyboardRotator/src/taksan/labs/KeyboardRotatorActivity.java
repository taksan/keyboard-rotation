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
import android.widget.SeekBar;
import android.widget.TextView;

public class KeyboardRotatorActivity extends Activity implements PlayerManager, RotationListener, RotationTimeProvider {
	
	final private Handler mHandler = new Handler();
	private CurrentPlayerManagerImpl currentPlayerManager;
	private TextView playerText;
	private SeekBar timeSlider;
	private RotationManagerImpl rotationManager;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        playerText = (TextView) findViewById(R.id.currentPlayer);
        
		TimerTaskFactory rotationTaskFactory = new TimerTaskFactory() {
			
			public TimerTask create() {
				currentPlayerManager = new CurrentPlayerManagerImpl(KeyboardRotatorActivity.this);
				return new RotationTask(currentPlayerManager);
			}
		};
		
		configureRotationTimerBar();
		
		TimerManagerImpl timerManager = new TimerManagerImpl();
		rotationManager = new RotationManagerImpl(rotationTaskFactory, timerManager, this, this);
		RotationClickListener rotationClickListener = new RotationClickListener(rotationManager);
		playerText.setOnClickListener(rotationClickListener);
    }

	private void configureRotationTimerBar() {
		timeSlider = (SeekBar) findViewById(R.id.rotationTimer);
		timeSlider.setMax(15);
		timeSlider.setProgress(7);
		RotationTimeChangeListener listener = new RotationTimeChangeListener(rotationManager);
		timeSlider.setOnSeekBarChangeListener(listener);
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
	}

	public void fireRotationDisabled() {
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

	public int getRotationPeriod() {
		return timeSlider.getProgress();
	}
}