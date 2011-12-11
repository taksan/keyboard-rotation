package taksan.labs;

import android.view.View;
import android.view.View.OnClickListener;

public class RotationClickListener implements OnClickListener {
	
	private final RotationManager rotationManager;
	private boolean enabled;

	public RotationClickListener(RotationManager rotationManager) {
		this.rotationManager = rotationManager;
		
	}

	public void onClick(View v) {
		if (enabled) 
			this.rotationManager.disableRotation();
		else 
			this.rotationManager.enableRotation();
		
		enabled = !enabled;
	}
}