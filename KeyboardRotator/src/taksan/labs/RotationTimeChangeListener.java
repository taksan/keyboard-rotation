package taksan.labs;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class RotationTimeChangeListener implements
		OnSeekBarChangeListener {
	private final RotationManager rotationManager;

	public RotationTimeChangeListener(RotationManager rotationManager) 
	{
		this.rotationManager = rotationManager;
		
	}
	public void onStopTrackingTouch(SeekBar seekBar) {
		rotationManager.disableRotation();
		rotationManager.enableRotation();
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
	}
}