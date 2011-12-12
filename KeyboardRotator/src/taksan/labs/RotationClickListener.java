package taksan.labs;

import android.view.View;
import android.view.View.OnClickListener;

public class RotationClickListener implements OnClickListener {
	
	private final RotationManager rotationManager;

	public RotationClickListener(RotationManager rotationManager) {
		this.rotationManager = rotationManager;
		
	}

	public void onClick(View v) {
		rotationManager.toggleRotation();
	}
}