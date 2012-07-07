package taksan.labs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class KeyboardRotatorActivity extends Activity 
 	implements RotationUiFacade, ProgressBarHolder
{
	private static final int MAX_ROTATION_TIME = 15;
	private static final String ROTATION_TIME = "RotationTime";

	private TextView playerText;
	private SeekBar timeSlider;
	ProgressBar turnProgress;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new RotationController(this).start();
    }


	public Context getContext() {
		return this;
	}
	
	public void configure() {
		setContentView(R.layout.main);
		playerText = (TextView) findViewById(R.id.currentPlayer);
		turnProgress =  (ProgressBar) findViewById(R.id.turnProgress);
		turnProgress.setVisibility(View.INVISIBLE);
		
		configureTimeSlider();
		updateTimeLabel();
	}


	public void setOnClickListener(RotationClickListener rotationClickListener) {
		playerText.setOnClickListener(rotationClickListener);
	}

	private void configureTimeSlider() 
	{
		OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean _ignore3) {
				updateTimeLabel();
			}
			public void onStopTrackingTouch(SeekBar seekBar) {}
			public void onStartTrackingTouch(SeekBar seekBar) {}
		};
		timeSlider = (SeekBar) findViewById(R.id.rotationTimer);
		timeSlider.setMax(MAX_ROTATION_TIME);
		timeSlider.setProgress(getStoredRotationTime());
		timeSlider.setOnSeekBarChangeListener(listener);		
	}

	public void setPlayerName(String player) {
		if (player.equals("player 1"))
    		playerText.setTextColor(Color.YELLOW);
    	else
    		playerText.setTextColor(Color.RED);
		playerText.setText(player);

	}

	private TurnProgressTask turnProgressTask;
	public void startRotation() {
		turnProgress.setVisibility(View.VISIBLE);
		timeSlider.setVisibility(View.INVISIBLE);
		turnProgress.setMax(getRotationInHundrethsOfSecs());
		
		if (turnProgressTask != null)
			turnProgressTask.cancel();
		turnProgressTask = new TurnProgressTask(new ClockImpl(), this);
		turnProgressTask.schedule();
	}


	public void stopRotation() {
		playerText.setText(R.string.rotationStopped);
		playerText.setTextColor(Color.CYAN);
		turnProgress.setVisibility(View.INVISIBLE);
		timeSlider.setVisibility(View.VISIBLE);
		
		turnProgressTask.cancel();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		SharedPreferences.Editor prefs = this.getPreferences(MODE_PRIVATE).edit();
        prefs.putInt(ROTATION_TIME, timeSlider.getProgress());
        prefs.commit();
	}


	public Integer getStoredRotationTime() {
		SharedPreferences prefs = getPreferences(Activity.MODE_PRIVATE);
		int rotationTime = prefs.getInt(ROTATION_TIME, 7);
		return rotationTime;
	}


	public void updateTimeLabel() 
	{
		TextView timeLabel = (TextView) findViewById(R.id.rotationTimeLabel);
		int progress = timeSlider.getProgress();
		String label = getString(R.string.timer_slider);
		timeLabel.setText(label + " " + progress + " min");
	}	
	
	public boolean fireProgressUpdatedRetunTrueToReset(long elapsed) {
		boolean resetTimer = false;
		long newProgress = elapsed;
		int rotationPeriodInHundrethSeconds = getRotationInHundrethsOfSecs();
		if (newProgress > rotationPeriodInHundrethSeconds) {
			newProgress = newProgress - rotationPeriodInHundrethSeconds;
			resetTimer = true;
		}
		turnProgress.setProgress((int) newProgress);
		
		return resetTimer;
	}


	private int getRotationInHundrethsOfSecs() {
		return getRotationPeriod() /100;
	}


	public int getRotationPeriod() {
		return timeSlider.getProgress() * 60000;
	}
}