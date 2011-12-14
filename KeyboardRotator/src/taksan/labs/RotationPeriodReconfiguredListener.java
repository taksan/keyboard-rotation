package taksan.labs;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class RotationPeriodReconfiguredListener implements
		OnSeekBarChangeListener {
	private final RotationManager rotationManager;
	private final RotationChangeListener listener;

	public RotationPeriodReconfiguredListener(RotationManager rotationManager, RotationChangeListener listener) 
	{
		this.rotationManager = rotationManager;
		this.listener = listener;
		
	}
	public void onStopTrackingTouch(SeekBar seekBar) {
		rotationManager.updateRotationTime();
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		listener.fireRotationTimeReconfigured();
	}
}