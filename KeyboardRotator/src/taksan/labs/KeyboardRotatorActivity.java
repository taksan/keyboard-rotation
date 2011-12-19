package taksan.labs;

import java.util.TimerTask;

import taksan.labs.android.CurrentPlayerManagerImpl;
import taksan.labs.android.PlayerManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class KeyboardRotatorActivity extends Activity 
	implements PlayerManager, RotationListener, RotationTimeProvider, RotationChangeListener, ProgressBarHolder {
	
	private static final int MAX_ROTATION_TIME = 15;
	private static final String ROTATION_TIME = "RotationTime";
	final private Handler mHandler = new Handler();
	private CurrentPlayerManagerImpl currentPlayerManager;
	private TextView playerText;
	private SeekBar timeSlider;
	ProgressBar turnProgress;
	private RotationManagerImpl rotationManager;
	private TurnProgressTask turnProgressTask;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        playerText = (TextView) findViewById(R.id.currentPlayer);
		turnProgress =  (ProgressBar) findViewById(R.id.turnProgress);
        
		TimerTaskFactory rotationTaskFactory = new TimerTaskFactory() {
			
			public TimerTask create() {
				currentPlayerManager = new CurrentPlayerManagerImpl(KeyboardRotatorActivity.this);
				return new RotationTask(currentPlayerManager);
			}
		};
		
		TimerManagerImpl timerManager = new TimerManagerImpl();
		rotationManager = new RotationManagerImpl(rotationTaskFactory, timerManager, this, this);
		
		timeSlider = (SeekBar) findViewById(R.id.rotationTimer);
		new RotationTimerConfigurator(timeSlider, MAX_ROTATION_TIME, getRotationTime(), rotationManager, this).configure();
		
		initializeTurnProgressIndicator();
		
		RotationClickListener rotationClickListener = new RotationClickListener(rotationManager);
		playerText.setOnClickListener(rotationClickListener);
		
		updateRotationTimeLabel();
    }

	private void initializeTurnProgressIndicator() {
		turnProgress.setVisibility(View.INVISIBLE);
	}

	private Integer getRotationTime() {
		SharedPreferences prefs = this.getPreferences(MODE_PRIVATE);
		int rotationTime = prefs.getInt(ROTATION_TIME, 7);
		return rotationTime;
	}

	public void setCurrentPlayer(final String player) {
		alertRotation();
		
		final Runnable mUpdateUITimerTask = new Runnable() {
		    public void run() {
		    	playerText.setText(player);
		    	if (player.equals("player 1"))
		    		playerText.setTextColor(Color.YELLOW);
		    	else
		    		playerText.setTextColor(Color.RED);
		    	
		    	startProgressBar();
		    }
		};
		mHandler.post(mUpdateUITimerTask);
	}

	public void fireRotationEnabled() {
		startProgressBar();
		
		String currentPlayer = currentPlayerManager.getCurrentPlayer();
		if (currentPlayer == null)
			return;
		setCurrentPlayer(currentPlayer);
	}

	private void startProgressBar() {
		if (turnProgressTask != null)
			turnProgressTask.cancel();
		turnProgress.setVisibility(View.VISIBLE);
		turnProgress.setMax(this.getRotationPeriod() / 100);
		turnProgressTask = new TurnProgressTask(new ClockImpl(), this);
		turnProgressTask.schedule();
	}

	public void fireRotationDisabled() {
		playerText.setText(R.string.rotationStopped);
		playerText.setTextColor(Color.CYAN);
		
		stopProgressBar();
	}

	private void stopProgressBar() {
		turnProgress.setVisibility(View.INVISIBLE);
		turnProgressTask.cancel();
	}
	
	private void alertRotation() {
		playAlert();
	}

	private void playAlert() {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	     if(alert == null){
	         alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	         if(alert == null){  
	             alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);               
	         }
	     }
	     MediaPlayer mMediaPlayer = new MediaPlayer();
	     try {
			mMediaPlayer.setDataSource(this, alert);
			final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		     if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
		    	 mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
		    	 mMediaPlayer.prepare();
		    	 mMediaPlayer.start();
		    	 Thread.sleep(1000);
		    	 mMediaPlayer.stop();
		      }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getRotationPeriod() {
		return timeSlider.getProgress() * 60000;
	}

	@Override
	public void onPause(){
		super.onPause();
		SharedPreferences.Editor prefs = this.getPreferences(MODE_PRIVATE).edit();
        prefs.putInt(ROTATION_TIME, timeSlider.getProgress());
        prefs.commit();
	}	
	
	public void fireRotationTimeReconfigured() {
		updateRotationTimeLabel();
	}
	
	private void updateRotationTimeLabel() {
		TextView timeLabel = (TextView) findViewById(R.id.rotationTimeLabel);
		int progress = timeSlider.getProgress();
		String label = getString(R.string.timer_slider);
		timeLabel.setText(label + " " + progress + " min");
	}

	public boolean fireProgressUpdatedRetunTrueToReset(long elapsed) {
		boolean resetTimer = false;
		long newProgress = elapsed;
		int rotationPeriodInHundrethSeconds = getRotationPeriod()/100;
		if (newProgress > rotationPeriodInHundrethSeconds) {
			newProgress = newProgress - rotationPeriodInHundrethSeconds;
			resetTimer = true;
		}
		turnProgress.setProgress((int) newProgress);
		
		return resetTimer;
	}
}