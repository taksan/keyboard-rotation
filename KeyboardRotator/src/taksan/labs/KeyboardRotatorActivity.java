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
	
	private static final String ROTATION_TIME = "RotationTime";
	final private Handler mHandler = new Handler();
	private CurrentPlayerManagerImpl currentPlayerManager;
	private TextView playerText;
	private SeekBar timeSlider;
	private RotationManagerImpl rotationManager;
	private Bundle savedInstanceState;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        playerText = (TextView) findViewById(R.id.currentPlayer);
        
		TimerTaskFactory rotationTaskFactory = new TimerTaskFactory() {
			
			public TimerTask create() {
				currentPlayerManager = new CurrentPlayerManagerImpl(KeyboardRotatorActivity.this);
				return new RotationTask(currentPlayerManager);
			}
		};
		
		TimerManagerImpl timerManager = new TimerManagerImpl();
		rotationManager = new RotationManagerImpl(rotationTaskFactory, timerManager, this, this);
		
		configureRotationTimerBar();
		
		RotationClickListener rotationClickListener = new RotationClickListener(rotationManager);
		playerText.setOnClickListener(rotationClickListener);
		
		updateRotationTimeLabel();
    }

	private void configureRotationTimerBar() {
		timeSlider = (SeekBar) findViewById(R.id.rotationTimer);
		timeSlider.setMax(15);
		Integer rotationTime = this.savedInstanceState.getInt(ROTATION_TIME, 7);
		timeSlider.setProgress(rotationTime);
		RotationTimeChangeListener listener = new RotationTimeChangeListener(rotationManager, this);
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
		String currentPlayer = currentPlayerManager.getCurrentPlayer();
		if (currentPlayer == null)
			return;
		setCurrentPlayer(currentPlayer);
	}

	public void fireRotationDisabled() {
		playerText.setText(R.string.rotationStopped);
		playerText.setTextColor(Color.GREEN);
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
		return timeSlider.getProgress() * 60000;
	}

	public void fireRotationTimeChanged() {
		updateRotationTimeLabel();
	}

	private void updateRotationTimeLabel() {
		TextView timeLabel = (TextView) findViewById(R.id.rotationTimeLabel);
		int progress = timeSlider.getProgress();
		String label = getString(R.string.timer_slider);
		timeLabel.setText(label + " " + progress + " min");
		
		this.savedInstanceState.putInt(ROTATION_TIME, progress);
	}
}