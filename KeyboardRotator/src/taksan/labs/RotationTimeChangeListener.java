package taksan.labs;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class RotationTimeChangeListener implements
		OnSeekBarChangeListener {
	private final RotationManager rotationManager;
	private final RotationListener listener;

	public RotationTimeChangeListener(RotationManager rotationManager, RotationListener listener) 
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
		listener.fireRotationTimeChanged();
	}
}