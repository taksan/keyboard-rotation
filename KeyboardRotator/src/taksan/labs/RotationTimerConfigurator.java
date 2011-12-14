package taksan.labs;

import android.widget.SeekBar;

public class RotationTimerConfigurator  {

	private SeekBar timeSlider;
	private int maxRotationTime;
	private int rotationTime;
	private RotationManager rotationManager;
	private final RotationChangeListener changeListener;
	
	public RotationTimerConfigurator(
			SeekBar timeSlider, 
			int maxRotationTime, 
			int initialRotationTime, 
			RotationManager rotationManager,
			RotationChangeListener changeListener) {
		this.timeSlider = timeSlider;
		this.maxRotationTime = maxRotationTime;
		rotationTime = initialRotationTime;
		this.rotationManager = rotationManager;
		this.changeListener = changeListener;
		
	}

	public void configure() {
		timeSlider.setMax(maxRotationTime);
		
		timeSlider.setProgress(rotationTime);
		RotationPeriodReconfiguredListener listener = new RotationPeriodReconfiguredListener(rotationManager, changeListener);
		timeSlider.setOnSeekBarChangeListener(listener);
	}

}
